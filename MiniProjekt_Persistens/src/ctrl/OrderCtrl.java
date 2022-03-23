package ctrl;

public class OrderCtrl {
	
	private CustomerCtrl customerCtrl;
	private EmployeeCtrl employeeCtrl;
	private ProductCtrl productCtrl;
	
	private OrderDb orderDb;
	
	public OrderCtrl() {
		customerCtrl = new CustomerCtrl();
		employeeCtrl = new EmployeeCtrl();
		productCtrl = new ProductCtrl();
		
		orderDb = new OrderDb();
	}
	
	public Order createOrder(int employeeId) {
		
	}
	
	public Customer findCustomer(String phoneNo) {
		
	}
	
	public SaleLineItem addProduct(String productNo, int quantity) {
		
	}
	
	public Receipt endOrder() {
		
	}
	
}
