package uedp.logic;

import uedp.logic.parser.CsvParser;
import uedp.logic.writer.InfoConverter;

public class CsvProcessor extends DataProcessor {
    private static final CsvParser parser = new CsvParser();

    public CsvProcessor(String name, InfoConverter infoConverter) {
        super(name, parser, infoConverter);
    }
}
