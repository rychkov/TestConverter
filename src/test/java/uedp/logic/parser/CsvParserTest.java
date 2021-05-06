package uedp.logic.parser;

import org.junit.jupiter.api.Test;
import uedp.dto.InfoDto;

import java.math.BigDecimal;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {
    final String fileName = "f.name";
    final Long lineNumber = 999L;
    final Long id = 1L;
    final BigDecimal amount = BigDecimal.TEN;
    final String currency = "RUB";
    final String comment = "some info";

    @Test
    public void testOk() {
        CsvParser parser = new CsvParser();
        StringJoiner line = new StringJoiner(",");
        line.add(id.toString());
        line.add(amount.toString());
        line.add(currency);
        line.add(comment);

        InfoDto result = parser.parse(line.toString(), fileName, lineNumber);

        assertEquals(id, result.getId());
        assertEquals(amount, result.getAmount());
        assertEquals(currency, result.getCurrency());
        assertEquals(comment, result.getComment());
        assertEquals(InfoParser.OK, result.getResult());
        assertEquals(fileName, result.getFilename());
        assertEquals(lineNumber, result.getLine());
    }

    @Test
    public void testNanId() {
        CsvParser parser = new CsvParser();
        String fileName = "f.name";
        Long lineNumber = 999L;

        StringJoiner line = new StringJoiner(",");
        line.add("NaN");
        line.add(amount.toString());
        line.add(currency);
        line.add(comment);

        InfoDto result = parser.parse(line.toString(), fileName, lineNumber);

        assertNotEquals(InfoParser.OK, result.getResult());
    }

    @Test
    public void testNanAmount() {
        CsvParser parser = new CsvParser();
        String fileName = "f.name";
        Long lineNumber = 999L;

        StringJoiner line = new StringJoiner(",");
        line.add(id.toString());
        line.add("NaN");
        line.add(currency);
        line.add(comment);

        InfoDto result = parser.parse(line.toString(), fileName, lineNumber);

        assertNotEquals(InfoParser.OK, result.getResult());
    }

}
