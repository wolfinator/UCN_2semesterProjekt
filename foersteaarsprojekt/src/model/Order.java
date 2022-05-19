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

	public List<OrderLineItem> getOrderLineItem() {
		return orderLineItems;
	}

	public int getOrderNo() {
		// TODO Auto-generated method stub
		return orderNo;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
