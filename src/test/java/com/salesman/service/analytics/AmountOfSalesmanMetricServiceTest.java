package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.stub.ModelDataStub;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AmountOfSalesmanMetricServiceTest {
    private AmountOfSalesmanMetricService amountOfSalesmanMetricService;

    @Before
    public void setUp() {
        amountOfSalesmanMetricService = new AmountOfSalesmanMetricService();
    }

    @Test
    public void process() {
        List<ModelData> data = ModelDataStub.createList();

        Metric metric = amountOfSalesmanMetricService.process(data);

        assertEquals("Amount of Salesman", metric.getName());
        assertEquals("2", metric.getValue());
    }
}