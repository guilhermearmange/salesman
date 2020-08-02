package com.salesman.service;

import com.salesman.exception.SalesmanException;
import com.salesman.model.Metric;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesmanWriterService {

    public void write(Path file, List<Metric> metrics) {
        try {
            String fileText = metrics.stream()
                    .map(metric -> toLine(metric.getName(), metric.getValue()))
                    .collect(Collectors.joining(System.lineSeparator()));

            FileWriter fileWriter = getFileWriter(file);
            fileWriter.write(fileText);
            fileWriter.close();
        } catch (IOException e) {
            throw new SalesmanException("Unable to write file: " + file, e);
        }
    }

    private FileWriter getFileWriter(Path path) throws IOException {
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        FileWriter fileWriter = new FileWriter(path.toFile());
        return fileWriter;
    }

    private String toLine(String metricName, String metricValue) {
        return metricName.concat(": ").concat(metricValue);
    }

}
