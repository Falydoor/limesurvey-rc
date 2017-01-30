package com.falydoor.limesurveyrest.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LimesurveyQuestion {
    private String question;

    private int id;

    private int groupId;

    private int order;

    private String type;

    private String relevance;

    public LimesurveyQuestion(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        id = jsonObject.get("qid").getAsInt();
        groupId = jsonObject.get("gid").getAsInt();
        question = jsonObject.get("question").getAsString();
        order = jsonObject.get("question_order").getAsInt();
        type = jsonObject.get("type").getAsString();
        relevance = jsonObject.get("relevance").getAsString();
    }

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
        return "LimesurveyQuestion{" +
                "question='" + question + '\'' +
                ", id=" + id +
                ", groupId=" + groupId +
                ", order=" + order +
                ", type='" + type + '\'' +
                ", relevance='" + relevance + '\'' +
                '}';
    }
}
