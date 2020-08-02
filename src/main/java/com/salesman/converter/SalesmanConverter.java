package com.salesman.converter;

import com.salesman.model.Salesman;
import org.springframework.stereotype.Component;

@Component
public class SalesmanConverter implements RawDataConverter<Salesman> {

	private static final int CPF_POSITION = 1;
	private static final int NAME_POSITION = 2;
	private static final int SALARY = 3;

	@Override
	public Salesman convertRawData(String[] rawData) {
		String cpf = rawData[CPF_POSITION];
		String name = rawData[NAME_POSITION];
		Double salary = Double.parseDouble(rawData[SALARY]);
		return new Salesman(cpf, name, salary);
	}

}
