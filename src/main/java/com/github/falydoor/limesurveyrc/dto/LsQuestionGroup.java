package com.github.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Group of a question DTO.
 */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets survey id.
     *
     * @return the survey id
     */
    public int getSurveyId() {
        return surveyId;
    }

    /**
     * Sets survey id.
     *
     * @param surveyId the survey id
     */
    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets relevance.
     *
     * @return the relevance
     */
    public String getRelevance() {
        return relevance;
    }

    /**
     * Sets relevance.
     *
     * @param relevance the relevance
     */
    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    /**
     * Gets randomization.
     *
     * @return the randomization
     */
    public String getRandomization() {
        return randomization;
    }

    /**
     * Sets randomization.
     *
     * @param randomization the randomization
     */
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
