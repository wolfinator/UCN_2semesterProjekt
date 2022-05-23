package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int orderNo;
	private int totalPrice;
	private boolean status;
	private List<OrderLineItem> orderLineItems;

	public Order() {
		orderLineItems = new ArrayList<>(); // TODO ÅBENBART FORKERT???
	}

	public OrderLineItem addProduct(Product product, int quantity) {
		OrderLineItem res = null;
		boolean productExistsAlready = false;
		for (OrderLineItem oli : orderLineItems) {
			if (oli.getProduct().equals(product)) {
				oli.setQuantity(oli.getQuantity() + quantity);
				productExistsAlready = true;
				res = oli;
			}
		}
		if (!productExistsAlready) {
			OrderLineItem oli = new OrderLineItem();

			oli.setProduct(product);
			oli.setQuantity(quantity);
			res = oli;
			orderLineItems.add(oli);

		}
		return res;
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

	public boolean isStatus() {
		return status;
	}

	public boolean setStatus(boolean status) {
		return this.status = status;
	}

	public List<OrderLineItem> getOrderLineItem() {
		return orderLineItems;
	}

	public int getOrderNo() {
		return this.orderNo;
	}
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderLineItems == null) ? 0 : orderLineItems.hashCode());
		result = prime * result + orderNo;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + totalPrice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderLineItems == null) {
			if (other.orderLineItems != null)
				return false;
		} else if (!orderLineItems.equals(other.orderLineItems))
			return false;
		if (orderNo != other.orderNo)
			return false;
		if (status != other.status)
			return false;
		if (totalPrice != other.totalPrice)
			return false;
		return true;
	}

	
}
