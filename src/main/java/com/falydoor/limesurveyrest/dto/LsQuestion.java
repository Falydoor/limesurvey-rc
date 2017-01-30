package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

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

    @SerializedName("gid")
    private int parentGroupId;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public int getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(int parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreg() {
        return preg;
    }

    public void setPreg(String preg) {
        this.preg = preg;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    public int getSameDefault() {
        return sameDefault;
    }

    public void setSameDefault(int sameDefault) {
        this.sameDefault = sameDefault;
    }

    public String getModuleName() {
        return moduleName;
    }

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
                ", parentGroupId=" + parentGroupId +
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
