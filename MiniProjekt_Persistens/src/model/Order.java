package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private Employee employee;
	private Customer customer;
	private LocalDate date;
	private String orderNo;
	private boolean deliveryStatus;
	private LocalDate deliveryDate;
	
	public List<SaleLineItem> saleLineItems;
	
	public Order() {
		saleLineItems = new ArrayList<>();
	}
	
	public SaleLineItem addProduct(Product product, int quantity) {
		SaleLineItem sli = new SaleLineItem();
		
		sli.setAmount(quantity);
		sli.setProduct(product);
		sli.setOrder(this);
		
		return sli;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
