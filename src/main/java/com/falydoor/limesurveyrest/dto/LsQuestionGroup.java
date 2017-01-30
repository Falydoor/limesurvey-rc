package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

public class LsQuestionGroup {
    @SerializedName("gid")
    private int id;

    @SerializedName("group_order")
    private int order;

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
        return "LsQuestionGroup{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }
}
