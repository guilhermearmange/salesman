package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesmanDataAnalyticsService {

    private List<AnalyticsMetricService> analyticsMetricServices;

    public SalesmanDataAnalyticsService(List<AnalyticsMetricService> analyticsMetricServices) {
        this.analyticsMetricServices = analyticsMetricServices;
    }

    public List<Metric> process(List<ModelData> data) {
        return analyticsMetricServices.stream()
                .map(service -> service.process(data))
                .collect(Collectors.toList());
    }
}
