package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LimesurveyApiBody {
    private String method;

    private int id;

    private LimesurveyApiParams params;

    public LimesurveyApiBody(String method, LimesurveyApiParams params) {
        this.method = method;
        this.params = params;
        this.id = 1;
    }

    public static class LimesurveyApiParams {
        private String username;

        private String password;

        @SerializedName("sSessionKey")
        private String sessionKey;

        @SerializedName("iSurveyID")
        private Integer surveyId;

        @SerializedName("iGroupID")
        private Integer groupId;

        @SerializedName("aSurveySettings")
        private List<String> surveySettings;

        @SerializedName("aSurveyLocaleSettings")
        private List<String> surveyLocaleSettings;

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
    }
}
