package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.stub.MetricStub;
import com.salesman.stub.ModelDataStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SalesmanDataAnalyticsServiceTest {


    private AnalyticsMetricService analyticsMetricService;
    private List<AnalyticsMetricService> analyticsMetricServices;
	private SalesmanDataAnalyticsService salesmanDataAnalyticsService;

	@Before
	public void setUp() {
        analyticsMetricService = Mockito.mock(AnalyticsMetricService.class);
        analyticsMetricServices = Arrays.asList(analyticsMetricService);
		salesmanDataAnalyticsService = new SalesmanDataAnalyticsService(analyticsMetricServices);
	}

	@Test
	public void mustProcessData() {
		List<ModelData> data = ModelDataStub.createList();

		Metric metric = MetricStub.createOne();
		Mockito.when(analyticsMetricService.process(data)).thenReturn(metric);

		List<Metric> dataAnalytics = salesmanDataAnalyticsService.process(data);

		assertEquals(1, dataAnalytics.size());
		assertEquals(dataAnalytics.get(0), metric);
	}

}
