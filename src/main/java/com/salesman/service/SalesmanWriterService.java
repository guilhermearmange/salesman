package com.salesman.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.salesman.exception.SalesmanException;
import com.salesman.model.DataAnalytics;

@Service
public class SalesmanWriterService {

	public void write(Path file, DataAnalytics data)  {
		try{
			FileWriter fileWriter = getFileWriter(file);
			appendLine(fileWriter, "Amount of Clients: ", data.getAmountOfClients());
			appendLine(fileWriter, "Amount of Salesman: ", data.getAmountOfSalesman());
			appendLine(fileWriter, "Most Expensive Sale: ", data.getMostExpensiveSale());
			appendLine(fileWriter, "Worst Salesman: ", data.getWorstSalesman());
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

	private void appendLine(FileWriter fileWriter, String property, Object value) throws IOException {
		fileWriter.append(property);
		fileWriter.append(String.valueOf(value));
		fileWriter.append(System.lineSeparator());
	}
	
}
