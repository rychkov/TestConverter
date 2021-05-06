package uedp.logic.parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import uedp.dto.InfoDto;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class JsonParser implements InfoParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public InfoDto parse(String line, String name, long lineNumber) {
        StringJoiner errors = new StringJoiner(" | ");
        Dto dto = null;
        try {
            dto = MAPPER.readValue(line, Dto.class);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        return new InfoDto(dto == null ? null : dto.orderId,
                dto == null ? null : dto.amount,
                dto == null ? null : dto.currency,
                dto == null ? null : dto.comment,
                name,
                lineNumber,
                errors.length() == 0 ? OK : errors.toString()
        );
    }

    private static class Dto {
        @JsonProperty
        Long orderId;
        @JsonProperty
        BigDecimal amount;
        @JsonProperty
        String currency;
        @JsonProperty
        String comment;
    }
}
