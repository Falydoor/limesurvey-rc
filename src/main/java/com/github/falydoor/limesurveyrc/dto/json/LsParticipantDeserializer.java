package com.github.falydoor.limesurveyrc.dto.json;

import com.github.falydoor.limesurveyrc.dto.LsParticipant;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * The type Ls participant deserializer.
 * A deserializer is used for getting participant informations.
 */
public class LsParticipantDeserializer implements JsonDeserializer<LsParticipant> {
    @Override
    public LsParticipant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        LsParticipant participant = new Gson().fromJson(jsonElement, type);
        JsonObject participantInfo = jsonElement.getAsJsonObject().getAsJsonObject("participant_info");
        participant.setEmail(participantInfo.get("email").getAsString());
        participant.setFirstName(participantInfo.get("firstname").getAsString());
        participant.setLastName(participantInfo.get("lastname").getAsString());
        return participant;
    }
}
