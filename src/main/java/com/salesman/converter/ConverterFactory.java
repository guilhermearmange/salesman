package com.salesman.converter;

import org.springframework.stereotype.Component;

import com.salesman.model.ModelData;

@Component
public class ConverterFactory {

	public RawDataConverter<? extends ModelData> getConverter(String rawData) {
		switch (rawData) {
		case "001":
			return new SalesmanConverter();
		case "002":
			return new CustomerConverter();
		case "003":
			return new SaleConverter();
		}
		return null;
	}

}
