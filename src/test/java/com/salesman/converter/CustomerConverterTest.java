package com.salesman.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.salesman.model.Customer;

public class CustomerConverterTest {

	private static final String[] DATA = new String[] {"002","345675434544345", "Jose da Silva", "Rural"};
	private static final String CNPJ = "345675434544345";
	private static final String NAME = "Jose da Silva";
	private static final String BUSINESS_AREA = "Rural";
	
	private CustomerConverter customerConverter;
	
	@Before
	public void setUp() {
		customerConverter = new CustomerConverter();
	}
	
	@Test
	public void mustConvertToCustomer() {
		Customer customer = customerConverter.convertRawData(DATA);
		assertEquals(CNPJ, customer.getCnpj());
		assertEquals(NAME, customer.getName());
		assertEquals(BUSINESS_AREA, customer.getBusinessArea());
	}
	
}
