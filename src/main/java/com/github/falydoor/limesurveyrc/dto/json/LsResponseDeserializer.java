package com.github.falydoor.limesurveyrc.dto.json;


import com.github.falydoor.limesurveyrc.dto.LsResponse;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * The type Ls response deserializer.
 */
public class LsResponseDeserializer implements JsonDeserializer<LsResponse> {

    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .create();

    @Override
    public LsResponse deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return gson.fromJson(jsonElement, type);
    }
}
