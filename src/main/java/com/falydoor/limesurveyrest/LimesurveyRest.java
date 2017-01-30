package com.falydoor.limesurveyrest;

import com.falydoor.limesurveyrest.dto.*;
import com.falydoor.limesurveyrest.dto.json.LocalDateDeserializer;
import com.falydoor.limesurveyrest.dto.json.LsQuestionDeserializer;
import com.falydoor.limesurveyrest.dto.json.LsSurveyDeserializer;
import com.falydoor.limesurveyrest.exception.LimesurveyRestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class LimesurveyRest {

    private final Logger LOGGER = LoggerFactory.getLogger(LimesurveyRest.class);

    private String url;

    private String user;

    private String password;

    private int keyTimeout = 7200;

    private String key = "";

    private ZonedDateTime keyExpiration = ZonedDateTime.now();

    private Gson gson;

    public LimesurveyRest(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LsSurvey.class, new LsSurveyDeserializer())
                .registerTypeAdapter(LsQuestion.class, new LsQuestionDeserializer())
                .create();
    }

    public void setKeyTimeout(int timeout) {
        keyTimeout = timeout;
    }

    public JsonElement callApi(LsApiBody body) throws LimesurveyRestException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            String jsonBody = gson.toJson(body);
            LOGGER.debug("API CALL JSON => " + jsonBody);
            post.setEntity(new StringEntity(jsonBody));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResult = EntityUtils.toString(response.getEntity());
                LOGGER.debug("API RESPONSE JSON => " + jsonResult);
                JsonElement result = new JsonParser().parse(jsonResult).getAsJsonObject().get("result");
                if (result.isJsonObject() && result.getAsJsonObject().has("status")) {
                    throw new LimesurveyRestException("Error from API : " + result.getAsJsonObject().get("status").getAsString());
                }
                return result;
            } else {
                throw new LimesurveyRestException("Expecting status code 200, got " + response.getStatusLine().getStatusCode() + " instead");
            }
        } catch (IOException e) {
            throw new LimesurveyRestException("Exception while calling API : " + e.getMessage(), e);
        }
    }

    public int createIncompleteResponse(int surveyId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put("submitdate", "");
        String date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        responseData.put("startdate", date);
        responseData.put("datestamp", date);
        params.setResponseData(responseData);
        return callApi(new LsApiBody("add_response", params)).getAsInt();
    }

    public boolean completeResponse(int surveyId, int responseId) throws LimesurveyRestException {
        return completeResponse(surveyId, responseId, LocalDateTime.now());
    }

    public boolean completeResponse(int surveyId, int responseId, LocalDateTime date) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put("submitdate", date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + date.format(DateTimeFormatter.ISO_LOCAL_TIME));
        responseData.put("id", String.valueOf(responseId));
        params.setResponseData(responseData);
        JsonElement result = callApi(new LsApiBody("update_response", params));

        if (!result.getAsBoolean()) {
            throw new LimesurveyRestException(result.getAsString());
        }

        return true;
    }

    public Stream<LsQuestion> getQuestions(int surveyId) throws LimesurveyRestException {
        return getGroups(surveyId).flatMap(group -> {
            try {
                return getQuestionsFromGroup(surveyId, group.getId());
            } catch (LimesurveyRestException e) {
                LOGGER.error("Unable to get questions from group " + group.getId(), e);
            }
            return Stream.empty();
        });
    }

    public Map<String, LsAnswer> getAnswers(int questionId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey();
        params.setQuestionId(questionId);
        List<String> questionSettings = new ArrayList<>();
        questionSettings.add("answeroptions");
        params.setQuestionSettings(questionSettings);
        JsonElement result = callApi(new LsApiBody("get_question_properties", params)).getAsJsonObject().get("answeroptions");

        return gson.fromJson(result, new TypeToken<Map<String, LsAnswer>>() {
        }.getType());
    }

    public Stream<LsQuestionGroup> getGroups(int surveyId) throws LimesurveyRestException {
        JsonElement result = callApi(new LsApiBody("list_groups", getParamsWithKey(surveyId)));
        List<LsQuestionGroup> questionGroups = gson.fromJson(result, new TypeToken<List<LsQuestionGroup>>() {
        }.getType());

        return questionGroups.stream().sorted(Comparator.comparing(LsQuestionGroup::getOrder));
    }

    public Stream<LsQuestion> getQuestionsFromGroup(int surveyId, int groupId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        params.setGroupId(groupId);
        JsonElement result = callApi(new LsApiBody("list_questions", params));
        List<LsQuestion> questions = gson.fromJson(result, new TypeToken<List<LsQuestion>>() {
        }.getType());

        return questions.stream().sorted(Comparator.comparing(LsQuestion::getOrder));
    }

    public boolean isSurveyActive(int surveyId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        List<String> surveySettings = new ArrayList<>();
        surveySettings.add("active");
        params.setSurveySettings(surveySettings);

        return "Y".equals(callApi(new LsApiBody("get_survey_properties", params)).getAsJsonObject().get("active").getAsString());
    }

    public boolean isSurveyExists(int surveyId) throws LimesurveyRestException {
        return getSurveys().anyMatch(survey -> survey.getId() == surveyId);
    }

    public Stream<LsSurvey> getActiveSurveys() throws LimesurveyRestException {
        return getSurveys().filter(LsSurvey::isActive);
    }

    public Stream<LsSurvey> getSurveys() throws LimesurveyRestException {
        JsonElement result = callApi(new LsApiBody("list_surveys", getParamsWithKey()));
        List<LsSurvey> surveys = gson.fromJson(result, new TypeToken<List<LsSurvey>>() {
        }.getType());

        return surveys.stream();
    }

    public LsSurveyLanguage getSurveyLanguageProperties(int surveyId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        List<String> localeSettings = new ArrayList<>();
        localeSettings.add("surveyls_welcometext");
        localeSettings.add("surveyls_endtext");
        params.setSurveyLocaleSettings(localeSettings);

        return gson.fromJson(callApi(new LsApiBody("get_language_properties", params)), LsSurveyLanguage.class);
    }

    public String getSessionKey() throws LimesurveyRestException {
        // Use the saved key if isn't expired
        if (!key.isEmpty() && ZonedDateTime.now().isBefore(keyExpiration)) {
            return key;
        }

        // Get session key and save it with an expiration set to 1 minute before the expiration date
        LsApiBody.LsApiParams params = new LsApiBody.LsApiParams();
        params.setUsername(user);
        params.setPassword(password);
        JsonElement result = callApi(new LsApiBody("get_session_key", params));
        key = result.getAsString();
        keyExpiration = ZonedDateTime.now().plusSeconds(keyTimeout - 60);

        return key;
    }

    private LsApiBody.LsApiParams getParamsWithKey(int surveyId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey();
        params.setSurveyId(surveyId);
        return params;
    }

    private LsApiBody.LsApiParams getParamsWithKey() throws LimesurveyRestException {
        LsApiBody.LsApiParams params = new LsApiBody.LsApiParams();
        params.setSessionKey(getSessionKey());
        return params;
    }

}
