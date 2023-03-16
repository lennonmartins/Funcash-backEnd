package br.com.insted.funcash.utils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonUtil {
    
    // public static byte[] toJson(Object object) throws JsonProcessingException{
    //     ObjectMapper mapper = new ObjectMapper();
    //     mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    //     return mapper.writeValueAsBytes(object);
    // }

    public static <T> T mapFomJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
