package com.falydoor.limesurveyrest.dto.json;

import com.falydoor.limesurveyrest.dto.LimesurveySurvey;
import com.google.gson.*;

import java.lang.reflect.Type;

public class LimesurveySurveyDeserializer implements JsonDeserializer<LimesurveySurvey> {
    @Override
    public LimesurveySurvey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LimesurveySurvey survey = new Gson().fromJson(jsonElement, type);
        survey.setActive("Y".equals(jsonElement.getAsJsonObject().get("active").getAsString()));
        return survey;
    }
}
