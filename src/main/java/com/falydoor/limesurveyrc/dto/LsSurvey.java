package com.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class LsSurvey {
    @SerializedName("sid")
    private int id;

    @SerializedName("surveyls_title")
    private String title;

    private boolean active;

    @SerializedName("startdate")
    private LocalDate startDate;

    private LocalDate expires;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "LsSurvey{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", active=" + active +
                ", startDate=" + startDate +
                ", expires=" + expires +
                '}';
    }
}
