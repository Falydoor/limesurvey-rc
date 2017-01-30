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

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LimesurveyRest {

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
            post.setEntity(new StringEntity(new Gson().toJson(limesurveyApiBody)));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                return new JsonParser().parse(result).getAsJsonObject().get("result");
            } else {
                throw new LimesurveyRestException("Expecting status code 200, got " + response.getStatusLine().getStatusCode() + " instead");
            }
        } catch (IOException e) {
            throw new LimesurveyRestException("Exception while calling API : " + e.getMessage(), e);
        }
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
