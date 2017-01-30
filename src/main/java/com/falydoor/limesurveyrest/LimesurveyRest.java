package com.falydoor.limesurveyrest;

import com.falydoor.limesurveyrest.dto.*;
import com.falydoor.limesurveyrest.dto.json.LsSurveyDeserializer;
import com.falydoor.limesurveyrest.exception.LimesurveyRestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        this.gson = new GsonBuilder().registerTypeAdapter(LsSurvey.class, new LsSurveyDeserializer()).create();
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

    public Stream<LsQuestionGroup> getGroups(int surveyId) throws LimesurveyRestException {
        JsonElement result = callApi(new LsApiBody("list_groups", getParamsWithKey(surveyId)));

        return StreamSupport.stream(result.getAsJsonArray().spliterator(), false)
                .map(group -> gson.fromJson(group, LsQuestionGroup.class))
                .sorted(Comparator.comparing(LsQuestionGroup::getOrder));
    }

    public Stream<LsQuestion> getQuestionsFromGroup(int surveyId, int groupId) throws LimesurveyRestException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        params.setGroupId(groupId);
        JsonElement result = callApi(new LsApiBody("list_questions", params));

        return StreamSupport.stream(result.getAsJsonArray().spliterator(), false)
                .map(question -> gson.fromJson(question, LsQuestion.class))
                .sorted(Comparator.comparing(LsQuestion::getOrder));
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
        return StreamSupport.stream(callApi(new LsApiBody("list_surveys", getParamsWithKey())).getAsJsonArray().spliterator(), false)
                .map(survey -> gson.fromJson(survey, LsSurvey.class));
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
