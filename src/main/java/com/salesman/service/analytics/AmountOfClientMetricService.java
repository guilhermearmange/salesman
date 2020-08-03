package com.salesman.service.analytics;

import com.salesman.model.Customer;
import com.salesman.model.Metric;
import com.salesman.model.ModelData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountOfClientMetricService implements AnalyticsMetricService {
    private final static String METRIC_NAME = "Amount of Clients";

    @Override
    public Metric process(List<ModelData> data) {
        return new Metric(METRIC_NAME, String.valueOf(data.stream().filter(Customer.class::isInstance).count()));
    }
}
