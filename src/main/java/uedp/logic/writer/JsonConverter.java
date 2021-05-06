package uedp.logic.writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uedp.dto.InfoDto;

public class JsonConverter implements InfoConverter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public String toString(InfoDto info) {
        try {
            return MAPPER.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
