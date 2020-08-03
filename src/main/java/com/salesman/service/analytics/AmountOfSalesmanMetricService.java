package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import com.salesman.model.Salesman;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountOfSalesmanMetricService implements AnalyticsMetricService {
    private final static String METRIC_NAME = "Amount of Salesman";

    @Override
    public Metric process(List<ModelData> data) {
        return new Metric(METRIC_NAME, String.valueOf(data.stream().filter(Salesman.class::isInstance).count()));
    }
}
