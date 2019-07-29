package com.salesman.runner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import com.salesman.configuration.Properties;
import com.salesman.service.SalesmanProcessorService;

public class FileWatchServiceRunnerTest {
	
	private FileWatchServiceRunner fileWatchServiceRunner;
	private Properties properties;
	private SalesmanProcessorService salesmanProcessorService;
	private Path inputFolder;
	
	@Before
	public void setUp() throws IOException {
		salesmanProcessorService = mock(SalesmanProcessorService.class);
		properties = mock(Properties.class);
		fileWatchServiceRunner = new FileWatchServiceRunner(salesmanProcessorService, properties);
		inputFolder = Files.createTempDirectory("in");
	}
	
	@Test
	public void mustProcessFile() throws IOException, InterruptedException {
		when(properties.getBasePath()).thenReturn(inputFolder.getParent().toString());
		when(properties.getFileInputFolder()).thenReturn(inputFolder.getFileName().toString());
		when(properties.getFileInputMaxWaitTime()).thenReturn(100L);
		when(properties.getFileInputMatcher()).thenReturn("glob:*.dat");
		
		File file = File.createTempFile("tmp", "teste.dat",inputFolder.toFile());
		file.deleteOnExit();
		fileWatchServiceRunner.run();

		verify(salesmanProcessorService, times(1)).process(file.toPath());
	}
	
	@Test
	public void mustNotProcessInvalidFormatFiles() throws IOException, InterruptedException {
		when(properties.getBasePath()).thenReturn(inputFolder.getParent().toString());
		when(properties.getFileInputFolder()).thenReturn(inputFolder.getFileName().toString());
		when(properties.getFileInputMaxWaitTime()).thenReturn(100L);
		when(properties.getFileInputMatcher()).thenReturn("glob:*.dat");
		
		File file = File.createTempFile("tmp", "teste.datInvalid",inputFolder.toFile());
		file.deleteOnExit();
		fileWatchServiceRunner.run();

		verify(salesmanProcessorService, never()).process(file.toPath());
	}

}
