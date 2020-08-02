package com.salesman.service;

import com.salesman.configuration.Properties;
import com.salesman.converter.ConverterFactory;
import com.salesman.model.ModelData;
import org.springframework.stereotype.Service;

@Service
public class SalesmanReaderService {

	private static final int TYPE_POSITION = 0;
	
	private ConverterFactory converterFactory;
	private Properties properties;
	
	public SalesmanReaderService(ConverterFactory converterFactory, Properties properties) {
		this.converterFactory = converterFactory;
		this.properties = properties;
	}

	public ModelData readLine(String line) {
		String[] rawDataArray = line.split(properties.getSeparator());
		return converterFactory.getConverter(rawDataArray[TYPE_POSITION]).convertRawData(rawDataArray);
	}
	
	
}
