package com.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

public class LsAnswer {
    private String answer;

    @SerializedName("assessment_value")
    private int value;

    @SerializedName("scale_id")
    private int scaleId;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    @Override
    public String toString() {
        return "LsAnswer{" +
                "answer='" + answer + '\'' +
                ", value=" + value +
                ", scaleId=" + scaleId +
                '}';
    }
}
