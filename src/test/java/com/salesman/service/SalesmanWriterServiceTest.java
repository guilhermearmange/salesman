package com.salesman.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.salesman.model.DataAnalytics;

public class SalesmanWriterServiceTest {

	private static final String WORST_SALESMAN = "Salesman";
	private static final long MOST_EXPENSIVE_SALE = 1;
	private static final long AMOUNT_OF_SALESMAN = 2;
	private static final long AMOUNT_OF_CLIENTS = 3;

	private SalesmanWriterService salesmanWriterService;
	private File file;
	
	@Before
	public void setUp() throws IOException {
		salesmanWriterService = new SalesmanWriterService();
		file = File.createTempFile("tmp", "FileWritingTest");
        file.deleteOnExit();
	}
	
	@Test
	public void mustWriteFile() throws IOException {
		Path path = file.toPath();
		salesmanWriterService.write(path, new DataAnalytics(AMOUNT_OF_CLIENTS,AMOUNT_OF_SALESMAN, WORST_SALESMAN, MOST_EXPENSIVE_SALE));
		
		List<String> lines = Files.lines(path).collect(Collectors.toList());
		assertEquals("Amount of Clients: " + AMOUNT_OF_CLIENTS, lines.get(0));
		assertEquals("Amount of Salesman: " + AMOUNT_OF_SALESMAN, lines.get(1));
		assertEquals("Most Expensive Sale: " + MOST_EXPENSIVE_SALE, lines.get(2));
		assertEquals("Worst Salesman: " + WORST_SALESMAN, lines.get(3));
	}
}
