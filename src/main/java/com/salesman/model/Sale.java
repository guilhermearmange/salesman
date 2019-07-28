package com.salesman.model;

import java.util.List;

public class Sale extends ModelData {

	private long id;
	private List<SaleItem> itens;
	private String salesmanName;

	public Sale(long id, List<SaleItem> itens, String salesmanName) {
		this.id = id;
		this.itens = itens;
		this.salesmanName = salesmanName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<SaleItem> getItens() {
		return itens;
	}

	public void setItens(List<SaleItem> itens) {
		this.itens = itens;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

}
