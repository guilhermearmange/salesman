package com.salesman.service.analytics;

import com.salesman.model.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorstSalesmanMetricService implements AnalyticsMetricService {
    private final static double DEFAULT_PRICE_VALUE = 0.0;
    private final static String METRIC_NAME = "Worst salesman";
    private final static String METRIC_DEFAULT_VALUE = "Salesman not found";

    @Override
    public Metric process(List<ModelData> data) {
        Map<String, List<Sale>> salesmanSales = data.stream()
                .filter(Sale.class::isInstance)
                .map(Sale.class::cast)
                .collect(Collectors.groupingBy(Sale::getSalesmanName));

        return data.stream().filter(Salesman.class::isInstance)
                .map(Salesman.class::cast)
                .map(salesman -> calculateTotalSalesmanSales(salesman, salesmanSales))
                .min(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .map(salesman -> new Metric(METRIC_NAME, salesman))
                .orElse(new Metric(METRIC_NAME, METRIC_DEFAULT_VALUE));
    }

    private Entry<String, Double> calculateTotalSalesmanSales(Salesman salesman, Map<String, List<Sale>> salesmanSales){
        List<Sale> sales = salesmanSales.get(salesman.getName());
        Double price = DEFAULT_PRICE_VALUE;
        if (!CollectionUtils.isEmpty(sales)) {
            price = sales.stream()
                    .flatMap(sale -> sale.getItens().stream())
                    .map(this::calculateTotalItemPrice)
                    .reduce(Double::sum)
                    .orElse(DEFAULT_PRICE_VALUE);
        }
        return new Entry<>(salesman.getName(), price);
    }

    private double calculateTotalItemPrice(SaleItem item) {
        return item.getPrice() * item.getQuantity();
    }

}
