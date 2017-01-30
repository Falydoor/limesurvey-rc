package com.falydoor.limesurveyrest.dto.json;

import com.falydoor.limesurveyrest.dto.LsSurvey;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LsSurveyDeserializer implements JsonDeserializer<LsSurvey> {
    @Override
    public LsSurvey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LsSurvey survey = new Gson().fromJson(jsonElement, type);
        survey.setActive("Y".equals(jsonElement.getAsJsonObject().get("active").getAsString()));
        return survey;
    }
}
