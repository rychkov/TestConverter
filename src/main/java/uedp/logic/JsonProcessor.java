package uedp.logic;

import uedp.logic.parser.JsonParser;
import uedp.logic.writer.InfoConverter;

public class JsonProcessor extends DataProcessor {
    private static final JsonParser parser = new JsonParser();

    public JsonProcessor(String name, InfoConverter infoConverter) {
        super(name, parser, infoConverter);
    }
}
