package com.falydoor.limesurveyrest.dto;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

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
        private int surveyId;

        @SerializedName("iGroupID")
        private int groupId;

        @SerializedName("aSurveySettings")
        private JsonArray surveySettings;

        @SerializedName("aSurveyLocaleSettings")
        private JsonArray surveyLocaleSettings;

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

        public int getSurveyId() {
            return surveyId;
        }

        public void setSurveyId(int surveyId) {
            this.surveyId = surveyId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public JsonArray getSurveySettings() {
            return surveySettings;
        }

        public void setSurveySettings(JsonArray surveySettings) {
            this.surveySettings = surveySettings;
        }

        public JsonArray getSurveyLocaleSettings() {
            return surveyLocaleSettings;
        }

        public void setSurveyLocaleSettings(JsonArray surveyLocaleSettings) {
            this.surveyLocaleSettings = surveyLocaleSettings;
        }
    }
}
