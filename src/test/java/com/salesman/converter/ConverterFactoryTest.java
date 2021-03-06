package com.salesman.converter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConverterFactoryTest {

	private ConverterFactory converterFactory;
	private SalesmanConverter salesmanConverter;
	private CustomerConverter customerConverter;
	private SaleConverter saleConverter;
	
	private static final String SALESMAN_TYPE = "001";
	private static final String CUSTOMER_TYPE = "002";
	private static final String SALE_TYPE = "003";
	private static final String INVALID_TYPE = "004";

	@Before
	public void setUp() {
		salesmanConverter = Mockito.mock(SalesmanConverter.class);
		customerConverter = Mockito.mock(CustomerConverter.class);
		saleConverter = Mockito.mock(SaleConverter.class);
		converterFactory = new ConverterFactory(salesmanConverter, customerConverter, saleConverter);
	}
	
	@Test
	public void mustCreateSalesmanConverter() {
		RawDataConverter<?> converter = converterFactory.getConverter(SALESMAN_TYPE);
		assertThat(converter, instanceOf(SalesmanConverter.class));
	}
	
	@Test
	public void mustCreateCustomerConverter() {
		RawDataConverter<?> converter = converterFactory.getConverter(CUSTOMER_TYPE);
		assertThat(converter, instanceOf(CustomerConverter.class));
	}

	@Test
	public void mustCreateSaleConverter() {
		RawDataConverter<?> converter = converterFactory.getConverter(SALE_TYPE);
		assertThat(converter, instanceOf(SaleConverter.class));
	}
	
	@Test
	public void mustReturnNullConverter() {
		RawDataConverter<?> converter = converterFactory.getConverter(INVALID_TYPE);
		assertNull(converter);
	}
}
