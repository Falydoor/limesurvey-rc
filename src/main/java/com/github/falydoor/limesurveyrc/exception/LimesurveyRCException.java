package com.github.falydoor.limesurveyrc.exception;

/**
 * The type Limesurvey rc exception.
 */
public class LimesurveyRCException extends Throwable {
    /**
     * Instantiates a new Limesurvey rc exception.
     *
     * @param msg the msg
     */
    public LimesurveyRCException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Limesurvey rc exception.
     *
     * @param msg       the msg
     * @param throwable the throwable
     */
    public LimesurveyRCException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
