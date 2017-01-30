package com.falydoor.limesurveyrest;

import com.falydoor.limesurveyrest.dto.*;
import com.falydoor.limesurveyrest.exception.LimesurveyRestException;
import com.google.gson.*;
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
import java.util.Comparator;
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

    public LimesurveyRest(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void setKeyTimeout(int timeout) {
        keyTimeout = timeout;
    }

    public JsonElement callApi(LimesurveyApiBody limesurveyApiBody) throws LimesurveyRestException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            String jsonBody = new Gson().toJson(limesurveyApiBody);
            LOGGER.debug("API CALL JSON => " + jsonBody);
            post.setEntity(new StringEntity(jsonBody));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                LOGGER.debug("API RESPONSE JSON => " + result);
                return new JsonParser().parse(result).getAsJsonObject().get("result");
            } else {
                throw new LimesurveyRestException("Expecting status code 200, got " + response.getStatusLine().getStatusCode() + " instead");
            }
        } catch (IOException e) {
            throw new LimesurveyRestException("Exception while calling API : " + e.getMessage(), e);
        }
    }

    public Stream<LimesurveyQuestion> getQuestions(int surveyId) throws LimesurveyRestException {
        return getGroups(surveyId).flatMap(group -> {
            try {
                return getQuestionsFromGroup(surveyId, group.getId());
            } catch (LimesurveyRestException e) {
                LOGGER.error("Unable to get questions from group " + group.getId(), e);
            }
            return Stream.empty();
        });
    }

    public Stream<LimesurveyQuestionGroup> getGroups(int surveyId) throws LimesurveyRestException {
        JsonElement result = callApi(new LimesurveyApiBody("list_groups", getParamsWithKey(surveyId)));

        return StreamSupport.stream(result.getAsJsonArray().spliterator(), false)
                .map(LimesurveyQuestionGroup::new)
                .sorted(Comparator.comparing(LimesurveyQuestionGroup::getOrder));
    }

    public Stream<LimesurveyQuestion> getQuestionsFromGroup(int surveyId, int groupId) throws LimesurveyRestException {
        LimesurveyApiBody.LimesurveyApiParams params = getParamsWithKey(surveyId);
        params.setGroupId(groupId);
        JsonElement result = callApi(new LimesurveyApiBody("list_questions", params));

        return StreamSupport.stream(result.getAsJsonArray().spliterator(), false)
                .map(LimesurveyQuestion::new)
                .sorted(Comparator.comparing(LimesurveyQuestion::getOrder));
    }

    public boolean isSurveyActive(int surveyId) throws LimesurveyRestException {
        LimesurveyApiBody.LimesurveyApiParams params = getParamsWithKey(surveyId);
        JsonArray surveySettings = new JsonArray();
        surveySettings.add(new JsonPrimitive("active"));
        params.setSurveySettings(surveySettings);

        return "Y".equals(callApi(new LimesurveyApiBody("get_survey_properties", params)).getAsJsonObject().get("active").getAsString());
    }

    public boolean isSurveyExists(int surveyId) throws LimesurveyRestException {
        return getSurveys().anyMatch(survey -> survey.getId() == surveyId);
    }

    public Stream<LimesurveySurvey> getActiveSurveys() throws LimesurveyRestException {
        return getSurveys().filter(LimesurveySurvey::isActive);
    }

    public Stream<LimesurveySurvey> getSurveys() throws LimesurveyRestException {
        return StreamSupport.stream(callApi(new LimesurveyApiBody("list_surveys", getParamsWithKey())).getAsJsonArray().spliterator(), false)
                .map(LimesurveySurvey::new);
    }

    public LimesurveySurveyLanguage getSurveyLanguageProperties(int surveyId) throws LimesurveyRestException {
        LimesurveyApiBody.LimesurveyApiParams params = getParamsWithKey(surveyId);
        JsonArray localeSettings = new JsonArray();
        localeSettings.add(new JsonPrimitive("surveyls_welcometext"));
        localeSettings.add(new JsonPrimitive("surveyls_endtext"));
        params.setSurveyLocaleSettings(localeSettings);

        return new LimesurveySurveyLanguage(callApi(new LimesurveyApiBody("get_language_properties", params)));
    }

    public String getSessionKey() throws LimesurveyRestException {
        // Use the saved key if isn't expired
        if (!key.isEmpty() && ZonedDateTime.now().isBefore(keyExpiration)) {
            return key;
        }

        // Get session key and save it with an expiration set to 1 minute before the expiration date
        LimesurveyApiBody.LimesurveyApiParams params = new LimesurveyApiBody.LimesurveyApiParams();
        params.setUsername(user);
        params.setPassword(password);
        JsonElement result = callApi(new LimesurveyApiBody("get_session_key", params));
        key = result.getAsString();
        keyExpiration = ZonedDateTime.now().plusSeconds(keyTimeout - 60);

        return key;
    }

    private LimesurveyApiBody.LimesurveyApiParams getParamsWithKey(int surveyId) throws LimesurveyRestException {
        LimesurveyApiBody.LimesurveyApiParams params = getParamsWithKey();
        params.setSurveyId(surveyId);
        return params;
    }

    private LimesurveyApiBody.LimesurveyApiParams getParamsWithKey() throws LimesurveyRestException {
        LimesurveyApiBody.LimesurveyApiParams params = new LimesurveyApiBody.LimesurveyApiParams();
        params.setSessionKey(getSessionKey());
        return params;
    }

}
