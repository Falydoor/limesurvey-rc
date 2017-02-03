package com.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Question DTO.
 */
public class LsQuestion {
    private String question;

    @SerializedName("qid")
    private int id;

    @SerializedName("gid")
    private int groupId;

    @SerializedName("question_order")
    private int order;

    private String type;

    private String relevance;

    @SerializedName("sid")
    private int surveyId;

    private String title;

    private String preg;

    private String help;

    private boolean other;

    private boolean mandatory;

    private String language;

    @SerializedName("scale_id")
    private int scaleId;

    @SerializedName("same_default")
    private int sameDefault;

    @SerializedName("modulename")
    private String moduleName;

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets question.
     *
     * @param question the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

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
     * Gets group id.
     *
     * @return the group id
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets group id.
     *
     * @param groupId the group id
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets preg.
     *
     * @return the preg
     */
    public String getPreg() {
        return preg;
    }

    /**
     * Sets preg.
     *
     * @param preg the preg
     */
    public void setPreg(String preg) {
        this.preg = preg;
    }

    /**
     * Gets help.
     *
     * @return the help
     */
    public String getHelp() {
        return help;
    }

    /**
     * Sets help.
     *
     * @param help the help
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * Is other boolean.
     *
     * @return the boolean
     */
    public boolean isOther() {
        return other;
    }

    /**
     * Sets other.
     *
     * @param other the other
     */
    public void setOther(boolean other) {
        this.other = other;
    }

    /**
     * Is mandatory boolean.
     *
     * @return the boolean
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets mandatory.
     *
     * @param mandatory the mandatory
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
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
     * Gets scale id.
     *
     * @return the scale id
     */
    public int getScaleId() {
        return scaleId;
    }

    /**
     * Sets scale id.
     *
     * @param scaleId the scale id
     */
    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    /**
     * Gets same default.
     *
     * @return the same default
     */
    public int getSameDefault() {
        return sameDefault;
    }

    /**
     * Sets same default.
     *
     * @param sameDefault the same default
     */
    public void setSameDefault(int sameDefault) {
        this.sameDefault = sameDefault;
    }

    /**
     * Gets module name.
     *
     * @return the module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets module name.
     *
     * @param moduleName the module name
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "LsQuestion{" +
                "question='" + question + '\'' +
                ", id=" + id +
                ", groupId=" + groupId +
                ", order=" + order +
                ", type='" + type + '\'' +
                ", relevance='" + relevance + '\'' +
                ", surveyId=" + surveyId +
                ", title='" + title + '\'' +
                ", preg='" + preg + '\'' +
                ", help='" + help + '\'' +
                ", other=" + other +
                ", mandatory=" + mandatory +
                ", language='" + language + '\'' +
                ", scaleId=" + scaleId +
                ", sameDefault=" + sameDefault +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
