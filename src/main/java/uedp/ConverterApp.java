package uedp;

import uedp.logic.CsvProcessor;
import uedp.logic.DataProcessor;
import uedp.logic.JsonProcessor;
import uedp.logic.writer.InfoConverter;
import uedp.logic.writer.JsonConverter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConverterApp {
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        InfoConverter infoConverter = new JsonConverter();
        for (String name : args) {
            File f = new File(name);
            if (f.exists() && f.isFile()) {
                DataProcessor processor = null;
                if (name.toLowerCase().endsWith(".csv")) {
                    processor = new CsvProcessor(name, infoConverter);
                } else if (name.toLowerCase().endsWith(".json")) {
                    processor = new JsonProcessor(name, infoConverter);
                }

                if (processor != null) {
                    executorService.submit(processor);
                } else {
                    // TODO log no processor
                }
            }
        }
        executorService.shutdown();
    }
}
