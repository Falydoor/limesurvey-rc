package com.falydoor.limesurveyrc.exception;

public class LimesurveyRCException extends Throwable {
    public LimesurveyRCException(String msg) {
        super(msg);
    }

    public LimesurveyRCException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
