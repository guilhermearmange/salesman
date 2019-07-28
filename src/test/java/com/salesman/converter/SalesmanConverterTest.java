package com.salesman.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.salesman.model.Salesman;

public class SalesmanConverterTest {

	private static final String[] DATA = new String[] {"001", "3245678865434", "Renato", "40000.99"};
	private static final Object CPF = "3245678865434";
	private static final Object NAME = "Renato";
	private static final Object SALARY = 40000.99;
	
	private SalesmanConverter salesmanConverter;

	@Before
	public void setUp() {
		salesmanConverter = new SalesmanConverter();
	}
	
	@Test
	public void mustConvertToSalesman() {
		Salesman salesman = salesmanConverter.convertRawData(DATA);
		assertEquals(CPF, salesman.getCpf());
		assertEquals(NAME, salesman.getName());
		assertEquals(SALARY, salesman.getSalary());
	}
	
}
