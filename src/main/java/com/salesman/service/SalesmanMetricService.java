package com.salesman.service;

import com.salesman.model.Metric;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesmanMetricService {

    public String metricsToRawText(List<Metric> metrics) {
           return metrics.stream()
                    .map(metric -> toLine(metric.getName(), metric.getValue()))
                    .collect(Collectors.joining(System.lineSeparator()));
    }

    private String toLine(String metricName, String metricValue) {
        return metricName.concat(": ").concat(metricValue);
    }

}
