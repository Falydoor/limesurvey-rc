package com.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Answer of a question DTO.
 */
public class LsQuestionAnswer {
    private String answer;

    @SerializedName("assessment_value")
    private int value;

    @SerializedName("scale_id")
    private int scaleId;

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets answer.
     *
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets scale id.
     *
     * @return the scale id
     */
    public int getScaleId() {
        return scaleId;
    }

    /**
     * Sets scale id.
     *
     * @param scaleId the scale id
     */
    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }

    @Override
    public String toString() {
        return "LsQuestionAnswer{" +
                "answer='" + answer + '\'' +
                ", value=" + value +
                ", scaleId=" + scaleId +
                '}';
    }
}
