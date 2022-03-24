package ctrl;

import db.OrderDb;
import db.OrderDbIF;
import model.Customer;
import model.Employee;
import model.Order;
import model.Product;
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
		
		SaleLineItem sli = new SaleLineItem();
		sli = currentOrder.addProduct(product, quantity);
		
		return sli;
	}
	
	public boolean endOrder() throws DataAccessException {
		return orderDb.insert(currentOrder);
	}
	
}
