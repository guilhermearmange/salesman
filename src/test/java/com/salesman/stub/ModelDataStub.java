package com.salesman.stub;

import com.salesman.model.*;

import java.util.Arrays;
import java.util.List;

public class ModelDataStub {
    private static final double SALARY_SALESMAN_1 = 1000;
    private static final String NAME_SALESMAN_1 = "Salesman 1";
    private static final String CPF_SALESMAN_1 = "CPF 1";
    private static final String CPF_SALESMAN_2 = "CPF 2";
    private static final String NAME_SALESMAN_2 = "Salesman 2";
    private static final double SALARY_SALESMAN_2 = 800;
    private static final String CPNJ = "CNPJ";
    private static final String NAME_CUSTOMER = "Customer Name";
    private static final String BUSINESS_AREA = "Business Area";
    private static final long SALE_ID_1 = 1L;
    private static final long SALE_ITEM_ID_1 = 2L;
    private static final double QUATITY_1 = 20;
    private static final double PRICE_1 = 30;
    private static final double QUANTITY_2 = 40;
    private static final double PRICE_2 = 5;
    private static final long SALE_ITEM_ID_2 = 3L;
    private static final long SALE_ID_2 = 4L;
    private static final long SALE_ITEM_ID_3 = 1001L;
    private static final double QUATITY_3 = 300;
    private static final double PRICE_3 = 7.99;

    public static List<ModelData> createList(){
        Salesman salesman1 = new Salesman(CPF_SALESMAN_1, NAME_SALESMAN_1, SALARY_SALESMAN_1);
        Salesman salesman2 = new Salesman(CPF_SALESMAN_2, NAME_SALESMAN_2, SALARY_SALESMAN_2);
        Customer customer = new Customer(CPNJ, NAME_CUSTOMER, BUSINESS_AREA);
        Sale sale1 = new Sale(SALE_ID_1, Arrays.asList(new SaleItem(SALE_ITEM_ID_1, QUATITY_1, PRICE_1), new SaleItem(SALE_ITEM_ID_2, QUANTITY_2, PRICE_2)), NAME_SALESMAN_2);
        Sale sale2 = new Sale(SALE_ID_2, Arrays.asList(new SaleItem(SALE_ITEM_ID_3, QUATITY_3, PRICE_3)), NAME_SALESMAN_1);
        return Arrays.asList(salesman1, salesman2, customer, sale1, sale2);
    }
}
