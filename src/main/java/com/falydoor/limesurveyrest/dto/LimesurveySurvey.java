package com.falydoor.limesurveyrest.dto;

import com.google.gson.annotations.SerializedName;

public class LimesurveySurvey {
    @SerializedName("sid")
    private int id;

    @SerializedName("surveyls_title")
    private String title;

    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "LimesurveySurvey{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", active=" + active +
                '}';
    }
}
