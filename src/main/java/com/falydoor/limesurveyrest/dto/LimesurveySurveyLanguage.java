package com.falydoor.limesurveyrest.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LimesurveySurveyLanguage {
    private String welcomeText;

    private String endText;

    public LimesurveySurveyLanguage(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        welcomeText = jsonObject.get("surveyls_welcometext").getAsString();
        endText = jsonObject.get("surveyls_endtext").getAsString();
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    @Override
    public String toString() {
        return "LimesurveySurveyLanguage{" +
                "welcomeText='" + welcomeText + '\'' +
                ", endText='" + endText + '\'' +
                '}';
    }
}
