package com.salesman.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.salesman.model.Sale;
import com.salesman.model.SaleItem;

public class SaleConverter  implements RawDataConverter<Sale> {

	private static final int SALE_ID_POSITION = 1;
	private static final int ITENS_POSITION = 2;
	private static final int SALESMAN_NAME_POSITION = 3;
	private static final String ITENS_SEPARATOR = ",";
	private static final String ITENS_ATTRIBUTS_SEPARATOR = "-";
	private static final int ITEM_ID_POSITION = 0;
	private static final int QUANTITY_POSITION = 1;
	private static final int PRICE_POSITION = 2;

	@Override
	public Sale convertRawData(String[] rawData) {
		Long id = Long.parseLong(rawData[SALE_ID_POSITION]);
		String rawItens = rawData[ITENS_POSITION];
		List<SaleItem> itens = convertToItens(rawItens);
		String salesmanName = rawData[SALESMAN_NAME_POSITION];
		return new Sale(id, itens, salesmanName);
	}

	private List<SaleItem> convertToItens(String rawItens) {
		return Arrays.stream(removeBrackets(rawItens).split(ITENS_SEPARATOR)).map(this::convertToItem).collect(Collectors.toList());
	}
	
	private SaleItem convertToItem(String rawItem) {
		String[] rawItemArray = rawItem.split(ITENS_ATTRIBUTS_SEPARATOR);
		long id = Long.parseLong(rawItemArray[ITEM_ID_POSITION]);
		double quantity = Double.parseDouble(rawItemArray[QUANTITY_POSITION]);
		double price = Double.parseDouble(rawItemArray[PRICE_POSITION]);
		return new SaleItem(id, quantity, price);
	}

	private String removeBrackets(String rawItens) {
		return rawItens.replaceAll("\\[|\\]", "");
	}
	
}
