package uedp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uedp.logic.CsvProcessor;
import uedp.logic.DataProcessor;
import uedp.logic.JsonProcessor;
import uedp.logic.writer.InfoConverter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ConverterApp implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(ConverterApp.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Autowired
    private InfoConverter infoConverter;

    public static void main(String[] args) {
        SpringApplication.run(ConverterApp.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
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
                    LOG.warn("No data processor for file {}", f);
                }
            } else if (!f.exists()) {
                LOG.warn("File [{}] not exist", f);
            } else if (f.isDirectory()) {
                LOG.warn("[{}] is directory", f);
            }
        }
        executorService.shutdown();
    }
}
