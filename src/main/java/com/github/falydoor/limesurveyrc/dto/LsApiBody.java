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

        @SerializedName("iStart")
        private int start = 0;

        @SerializedName("iLimit")
        private int limit = -1;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public Integer getSurveyId() {
            return surveyId;
        }

        public void setSurveyId(Integer surveyId) {
            this.surveyId = surveyId;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public List<String> getSurveySettings() {
            return surveySettings;
        }

        public void setSurveySettings(List<String> surveySettings) {
            this.surveySettings = surveySettings;
        }

        public List<String> getSurveyLocaleSettings() {
            return surveyLocaleSettings;
        }

        public void setSurveyLocaleSettings(List<String> surveyLocaleSettings) {
            this.surveyLocaleSettings = surveyLocaleSettings;
        }

        public Map<String, String> getResponseData() {
            return responseData;
        }

        public void setResponseData(Map<String, String> responseData) {
            this.responseData = responseData;
        }

        public Integer getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        public List<String> getQuestionSettings() {
            return questionSettings;
        }

        public void setQuestionSettings(List<String> questionSettings) {
            this.questionSettings = questionSettings;
        }

        public Map<String, String> getTokenQueryProperties() {
            return tokenQueryProperties;
        }

        public void setTokenQueryProperties(Map<String, String> tokenQueryProperties) {
            this.tokenQueryProperties = tokenQueryProperties;
        }

        public List<String> getTokenProperties() {
            return tokenProperties;
        }

        public void setTokenProperties(List<String> tokenProperties) {
            this.tokenProperties = tokenProperties;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }
    }
}
