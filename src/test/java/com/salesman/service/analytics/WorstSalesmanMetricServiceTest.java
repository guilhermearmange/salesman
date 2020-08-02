package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.stub.ModelDataStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WorstSalesmanMetricServiceTest {
    private WorstSalesmanMetricService worstSalesmanMetricService;

    @Before
    public void setUp(){
        worstSalesmanMetricService = new WorstSalesmanMetricService();
    }

    @Test
    public void process() {
        List<ModelData> data = ModelDataStub.createList();

        Metric metric = worstSalesmanMetricService.process(data);

        assertEquals("Worst salesman", metric.getName());
        assertEquals("Salesman 2", metric.getValue());
    }

    @Test
    public void processNoContent() {

        Metric metric = worstSalesmanMetricService.process(Collections.emptyList());

        assertEquals("Worst salesman", metric.getName());
        assertEquals("Salesman not found", metric.getValue());
    }
}