package com.salesman.service;

import com.salesman.model.Metric;
import com.salesman.stub.MetricStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SalesmanMetricServiceTest {

	private SalesmanMetricService salesmanMetricService;

	@Before
	public void setUp()  {
		salesmanMetricService = new SalesmanMetricService();
	}
	
	@Test
	public void mustWriteFile() {
		List<Metric> metrics = Arrays.asList(MetricStub.createOne());
		String rawText = salesmanMetricService.metricsToRawText(metrics);
		assertEquals("Test name: Test value", rawText);
	}
}
