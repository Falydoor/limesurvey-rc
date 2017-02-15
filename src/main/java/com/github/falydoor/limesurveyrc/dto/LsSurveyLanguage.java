package com.github.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Survey language properties DTO.
 */
public class LsSurveyLanguage {
    private int id;

    @SerializedName("surveyls_welcometext")
    private String welcomeText;

    @SerializedName("surveyls_endtext")
    private String endText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets welcome text.
     *
     * @return the welcome text
     */
    public String getWelcomeText() {
        return welcomeText;
    }

    /**
     * Sets welcome text.
     *
     * @param welcomeText the welcome text
     */
    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    /**
     * Gets end text.
     *
     * @return the end text
     */
    public String getEndText() {
        return endText;
    }

    /**
     * Sets end text.
     *
     * @param endText the end text
     */
    public void setEndText(String endText) {
        this.endText = endText;
    }

    @Override
    public String toString() {
        return "LsSurveyLanguage{" +
                "id=" + id +
                ", welcomeText='" + welcomeText + '\'' +
                ", endText='" + endText + '\'' +
                '}';
    }
}
