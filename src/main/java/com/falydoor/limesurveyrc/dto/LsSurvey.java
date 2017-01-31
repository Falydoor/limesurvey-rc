package com.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

/**
 * Survey DTO.
 */
public class LsSurvey {
    @SerializedName("sid")
    private int id;

    @SerializedName("surveyls_title")
    private String title;

    private boolean active;

    @SerializedName("startdate")
    private LocalDate startDate;

    private LocalDate expires;

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
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets expires.
     *
     * @return the expires
     */
    public LocalDate getExpires() {
        return expires;
    }

    /**
     * Sets expires.
     *
     * @param expires the expires
     */
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
