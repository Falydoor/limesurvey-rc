package com.falydoor.limesurveyrc.dto.json;

import com.falydoor.limesurveyrc.dto.LsQuestion;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LsQuestionDeserializer implements JsonDeserializer<LsQuestion> {
    @Override
    public LsQuestion deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LsQuestion question = new Gson().fromJson(jsonElement, type);
        question.setMandatory("Y".equals(jsonElement.getAsJsonObject().get("mandatory").getAsString()));
        question.setOther("Y".equals(jsonElement.getAsJsonObject().get("other").getAsString()));
        return question;
    }
}
