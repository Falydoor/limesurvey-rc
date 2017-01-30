package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

public class LimesurveySurveyLanguage {
    @SerializedName("surveyls_welcometext")
    private String welcomeText;

    @SerializedName("surveyls_endtext")
    private String endText;

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
