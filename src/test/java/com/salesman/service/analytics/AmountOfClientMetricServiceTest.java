package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.stub.ModelDataStub;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AmountOfClientMetricServiceTest {
    private AmountOfClientMetricService amountOfClientMetricService;

    @Before
    public void setUp() {
        amountOfClientMetricService = new AmountOfClientMetricService();
    }

    @Test
    public void process() {
        List<ModelData> data = ModelDataStub.createList();

        Metric metric = amountOfClientMetricService.process(data);

        assertEquals("Amount of Clients", metric.getName());
        assertEquals("1", metric.getValue());
    }
}