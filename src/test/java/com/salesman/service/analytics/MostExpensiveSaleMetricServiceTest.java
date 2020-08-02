package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.stub.ModelDataStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MostExpensiveSaleMetricServiceTest {

    private MostExpensiveSaleMetricService mostExpensiveSaleMetricService;

    @Before
    public void setUp(){
        mostExpensiveSaleMetricService = new MostExpensiveSaleMetricService();
    }

    @Test
    public void process() {
        List<ModelData> data = ModelDataStub.createList();

        Metric metric = mostExpensiveSaleMetricService.process(data);

        assertEquals("Most Expensive Sale", metric.getName());
        assertEquals("4", metric.getValue());
    }

    @Test
    public void processNoContent() {

        Metric metric = mostExpensiveSaleMetricService.process(Collections.emptyList());

        assertEquals("Most Expensive Sale", metric.getName());
        assertEquals("Sale not found", metric.getValue());
    }
}