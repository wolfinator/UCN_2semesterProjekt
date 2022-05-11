package model;

import java.util.List;

public class Order {
	private int id; 
	private int orderNo; 
	private boolean status; 
	private List<OrderLineItem> orderLineItems; 
	
	public Order() {
		orderLineItems = new List<>(); //TODO ÅBENBART FORKERT??? 
	}
	
	public SaleLineItem addProduct(Product product, int quantity) {
		
	}
	
}
