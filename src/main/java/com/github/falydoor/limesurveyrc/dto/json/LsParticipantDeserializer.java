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
        ParticipantInfo participantInfo = new Gson().fromJson(jsonElement.getAsJsonObject().getAsJsonObject("participant_info"), ParticipantInfo.class);
        participant.setEmail(participantInfo.getEmail());
        participant.setFirstName(participantInfo.getFirstname());
        participant.setLastName(participantInfo.getLastname());
        return participant;
    }

    private class ParticipantInfo {
        private String email;

        private String firstname;

        private String lastname;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
    }
}
