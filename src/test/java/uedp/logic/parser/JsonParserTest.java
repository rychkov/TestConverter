package uedp.logic.parser;

import org.junit.jupiter.api.Test;
import uedp.dto.InfoDto;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    final String fileName = "f.name";
    final Long lineNumber = 999L;
    final Long id = 1L;
    final BigDecimal amount = BigDecimal.TEN;
    final String currency = "RUB";
    final String comment = "some info";

    @Test
    public void testOk() {
        JsonParser parser = new JsonParser();
        String line = getLine(id.toString(), amount.toString(), currency, comment);

        InfoDto result = parser.parse(line, fileName, lineNumber);

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
        JsonParser parser = new JsonParser();
        String line = getLine("id", amount.toString(), currency, comment);

        InfoDto result = parser.parse(line, fileName, lineNumber);

        assertNotEquals(InfoParser.OK, result.getResult());
    }

    @Test
    public void testNanAmount() {
        JsonParser parser = new JsonParser();
        String line = getLine(id.toString(), "amount", currency, comment);

        InfoDto result = parser.parse(line, fileName, lineNumber);

        assertNotEquals(InfoParser.OK, result.getResult());
    }

    private static String getLine(String id, String amount, String currency, String comment) {
        return "{\"orderId\":" + id +
               ",\"amount\":" + amount +
               ",\"currency\":\"" + currency + "\"" +
               ",\"comment\":\"" + comment + "\"}";
    }
}
