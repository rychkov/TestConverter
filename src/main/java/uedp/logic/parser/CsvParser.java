package uedp.logic.parser;

import uedp.dto.InfoDto;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class CsvParser implements InfoParser {
    @Override
    public InfoDto parse(String line, String name, long lineNumber) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        StringJoiner errors = new StringJoiner(" | ");

        Long id = getLongValue(tokenizer, errors, "id");
        BigDecimal amount = getBigDecimalValue(tokenizer, errors, "amount");
        String currency = getStringValue(tokenizer, errors, "currency");
        String comment = getStringValue(tokenizer, errors, "comment");

        return new InfoDto(id, amount, currency, comment, name, lineNumber, errors.length() == 0 ? OK : errors.toString());
    }

    private Long getLongValue(StringTokenizer tokenizer, StringJoiner errors, String fieldName) {
        if (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                errors.add(fieldName + "=[" + value + "] is not a number");
            }
        } else {
            errors.add("no data field [" + fieldName + "]");
        }
        return null;
    }

    private BigDecimal getBigDecimalValue(StringTokenizer tokenizer, StringJoiner errors, String fieldName) {
        if (tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                errors.add(fieldName + "=[" + value + "] is not a number");
            }
        } else {
            errors.add("no data field [" + fieldName + "]");
        }
        return null;
    }

    private String getStringValue(StringTokenizer tokenizer, StringJoiner errors, String fieldName) {
        if (tokenizer.hasMoreTokens()) {
            return tokenizer.nextToken();
        } else {
            errors.add("no data field [" + fieldName + "]");
            return null;
        }
    }
}
