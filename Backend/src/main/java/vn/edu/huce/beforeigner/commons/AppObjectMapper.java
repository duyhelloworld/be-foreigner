package vn.edu.huce.beforeigner.commons;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    }
    
    public String toJson(Object object) {
        try {
            return writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("Error when cast {} to JSON : {}", object.getClass().getSimpleName(), e.getMessage());
            return e.getMessage();
        }
    }
}
