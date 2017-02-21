package com.github.falydoor.limesurveyrc;

import com.github.falydoor.limesurveyrc.dto.*;
import com.github.falydoor.limesurveyrc.dto.json.LocalDateDeserializer;
import com.github.falydoor.limesurveyrc.dto.json.LsParticipantDeserializer;
import com.github.falydoor.limesurveyrc.dto.json.LsQuestionDeserializer;
import com.github.falydoor.limesurveyrc.dto.json.LsSurveyDeserializer;
import com.github.falydoor.limesurveyrc.exception.LimesurveyRCException;
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

/**
 * Limesurvey Remote Control client class.
 */
public class LimesurveyRC {

    private final Logger LOGGER = LoggerFactory.getLogger(LimesurveyRC.class);

    private String url;

    private String user;

    private String password;

    private int keyTimeout = 7200;

    private String key = "";

    private ZonedDateTime keyExpiration = ZonedDateTime.now();

    private Gson gson;

    /**
     * Create a new Limesurvey Remote Control instance.
     *
     * @param url      the url for the Remote Control endpoint (ex: http://XX.XX.XX.XX/index.php/admin/remotecontrol)
     * @param user     the user for the Remote Control
     * @param password the password for the Remote Control
     */
    public LimesurveyRC(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LsSurvey.class, new LsSurveyDeserializer())
                .registerTypeAdapter(LsQuestion.class, new LsQuestionDeserializer())
                .registerTypeAdapter(LsParticipant.class, new LsParticipantDeserializer())
                .create();
    }

    /**
     * Sets key timeout. Default value is 7200 seconds (2 hours).
     * If the value of "iSessionExpirationTime" in your config-defaults.php is different, you have to set it using this method.
     *
     * @param timeout the timeout
     */
    public void setKeyTimeout(int timeout) {
        keyTimeout = timeout;
    }

    /**
     * Call Limesurvey Remote Control.
     *
     * @param body the body of the request. Contains which method to call and the parameters
     * @return the json element containing the result from the call
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public JsonElement callRC(LsApiBody body) throws LimesurveyRCException {
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
                    throw new LimesurveyRCException("Error from API : " + result.getAsJsonObject().get("status").getAsString());
                }
                return result;
            } else {
                throw new LimesurveyRCException("Expecting status code 200, got " + response.getStatusLine().getStatusCode() + " instead");
            }
        } catch (IOException e) {
            throw new LimesurveyRCException("Exception while calling API : " + e.getMessage(), e);
        }
    }

    /**
     * Create an incomplete response, its field "completed" is set to "N" and the response doesn't have a submitdate.
     *
     * @param surveyId the survey id of the survey you want to create the response
     * @return the id of the response
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public int createIncompleteResponse(int surveyId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        HashMap<String, String> responseData = new HashMap<>();
        responseData.put("submitdate", "");
        String date = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        responseData.put("startdate", date);
        responseData.put("datestamp", date);
        params.setResponseData(responseData);
        return callRC(new LsApiBody("add_response", params)).getAsInt();
    }

    /**
     * Complete a response with the current date.
     *
     * @param surveyId   the survey id of the survey you want to complete the response
     * @param responseId the response id of the response you want to complete
     * @return true if the response was successfuly completed
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public boolean completeResponse(int surveyId, int responseId) throws LimesurveyRCException {
        return completeResponse(surveyId, responseId, LocalDateTime.now());
    }

    /**
     * Complete a response.
     *
     * @param surveyId   the survey id of the survey you want to complete the response
     * @param responseId the response id of the response you want to complete
     * @param date       the date where the response will be completed (submitdate)
     * @return true if the response was successfuly completed
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public boolean completeResponse(int surveyId, int responseId, LocalDateTime date) throws LimesurveyRCException {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("submitdate", date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + date.format(DateTimeFormatter.ISO_LOCAL_TIME));
        JsonElement result = updateResponse(surveyId, responseId, responseData);

        if (!result.getAsBoolean()) {
            throw new LimesurveyRCException(result.getAsString());
        }

        return true;
    }

    /**
     * Update a response.
     *
     * @param surveyId     the survey id of the survey you want to update the response
     * @param responseId   the response id of the response you want to update
     * @param responseData the response data that contains the fields you want to update
     * @return the json element
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public JsonElement updateResponse(int surveyId, int responseId, Map<String, String> responseData) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        responseData.put("id", String.valueOf(responseId));
        params.setResponseData(responseData);

        return callRC(new LsApiBody("update_response", params));
    }

    /**
     * Gets questions from a survey.
     * The questions are ordered using the "group_order" field from the groups and then the "question_order" field from the questions
     *
     * @param surveyId the survey id of the survey you want to get the questions
     * @return a stream of questions in an ordered order
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsQuestion> getQuestions(int surveyId) throws LimesurveyRCException {
        return getGroups(surveyId).flatMap(group -> {
            try {
                return getQuestionsFromGroup(surveyId, group.getId());
            } catch (LimesurveyRCException e) {
                LOGGER.error("Unable to get questions from group " + group.getId(), e);
            }
            return Stream.empty();
        });
    }

    /**
     * Gets question from a survey.
     *
     * @param surveyId   the survey id of the survey you want to get the question
     * @param questionId the question id
     * @return the question
     * @throws LimesurveyRCException if no question was found with the given id
     */
    public LsQuestion getQuestion(int surveyId, int questionId) throws LimesurveyRCException {
        return getQuestions(surveyId).filter(question -> question.getId() == questionId)
                .findFirst().orElseThrow(() -> new LimesurveyRCException("No question found for id " + questionId + " in survey " + surveyId));
    }

    /**
     * Gets possible answers from a question.
     *
     * @param questionId the question id you want to get the answers
     * @return the answers of the question
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Map<String, LsQuestionAnswer> getQuestionAnswers(int questionId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey();
        params.setQuestionId(questionId);
        List<String> questionSettings = new ArrayList<>();
        questionSettings.add("answeroptions");
        params.setQuestionSettings(questionSettings);
        JsonElement result = callRC(new LsApiBody("get_question_properties", params)).getAsJsonObject().get("answeroptions");

        return gson.fromJson(result, new TypeToken<Map<String, LsQuestionAnswer>>() {
        }.getType());
    }

    /**
     * Gets groups from a survey.
     * The groups are ordered using the "group_order" field.
     *
     * @param surveyId the survey id you want to get the groups
     * @return a stream of groups in an ordered order
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsQuestionGroup> getGroups(int surveyId) throws LimesurveyRCException {
        JsonElement result = callRC(new LsApiBody("list_groups", getParamsWithKey(surveyId)));
        List<LsQuestionGroup> questionGroups = gson.fromJson(result, new TypeToken<List<LsQuestionGroup>>() {
        }.getType());

        return questionGroups.stream().sorted(Comparator.comparing(LsQuestionGroup::getOrder));
    }

    /**
     * Gets questions from a group.
     * The questions are ordered using the "question_order" field.
     *
     * @param surveyId the survey id you want to get the questions
     * @param groupId  the group id you want to get the questions
     * @return a stream of questions in an ordered order
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsQuestion> getQuestionsFromGroup(int surveyId, int groupId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        params.setGroupId(groupId);
        JsonElement result = callRC(new LsApiBody("list_questions", params));
        List<LsQuestion> questions = gson.fromJson(result, new TypeToken<List<LsQuestion>>() {
        }.getType());

        return questions.stream().sorted(Comparator.comparing(LsQuestion::getOrder));
    }

    /**
     * Check if a survey is active.
     *
     * @param surveyId the survey id of the survey you want to check
     * @return true if the survey is active
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public boolean isSurveyActive(int surveyId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        List<String> surveySettings = new ArrayList<>();
        surveySettings.add("active");
        params.setSurveySettings(surveySettings);

        return "Y".equals(callRC(new LsApiBody("get_survey_properties", params)).getAsJsonObject().get("active").getAsString());
    }

    /**
     * Check if a survey exists.
     *
     * @param surveyId the survey id of the survey you want to check
     * @return true if the survey exists
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public boolean isSurveyExists(int surveyId) throws LimesurveyRCException {
        return getSurveys().anyMatch(survey -> survey.getId() == surveyId);
    }

    /**
     * Gets surveys that are active.
     *
     * @return a stream of active surveys
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsSurvey> getActiveSurveys() throws LimesurveyRCException {
        return getSurveys().filter(LsSurvey::isActive);
    }

    /**
     * Gets all surveys.
     *
     * @return a stream of surveys
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsSurvey> getSurveys() throws LimesurveyRCException {
        JsonElement result = callRC(new LsApiBody("list_surveys", getParamsWithKey()));
        List<LsSurvey> surveys = gson.fromJson(result, new TypeToken<List<LsSurvey>>() {
        }.getType());

        return surveys.stream();
    }

    /**
     * Gets language properties from a survey.
     *
     * @param surveyId the survey id of the survey you want the properties
     * @return the language properties
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public LsSurveyLanguage getSurveyLanguageProperties(int surveyId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        List<String> localeSettings = new ArrayList<>();
        localeSettings.add("surveyls_welcometext");
        localeSettings.add("surveyls_endtext");
        params.setSurveyLocaleSettings(localeSettings);

        LsSurveyLanguage surveyLanguage = gson.fromJson(callRC(new LsApiBody("get_language_properties", params)), LsSurveyLanguage.class);
        surveyLanguage.setId(surveyId);
        return surveyLanguage;
    }

    /**
     * Gets the current session key.
     *
     * @return the session key
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public String getSessionKey() throws LimesurveyRCException {
        // Use the saved key if isn't expired
        if (!key.isEmpty() && ZonedDateTime.now().isBefore(keyExpiration)) {
            return key;
        }

        // Get session key and save it with an expiration set to 1 minute before the expiration date
        LsApiBody.LsApiParams params = new LsApiBody.LsApiParams();
        params.setUsername(user);
        params.setPassword(password);
        JsonElement result = callRC(new LsApiBody("get_session_key", params));
        key = result.getAsString();
        keyExpiration = ZonedDateTime.now().plusSeconds(keyTimeout - 60);

        return key;
    }

    /**
     * Gets participant properties.
     *
     * @param surveyId        the survey id of the participant's survey
     * @param token           the token of the participant
     * @param tokenProperties the token properties
     * @return the participant properties
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Map<String, String> getParticipantProperties(int surveyId, String token, List<String> tokenProperties) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        Map<String, String> queryProperties = new HashMap<>();
        queryProperties.put("token", token);
        params.setTokenQueryProperties(queryProperties);
        params.setTokenProperties(tokenProperties);

        return gson.fromJson(callRC(new LsApiBody("get_participant_properties", params)), new TypeToken<Map<String, String>>() {
        }.getType());
    }

    /**
     * Gets all participants from a survey.
     *
     * @param surveyId the survey id of the survey you want the participants
     * @return the all participants
     * @throws LimesurveyRCException the limesurvey rc exception
     */
    public Stream<LsParticipant> getAllParticipants(int surveyId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey(surveyId);
        params.setStart(0);
        params.setLimit(-1);
        List<LsParticipant> participants = gson.fromJson(callRC(new LsApiBody("list_participants", params)), new TypeToken<List<LsParticipant>>() {
        }.getType());

        return participants.stream();
    }

    private LsApiBody.LsApiParams getParamsWithKey(int surveyId) throws LimesurveyRCException {
        LsApiBody.LsApiParams params = getParamsWithKey();
        params.setSurveyId(surveyId);
        return params;
    }

    private LsApiBody.LsApiParams getParamsWithKey() throws LimesurveyRCException {
        LsApiBody.LsApiParams params = new LsApiBody.LsApiParams();
        params.setSessionKey(getSessionKey());
        return params;
    }

}
