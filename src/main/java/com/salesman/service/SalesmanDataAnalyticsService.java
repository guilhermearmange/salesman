package com.salesman.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.salesman.model.Customer;
import com.salesman.model.DataAnalytics;
import com.salesman.model.Entry;
import com.salesman.model.ModelData;
import com.salesman.model.Sale;
import com.salesman.model.SaleItem;
import com.salesman.model.Salesman;

@Service
public class SalesmanDataAnalyticsService {

	private static final double DEFAULT_PRICE_VALUE = 0.0;
	private static final long ID_SALE_NOT_FOUND = -1l;

	public DataAnalytics process(List<ModelData> data) {
		long amountOfClients = getAmountOfClients(data);
		long amountOfSalesman = getAmountOfSalesman(data);
		String worstSalesman = getWorstSalesman(data);
		long mostExpensiveSale = getMostExpensiveSale(data);
		return new DataAnalytics(amountOfClients, amountOfSalesman, worstSalesman, mostExpensiveSale);
	}

	private long getMostExpensiveSale(List<ModelData> data) {
		return data.stream().filter(Sale.class::isInstance)
			.map(Sale.class::cast)
			.map(sale -> {
				Double price = sale.getItens().stream()
					.map(this::calculateTotalItemPrice)
					.reduce(Double::sum)
					.orElse(DEFAULT_PRICE_VALUE);
				return new Entry<Sale, Double>(sale, price);
			}).max(Comparator.comparing(Entry::getValue))
			.map(entry -> entry.getKey().getId()).orElse(ID_SALE_NOT_FOUND);
	}


	private String getWorstSalesman(List<ModelData> data) {
		Map<String, List<Sale>> salesmanSales = data.stream()
			.filter(Sale.class::isInstance)
			.map(Sale.class::cast)
			.collect(Collectors.groupingBy(Sale::getSalesmanName));
		
		return data.stream().filter(Salesman.class::isInstance)
			.map(Salesman.class::cast)
			.map(salesman -> {
				List<Sale> sales = salesmanSales.get(salesman.getName());
				Double price = DEFAULT_PRICE_VALUE;
				if (!CollectionUtils.isEmpty(sales)) {
					price = sales.stream()
						.flatMap(sale -> sale.getItens().stream())
						.map(this::calculateTotalItemPrice)
						.reduce(Double::sum)
						.orElse(DEFAULT_PRICE_VALUE);
				}
				return new Entry<String, Double>(salesman.getName(), price);
			}).min(Comparator.comparing(Entry::getValue))
			.map(entry -> entry.getKey()).orElse(null);
	}
	
	private long getAmountOfSalesman(List<ModelData> data) {
		return data.stream().filter(Salesman.class::isInstance).count();
	}

	private long getAmountOfClients(List<ModelData> data) {
		return data.stream().filter(Customer.class::isInstance).count();
	}

	private double calculateTotalItemPrice(SaleItem item) {
		return item.getPrice() * item.getQuantity();
	}
}
