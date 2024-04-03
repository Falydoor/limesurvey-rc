package com.github.falydoor.limesurveyrc.dto.json;


import java.lang.reflect.Type;
import java.time.LocalDate;

import com.github.falydoor.limesurveyrc.dto.LsResponse;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * The type Ls response deserializer.
 *  
 */
public class LsResponseDeserializer implements JsonDeserializer<LsResponse> {
    @Override
    public LsResponse deserialize(JsonElement jsonElement, Type type, 
    		JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    	LsResponse response = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create().fromJson(jsonElement, type);
    	return response;
    }
}
