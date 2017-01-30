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

    @Override
    public String toString() {
        return "LsQuestion{" +
                "question='" + question + '\'' +
                ", id=" + id +
                ", groupId=" + groupId +
                ", order=" + order +
                ", type='" + type + '\'' +
                ", relevance='" + relevance + '\'' +
                '}';
    }
}
