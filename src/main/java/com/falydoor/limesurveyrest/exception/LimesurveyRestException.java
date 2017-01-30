package com.falydoor.limesurveyrest.exception;

public class LimesurveyRestException extends Throwable {
    public LimesurveyRestException(String msg) {
        super(msg);
    }

    public LimesurveyRestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
