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
		SaleLineItem sli = new SaleLineItem();
		
		sli.setProduct(); 
		sli.setQuanity();
		sli.setOrder(this); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<OrderLineItem> getOrderLineItem(){
		return orderLineItems; 
	}
}
