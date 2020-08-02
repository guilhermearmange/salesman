package com.salesman.converter;

import com.salesman.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements RawDataConverter<Customer> {

	private static final int CNPJ_POSITION = 1;
	private static final int NAME_POSITION = 2;
	private static final int BUSINESS_AREA_POSITION = 3;

	@Override
	public Customer convertRawData(String[] rawData) {
		String cnpj = rawData[CNPJ_POSITION];
		String name = rawData[NAME_POSITION];
		String businessArea = rawData[BUSINESS_AREA_POSITION];
		return new Customer(cnpj, name, businessArea);
	}

}
