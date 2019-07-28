package com.salesman.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.salesman.configuration.Properties;
import com.salesman.converter.ConverterFactory;
import com.salesman.converter.SalesmanConverter;
import com.salesman.model.ModelData;
import com.salesman.model.Salesman;

public class SalesmanReaderServiceTest {

	private static final String SEPARATOR = "ç";
	private static final String SALESMAN_DATA = "001ç1234567891234çDiegoç50000";
	private static final String[] SALESMAN_DATA_ARRAY = SALESMAN_DATA.split(SEPARATOR);
	private static final String SALESMAN_TYPE = "001";
	private static final String CPF = "CPF";
	private static final String NAME = "NOME";
	private static final double SALARY = 10;
	
	private SalesmanReaderService salesmanReaderService;
	private Properties properties;
	private ConverterFactory converterFactory;
	private File file;
	
	@Before
	public void setUp() throws IOException {
		converterFactory = mock(ConverterFactory.class);
		properties = mock(Properties.class);
		file = createFile();
		salesmanReaderService = new SalesmanReaderService(converterFactory, properties);
	}

	
	@Test
	public void mustReadFile() {
		SalesmanConverter salesmanConverter = mock(SalesmanConverter.class);
		Salesman salesman = new Salesman(CPF, NAME, SALARY);
		when(salesmanConverter.convertRawData(SALESMAN_DATA_ARRAY)).thenReturn(salesman);
		when(properties.getSeparator()).thenReturn(SEPARATOR);
		when(converterFactory.getConverter(SALESMAN_TYPE)).thenAnswer((invocation) -> salesmanConverter);

		List<ModelData> data = salesmanReaderService.readFile(file.toPath());
		
		assertEquals(1, data.size());
		assertThat(data.get(0), instanceOf(Salesman.class));
		assertEquals(salesman, (Salesman) data.get(0));
		verify(properties, times(1)).getSeparator();
		verify(converterFactory, times(1)).getConverter(SALESMAN_TYPE);
		verify(salesmanConverter, times(1)).convertRawData(SALESMAN_DATA_ARRAY);
	}
	
	private File createFile() throws IOException {
		File file = File.createTempFile("tmp", "FileWritingTest");
		file.deleteOnExit();
		Files.write(file.toPath(), SALESMAN_DATA.getBytes());
		return file;
	}
}
