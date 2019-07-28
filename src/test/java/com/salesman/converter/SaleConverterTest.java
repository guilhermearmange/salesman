package com.salesman.converter;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.salesman.model.Sale;
import com.salesman.model.SaleItem;

public class SaleConverterTest {

	private static final String[] DATA = new String[] {"003", "10", "[1-10-100,2-30-2.50]", "Diego"};
	private static final long SALE_ID = 10L;
	private static final String SALESMAN_NAME = "Diego";
	private static final long ITEM_1_ID = 1L;
	private static final double ITEM_1_PRICE = 100.0;
	private static final double ITEM_1_QUANTITY = 10.0;
	private static final long ITEM_2_ID = 2L;
	private static final double ITEM_2_PRICE = 2.5;
	private static final double ITEM_2_QUANTITY = 30.0;
	private static final double DELTA = 0.0;
	private SaleConverter saleConverter;
	
	@Before
	public void setUp() {
		saleConverter = new SaleConverter();
	}
	
	@Test
	public void mustConvertToSale() {
		Sale sale = saleConverter.convertRawData(DATA);
		
		assertEquals(SALE_ID, sale.getId());
		assertEquals(SALESMAN_NAME, sale.getSalesmanName());
		
		List<SaleItem> itens = sale.getItens();
		assertEquals(2, itens.size());
		
		SaleItem item1 = itens.get(0);
		assertEquals(ITEM_1_ID, item1.getId());
		assertEquals(ITEM_1_PRICE, item1.getPrice(), DELTA);
		assertEquals(ITEM_1_QUANTITY, item1.getQuantity(), DELTA);
		
		SaleItem item2 = itens.get(1);
		assertEquals(ITEM_2_ID, item2.getId());
		assertEquals(ITEM_2_PRICE, item2.getPrice(), DELTA);
		assertEquals(ITEM_2_QUANTITY, item2.getQuantity(), DELTA);
	}
	
}
