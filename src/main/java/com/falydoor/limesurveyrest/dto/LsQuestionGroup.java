package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

public class LsQuestionGroup {
    @SerializedName("gid")
    private int id;

    @SerializedName("group_order")
    private int order;

    @SerializedName("sid")
    private int surveyId;

    @SerializedName("group_name")
    private String name;

    private String description;

    private String language;

    @SerializedName("grelevance")
    private String relevance;

    @SerializedName("randomization_group")
    private String randomization;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getRandomization() {
        return randomization;
    }

    public void setRandomization(String randomization) {
        this.randomization = randomization;
    }

    @Override
    public String toString() {
        return "LsQuestionGroup{" +
                "id=" + id +
                ", order=" + order +
                ", surveyId=" + surveyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", relevance='" + relevance + '\'' +
                ", randomization='" + randomization + '\'' +
                '}';
    }
}
