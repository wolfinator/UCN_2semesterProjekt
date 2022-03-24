package ctrl;

import db.OrderDb;
import db.OrderDbIF;
import model.Customer;
import model.Employee;
import model.Order;
import model.Product;
import model.Receipt;
import model.SaleLineItem;

public class OrderCtrl {
	
	private CustomerCtrl customerCtrl;
	private EmployeeCtrl employeeCtrl;
	private ProductCtrl productCtrl;
	
	private OrderDbIF orderDb;
	
	private Order currentOrder;
	
	public OrderCtrl() throws DataAccessException {
		customerCtrl = new CustomerCtrl();
		employeeCtrl = new EmployeeCtrl();
		productCtrl = new ProductCtrl();
		
		orderDb = new OrderDb();
	}
	
	public Order createOrder(int employeeId) throws DataAccessException {
		currentOrder = new Order();
		Employee employee = employeeCtrl.findEmployeeById(employeeId);
		currentOrder.setEmployee(employee);
		return currentOrder;
	}
	
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		Customer customer = customerCtrl.findByPhoneNo(phoneNo);
		currentOrder.setCustomer(customer);
		return customer;
	}
	
	public SaleLineItem addProduct(String productNo, int quantity) throws DataAccessException {
		Product product = productCtrl.findByProductNo(productNo);
		
		/*
		 * Ved ikke om det er for weird at den returnerer
		 * et SaleLineItem, men det står der pt i interaktionsdiagrammet
		 */
		SaleLineItem sli = new SaleLineItem();
		sli.setAmount(quantity);
		sli.setOrder(currentOrder);
		sli.setProduct(product);
		
		currentOrder.addProduct(product, quantity);
		
		return sli;
	}
	
	public Receipt endOrder() {
		Receipt receipt = new Receipt();
		//TODO Find ud af hvad receipt skal indebære.
		return receipt;
	}
	
}
