package com.salesman.service;

import com.salesman.stub.MetricStub;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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
		salesmanWriterService.write(path, Arrays.asList(MetricStub.createOne()));

		List<String> lines = Files.lines(path).collect(Collectors.toList());
		assertEquals(1, lines.size());
		assertEquals("Test name: Test value", lines.get(0));
	}
}
