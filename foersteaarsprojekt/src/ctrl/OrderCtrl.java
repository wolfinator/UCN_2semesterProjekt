package ctrl;


import db.OrderDB;
import model.Customer;
import model.Order;
import model.OrderLineItem;
import model.Product;

public class OrderCtrl {
	private ProductCtrl productCtrl;
	private Order currOrder;
	private OrderDB orderDb;

	public OrderCtrl() throws DataAccessException {
		productCtrl = new ProductCtrl();
		orderDb = new OrderDB();
	}
	public Order addProduct(int productId, int quantity) throws DataAccessException {
		Product product = productCtrl.findProductById(productId);
		
		OrderLineItem oli = new OrderLineItem();
		oli = currOrder.addProduct(product, quantity);
		return currOrder;
	}
	
	public Order createOrder() {
		Order res = new Order();
		
		currOrder = res;
		
		return res;
	}
	
}

