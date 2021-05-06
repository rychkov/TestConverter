package uedp.logic;

import uedp.dto.InfoDto;
import uedp.logic.parser.InfoParser;
import uedp.logic.writer.InfoConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class DataProcessor implements Runnable {
    private final String name;
    private final InfoParser parser;
    private final InfoConverter infoConverter;

    public DataProcessor(String name, InfoParser parser, InfoConverter infoConverter) {
        this.name = name;
        this.parser = parser;
        this.infoConverter = infoConverter;
    }

    @Override
    public void run() {
        try (FileReader fileReader = new FileReader(name)) {
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line = reader.readLine();
                long lineNumber = 1;
                while (line != null) {
                    InfoDto info = parser.parse(line, name, lineNumber);
                    System.out.println(infoConverter.toString(info));
                    line = reader.readLine();
                    lineNumber++;
                }
            } catch (IOException e) {
                // ignore
            }
        } catch (IOException e) {
            // ignore
        }
    }
}
