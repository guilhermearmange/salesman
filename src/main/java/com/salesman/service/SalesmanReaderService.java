package com.salesman.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesman.configuration.Properties;
import com.salesman.converter.ConverterFactory;
import com.salesman.exception.SalesmanException;
import com.salesman.model.ModelData;

@Service
public class SalesmanReaderService {

	private static final int TYPE_POSITION = 0;
	
	private ConverterFactory converterFactory;
	private Properties properties;
	
	public SalesmanReaderService(ConverterFactory converterFactory, Properties properties) {
		this.converterFactory = converterFactory;
		this.properties = properties;
	}

	public List<ModelData> readFile(Path file) {
		try {
			return Files.lines(file).parallel().map(rawData -> {
				String[] rawDataArray = rawData.split(properties.getSeparator());
				return converterFactory.getConverter(rawDataArray[TYPE_POSITION]).convertRawData(rawDataArray);
			}).collect(Collectors.toList());
		} catch (IOException e) {
			throw new SalesmanException("Unable to read file: " + file, e);
		}
	}
	
	
}
