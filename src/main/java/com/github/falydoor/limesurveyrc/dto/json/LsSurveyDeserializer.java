package com.github.falydoor.limesurveyrc.dto.json;

import com.github.falydoor.limesurveyrc.dto.LsSurvey;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * The type Ls survey deserializer.
 *  * A deserializer is used for converting the Y/N values to a boolean.
 */
public class LsSurveyDeserializer implements JsonDeserializer<LsSurvey> {
    @Override
    public LsSurvey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LsSurvey survey = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create().fromJson(jsonElement, type);
        survey.setActive("Y".equals(jsonElement.getAsJsonObject().get("active").getAsString()));
        return survey;
    }
}
