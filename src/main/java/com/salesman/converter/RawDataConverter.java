package com.salesman.converter;

import com.salesman.model.ModelData;

public interface RawDataConverter<T extends ModelData> {

	T convertRawData(String[] rawData);
	
}
