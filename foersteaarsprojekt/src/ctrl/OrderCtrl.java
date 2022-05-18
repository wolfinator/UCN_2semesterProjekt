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
		currOrder = new Order();
		orderDb = new OrderDB();
	}
	public OrderLineItem addProduct(int productId, int quantity) throws DataAccessException {
		Product product = productCtrl.findProductById(productId);
		
		OrderLineItem oli = new OrderLineItem();
		oli = currOrder.addProduct(product, quantity);
		return oli; //Burde man returnere ordren istedet for orderlinen?
	}
	
}

