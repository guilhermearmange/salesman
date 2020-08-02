package com.salesman.converter;

import com.salesman.model.ModelData;
import org.springframework.stereotype.Component;

@Component
public class ConverterFactory {

	private SalesmanConverter salesmanConverter;
	private CustomerConverter customerConverter;
	private SaleConverter saleConverter;

	public ConverterFactory(SalesmanConverter salesmanConverter, CustomerConverter customerConverter, SaleConverter saleConverter) {
		this.salesmanConverter = salesmanConverter;
		this.customerConverter = customerConverter;
		this.saleConverter = saleConverter;
	}

	public RawDataConverter<? extends ModelData> getConverter(String rawData) {
		switch (rawData) {
		case "001":
			return salesmanConverter;
		case "002":
			return customerConverter;
		case "003":
			return saleConverter;
		}
		return null;
	}

}
