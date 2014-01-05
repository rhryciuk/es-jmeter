package org.rhr.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonSerializer {

    private final ObjectMapper objectMapper;


    public JsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public <T> String serialize(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Object json serialization failed : " + object);
        }
    }

    public <T> T deserialize(final String serializedObject, Class<T> clazz) {
        try {
            return objectMapper.readValue(serializedObject, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Object deserialization failed: " + serializedObject);
        }
    }

}
