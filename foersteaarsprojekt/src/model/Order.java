package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id; 
	private int orderNo; 
	private int totalPrice; 
	private boolean status; 
	private List<OrderLineItem> orderLineItems; 
	
	public Order() {
		orderLineItems = new ArrayList<>(); //TODO ÅBENBART FORKERT??? 
	}
	
	public OrderLineItem addProduct(Product product, int quantity) {
		OrderLineItem oli = new OrderLineItem();
		
		oli.setProduct(product);
		oli.setQuantity(quantity);
		
		orderLineItems.add(oli);
		
		return oli; 
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for(OrderLineItem o : orderLineItems) {
			double productPrice = o.getProduct().getPrice();
			int quantity = o.getQuantity();
			double orderLinePrice = quantity * productPrice;
			totalPrice = orderLinePrice + totalPrice;
			
		}
		
		return totalPrice;
		
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

	public boolean setStatus(boolean status) {
		return this.status = status;
	}
	
	public List<OrderLineItem> getOrderLineItem(){
		return orderLineItems; 
	}

	public int getOrderNo() {
		// TODO Auto-generated method stub
		return orderNo;
	}

}
