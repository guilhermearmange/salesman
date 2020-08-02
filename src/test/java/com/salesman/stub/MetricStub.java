package com.salesman.stub;

import com.salesman.model.Metric;

public class MetricStub {

    public static Metric createOne(){
        return new Metric("Test name", "Test value");
    }
}
