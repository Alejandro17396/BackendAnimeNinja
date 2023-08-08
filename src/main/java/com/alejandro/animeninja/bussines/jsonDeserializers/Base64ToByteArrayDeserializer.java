package com.alejandro.animeninja.bussines.jsonDeserializers;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class Base64ToByteArrayDeserializer extends JsonDeserializer<byte[]> {

    @Override
    public byte[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String base64 = jp.getValueAsString();
        if(base64!= null) {
        	return Base64.getDecoder().decode(base64);
        }
        return null;
    }
}


