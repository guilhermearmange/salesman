package com.salesman.service;

import com.salesman.configuration.Properties;
import com.salesman.converter.ConverterFactory;
import com.salesman.converter.SalesmanConverter;
import com.salesman.model.ModelData;
import com.salesman.model.Salesman;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

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

	@Before
	public void setUp() {
		converterFactory = mock(ConverterFactory.class);
		properties = mock(Properties.class);
		salesmanReaderService = new SalesmanReaderService(converterFactory, properties);
	}

	@Test
	public void readLine() {
		SalesmanConverter salesmanConverter = mock(SalesmanConverter.class);
		Salesman salesman = new Salesman(CPF, NAME, SALARY);
		when(salesmanConverter.convertRawData(SALESMAN_DATA_ARRAY)).thenReturn(salesman);
		when(properties.getSeparator()).thenReturn(SEPARATOR);
		when(converterFactory.getConverter(SALESMAN_TYPE)).thenAnswer((invocation) -> salesmanConverter);

		ModelData data = salesmanReaderService.readLine(SALESMAN_DATA);
		
		assertThat(data, instanceOf(Salesman.class));
		assertEquals(salesman, data);
		verify(converterFactory, times(1)).getConverter(SALESMAN_TYPE);
		verify(salesmanConverter, times(1)).convertRawData(SALESMAN_DATA_ARRAY);
	}
	
}
