package vn.edu.huce.beforeigner.commons;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppObjectMapper extends ObjectMapper {
    
    public AppObjectMapper() {
        super.registerModules(new JavaTimeModule());
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.USE_LONG_FOR_INTS, true); 
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(SerializationFeature.INDENT_OUTPUT, true);
        
        setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }
    
    public String toJson(Object object) {
        try {
            return writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Error when cast {} to JSON : {}", object.getClass().getSimpleName(), e.getMessage());
            return e.getMessage();
        }
    }

    public Map<String, String> readJson(String json) {
        try {
            return readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (JsonMappingException e) {
            log.info("Error when read '{}'' to Map : {}", json, e.getMessage());
            return null;
        } catch (JsonProcessingException e) {
            log.info("Error when read '{}'' to Map : {}", json, e.getMessage());
            return null;
        }
    }
}
