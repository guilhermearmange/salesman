package com.salesman.service.analytics;

import com.salesman.model.Metric;
import com.salesman.model.ModelData;

import java.util.List;

public interface AnalyticsMetricService {

    Metric process(List<ModelData> data);
}
