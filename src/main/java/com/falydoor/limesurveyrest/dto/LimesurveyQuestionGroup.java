package com.falydoor.limesurveyrest.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LimesurveyQuestionGroup {
    private int id;

    private int order;

    public LimesurveyQuestionGroup(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        id = jsonObject.get("gid").getAsInt();
        order = jsonObject.get("group_order").getAsInt();
    }

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

    @Override
    public String toString() {
        return "LimesurveyQuestionGroup{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }
}
