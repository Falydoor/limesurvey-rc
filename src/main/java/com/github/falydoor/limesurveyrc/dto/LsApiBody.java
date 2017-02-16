package com.github.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Body of a Remote Control request. The body contains the name of the method and the parameters.
 */
public class LsApiBody {
    private String method;

    private int id;

    private LsApiParams params;

    /**
     * Instantiates a new Ls api body.
     *
     * @param method the method that will be called from the Remote Control
     * @param params the params that will be used
     */
    public LsApiBody(String method, LsApiParams params) {
        this.method = method;
        this.params = params;
        this.id = 1;
    }

    /**
     * Parameters of the body.
     */
    public static class LsApiParams {
        private String username;

        private String password;

        @SerializedName("sSessionKey")
        private String sessionKey;

        @SerializedName("iSurveyID")
        private Integer surveyId;

        @SerializedName("iGroupID")
        private Integer groupId;

        @SerializedName("iQuestionID")
        private Integer questionId;

        @SerializedName("aSurveySettings")
        private List<String> surveySettings;

        @SerializedName("aSurveyLocaleSettings")
        private List<String> surveyLocaleSettings;

        @SerializedName("aQuestionSettings")
        private List<String> questionSettings;

        @SerializedName("aResponseData")
        private Map<String, String> responseData;

        @SerializedName("aTokenQueryProperties")
        private Map<String, String> tokenQueryProperties;

        @SerializedName("aTokenProperties")
        private List<String> tokenProperties;

        /**
         * Gets username.
         *
         * @return the username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets username.
         *
         * @param username the username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Gets password.
         *
         * @return the password
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets password.
         *
         * @param password the password
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Gets session key.
         *
         * @return the session key
         */
        public String getSessionKey() {
            return sessionKey;
        }

        /**
         * Sets session key.
         *
         * @param sessionKey the session key
         */
        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        /**
         * Gets survey id.
         *
         * @return the survey id
         */
        public Integer getSurveyId() {
            return surveyId;
        }

        /**
         * Sets survey id.
         *
         * @param surveyId the survey id
         */
        public void setSurveyId(Integer surveyId) {
            this.surveyId = surveyId;
        }

        /**
         * Gets group id.
         *
         * @return the group id
         */
        public Integer getGroupId() {
            return groupId;
        }

        /**
         * Sets group id.
         *
         * @param groupId the group id
         */
        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        /**
         * Gets survey settings.
         *
         * @return the survey settings
         */
        public List<String> getSurveySettings() {
            return surveySettings;
        }

        /**
         * Sets survey settings.
         *
         * @param surveySettings the survey settings
         */
        public void setSurveySettings(List<String> surveySettings) {
            this.surveySettings = surveySettings;
        }

        /**
         * Gets survey locale settings.
         *
         * @return the survey locale settings
         */
        public List<String> getSurveyLocaleSettings() {
            return surveyLocaleSettings;
        }

        /**
         * Sets survey locale settings.
         *
         * @param surveyLocaleSettings the survey locale settings
         */
        public void setSurveyLocaleSettings(List<String> surveyLocaleSettings) {
            this.surveyLocaleSettings = surveyLocaleSettings;
        }

        /**
         * Gets response data.
         *
         * @return the response data
         */
        public Map<String, String> getResponseData() {
            return responseData;
        }

        /**
         * Sets response data.
         *
         * @param responseData the response data
         */
        public void setResponseData(Map<String, String> responseData) {
            this.responseData = responseData;
        }

        /**
         * Gets question id.
         *
         * @return the question id
         */
        public Integer getQuestionId() {
            return questionId;
        }

        /**
         * Sets question id.
         *
         * @param questionId the question id
         */
        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        /**
         * Gets question settings.
         *
         * @return the question settings
         */
        public List<String> getQuestionSettings() {
            return questionSettings;
        }

        /**
         * Sets question settings.
         *
         * @param questionSettings the question settings
         */
        public void setQuestionSettings(List<String> questionSettings) {
            this.questionSettings = questionSettings;
        }

        /**
         * Gets token query properties.
         *
         * @return the token query properties
         */
        public Map<String, String> getTokenQueryProperties() {
            return tokenQueryProperties;
        }

        /**
         * Sets token query properties.
         *
         * @param tokenQueryProperties the token query properties
         */
        public void setTokenQueryProperties(Map<String, String> tokenQueryProperties) {
            this.tokenQueryProperties = tokenQueryProperties;
        }

        /**
         * Gets token properties.
         *
         * @return the token properties
         */
        public List<String> getTokenProperties() {
            return tokenProperties;
        }

        /**
         * Sets token properties.
         *
         * @param tokenProperties the token properties
         */
        public void setTokenProperties(List<String> tokenProperties) {
            this.tokenProperties = tokenProperties;
        }
    }
}
