package uedp.logic.parser;

import uedp.dto.InfoDto;

public interface InfoParser {
    String OK = "OK";

    InfoDto parse(String line, String name, long lineNumber);
}
