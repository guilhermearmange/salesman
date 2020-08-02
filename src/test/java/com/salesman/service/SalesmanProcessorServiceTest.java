package com.salesman.service;

import com.salesman.configuration.Properties;
import com.salesman.model.Metric;
import com.salesman.service.analytics.SalesmanDataAnalyticsService;
import com.salesman.stub.MetricStub;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

public class SalesmanProcessorServiceTest {

	private static final String OUTPUT_REPLACE = "$1.done$2";
	private static final String OUTPUT_REGEX = "(.*)(\\.dat)";
	private static final String OUTPUT_FOLDER = "out";
	private static final String INPUT_FILENAME = "test.dat";
	private static final String BASE_PATH = "tmp";
	private static final long AMOUNT_OF_CLIENTS = 1;
	private static final long AMOUNT_OF_SALESMAN = 2;
	private static final String WORST_SALESMAN = "SALESMAN";
	private static final long MOST_EXPENSIVE_SALE = 3L;
	private static final String OUTPUT_FILENAME = "test.done.dat";
	private static final Path OUTPUT_FILE_PATH = Paths.get(BASE_PATH, OUTPUT_FOLDER, OUTPUT_FILENAME);
	
	private SalesmanProcessorService salesmanProcessorService;
	private SalesmanReaderService salesmanReaderService;
	private SalesmanDataAnalyticsService salesmanDataAnalyticsService;
	private SalesmanWriterService salesmanWriterService;
	private Properties properties;
	
	@Before
	public void setUp() {
		salesmanReaderService = mock(SalesmanReaderService.class);
		salesmanDataAnalyticsService = mock(SalesmanDataAnalyticsService.class);
		salesmanWriterService = mock(SalesmanWriterService.class);
		properties = mock(Properties.class);
		salesmanProcessorService = new SalesmanProcessorService(salesmanReaderService, salesmanDataAnalyticsService, salesmanWriterService, properties );
	}
	
	@Test
	public void mustProcessFile() {
		Path file = Paths.get(BASE_PATH, INPUT_FILENAME);
		List<Metric> metricList = Arrays.asList(MetricStub.createOne());

		when(properties.getBasePath()).thenReturn(BASE_PATH);
		when(properties.getFileOutputFolder()).thenReturn(OUTPUT_FOLDER);
		when(properties.getFileOutputRegex()).thenReturn(OUTPUT_REGEX);
		when(properties.getFileOutputReplace()).thenReturn(OUTPUT_REPLACE);
		when(salesmanReaderService.readFile(file)).thenReturn(emptyList());
		when(salesmanDataAnalyticsService.process(emptyList())).thenReturn(metricList);

		salesmanProcessorService.process(file);

		verify(properties, times(1)).getBasePath();
		verify(properties, times(1)).getFileOutputFolder();
		verify(properties, times(1)).getFileOutputRegex();
		verify(properties, times(1)).getFileOutputReplace();
		verify(salesmanReaderService, times(1)).readFile(file);
		verify(salesmanDataAnalyticsService, times(1)).process(Collections.emptyList());
		verify(salesmanWriterService, times(1)).write(OUTPUT_FILE_PATH, metricList);
	}
}
