package gui;

import java.util.Scanner;

import ctrl.DataAccessException;
import ctrl.OrderCtrl;
import model.Customer;
import model.Product;

public class OrderTUI {

	private static OrderCtrl orderCtrl;
	
	private static int employeeId = 3;
	private static String input;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws DataAccessException {
		orderCtrl = new OrderCtrl();	
		
		System.out.println("Western Style Ltd. \n - Order Processing");
		do {
			System.out.println("\n");
			System.out.println("Write next command:\n"+
							   "q: quit, create: create new order, "+
							   "find: find customer, add: add product, end: end order\n");
			input = scanner.next();
			
			switch(input) {
			case "create":
				createOrder();
				break;
			case "find":
				findCustomer();
				break;
			case "add":
				addProduct();
				break;
			case "end":
				endOrder();
				break;
			default:
				break;
			}
			
		} while (!input.equals("q"));
	}

	private static void endOrder() {
		try {
			orderCtrl.endOrder();
			
			System.out.println("Order ended.");
		} catch (DataAccessException e) {
			System.out.println("Couldn't access database. ErrorMessage: " + e.getMessage());
		}
	}

	private static void addProduct() {
		System.out.println("Enter productNo: ");
		String productNo = scanner.next();
		
		System.out.println("Enter quantity: ");
		int quantity = scanner.nextInt();
		
		try {
			Product product = orderCtrl.addProduct(productNo, quantity).getProduct();
			
			System.out.println("Product " + product.getName() + " added " + quantity + " times");
		} catch (DataAccessException e) {
			System.out.println("Couldn't access database. ErrorMessage: " + e.getMessage());
		}
	}

	private static void findCustomer() {
		System.out.println("Enter phoneNo: ");
		String phoneNo = scanner.next();
		
		try {
			Customer customer = orderCtrl.findCustomer(phoneNo);
			System.out.println("Found customer: " + customer.getName());
		} catch (DataAccessException e) {
			System.out.println("Couldn't access database. ErrorMessage: " + e.getMessage());
		}
	}

	private static void createOrder() {
		try {
			orderCtrl.createOrder(employeeId);
			System.out.println("Created new order.");
		} catch (DataAccessException e) {
			System.out.println("Couldn't access database. ErrorMessage: " + e.getMessage());
		}
	}

}
