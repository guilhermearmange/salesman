package com.salesman.service.analytics;

import com.salesman.model.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MostExpensiveSaleMetricService implements AnalyticsMetricService {
    private final static double DEFAULT_PRICE_VALUE = 0.0;
    private final static String METRIC_NAME = "Most Expensive Sale";
    private final static String METRIC_DEFAULT_VALUE = "Sale not found";

    @Override
    public Metric process(List<ModelData> data) {
        return data.stream().filter(Sale.class::isInstance)
                .map(Sale.class::cast)
                .map(this::calculateTotalSalePrice)
                .max(Comparator.comparing(Entry::getValue))
                .map(entry -> entry.getKey().getId())
                .map(sale -> new Metric(METRIC_NAME, String.valueOf(sale)))
                .orElse(new Metric(METRIC_NAME, METRIC_DEFAULT_VALUE));
    }

    private Entry<Sale, Double> calculateTotalSalePrice(Sale sale){
        Double price = sale.getItens().stream()
                .map(this::calculateTotalItemPrice)
                .reduce(Double::sum)
                .orElse(DEFAULT_PRICE_VALUE);
        return new Entry<>(sale, price);
    }

    private double calculateTotalItemPrice(SaleItem item) {
        return item.getPrice() * item.getQuantity();
    }

}
