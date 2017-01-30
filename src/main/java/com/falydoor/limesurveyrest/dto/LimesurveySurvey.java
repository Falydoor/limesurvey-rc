package com.falydoor.limesurveyrest.dto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LimesurveySurvey {
    private int id;

    private String title;

    private boolean active;

    public LimesurveySurvey(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        id = jsonObject.get("sid").getAsInt();
        title = jsonObject.get("surveyls_title").getAsString();
        active = "Y".equals(jsonObject.get("active").getAsString());
    }

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
