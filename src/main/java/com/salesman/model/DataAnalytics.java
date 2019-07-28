package com.salesman.model;

public class DataAnalytics {

	private long amountOfClients;
	private long amountOfSalesman;
	private String worstSalesman;
	private long mostExpensiveSale;

	public DataAnalytics(long amountOfClients, long amountOfSalesman, String worstSalesman, long mostExpensiveSale) {
		this.amountOfClients = amountOfClients;
		this.amountOfSalesman = amountOfSalesman;
		this.worstSalesman = worstSalesman;
		this.mostExpensiveSale = mostExpensiveSale;
	}

	public long getAmountOfClients() {
		return amountOfClients;
	}

	public void setAmountOfClients(long amountOfClients) {
		this.amountOfClients = amountOfClients;
	}

	public long getAmountOfSalesman() {
		return amountOfSalesman;
	}

	public void setAmountOfSalesman(long amountOfSalesman) {
		this.amountOfSalesman = amountOfSalesman;
	}

	public String getWorstSalesman() {
		return worstSalesman;
	}

	public void setWorstSalesman(String worstSalesman) {
		this.worstSalesman = worstSalesman;
	}

	public long getMostExpensiveSale() {
		return mostExpensiveSale;
	}

	public void setMostExpensiveSale(long mostExpensiveSale) {
		this.mostExpensiveSale = mostExpensiveSale;
	}

}
