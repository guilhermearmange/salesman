package com.salesman.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesman.configuration.Properties;
import com.salesman.model.DataAnalytics;
import com.salesman.model.ModelData;

@Service
public class SalesmanProcessorService {

	private SalesmanReaderService salesmanReaderService;
	private SalesmanDataAnalyticsService salesmanDataAnalyticsService;
	private SalesmanWriterService salesmanWriterService;
	private Properties properties;
	
	public SalesmanProcessorService(SalesmanReaderService salesmanReaderService,
			SalesmanDataAnalyticsService salesmanDataAnalyticsService,
			SalesmanWriterService salesmanWriterService,
			Properties properties) {
		this.salesmanReaderService = salesmanReaderService;
		this.salesmanDataAnalyticsService = salesmanDataAnalyticsService;
		this.salesmanWriterService = salesmanWriterService;
		this.properties = properties;
	}

	public void process(Path file) {
		List<ModelData> data = salesmanReaderService.readFile(file);
		DataAnalytics dataAnalytics = salesmanDataAnalyticsService.process(data);
		salesmanWriterService.write(getOutputPath(file), dataAnalytics);
	}

	private Path getOutputPath(Path file) {
		String fileName = file.getFileName().toString();
		fileName = fileName.replaceFirst(properties.getFileOutputRegex(), properties.getFileOutputReplace());
		return Paths.get(properties.getBasePath(), properties.getFileOutputFolder(), fileName);
	}
}
