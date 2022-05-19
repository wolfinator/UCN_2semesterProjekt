package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ctrl.CustomerCtrl;
import ctrl.DataAccessException;
import ctrl.OrderCtrl;
import ctrl.ProductCtrl;
import ctrl.ReservationCtrl;
import ctrl.TableCtrl;
import db.CustomerDB;
import db.OrderDB;
import db.ProductDB;
import db.ReservationDB;
import db.TableDB;
import model.Customer;
import model.Order;
import model.OrderLineItem;
import model.Product;
import model.ProductType;
import model.Reservation;
import model.Table;

class JUnitTestReservation {
	private static CustomerCtrl customerCtrl;
	private static OrderCtrl orderCtrl;
	private static ProductCtrl productCtrl;
	private static ReservationCtrl reservationCtrl;
	private static TableCtrl tableCtrl;
	private static CustomerDB customerDb;
	private static OrderDB orderDb;
	private static ProductDB productDb;
	private static ReservationDB reservationDb;
	private static TableDB tableDb;
	private static Customer c1, c2, c3;
	private static Product p1, p2, p3;
	private static Order o1;

//	@BeforeAll
//	public static void beforeClass() throws DataAccessException {
//		customerCtrl = new CustomerCtrl();
//		orderCtrl = new OrderCtrl();
//		productCtrl = new ProductCtrl();
//		reservationCtrl = new ReservationCtrl();
//		
//		c1 = new Customer("Bob", "30222998", "Bob@bobmail.dk");
//		c2 = new Customer ("Anna", "30231854", "AnnalGren@annaMail.com"); 
//		c3 = new Customer("Mads", "30694075", "Wolf1337@WolfMail.com");
//	
//
//	}

	// UnitTest for at se om reservation virker.

	@Test
	void testCreateReservation() {
		// Arrange
		// Customer customer = new Customer("Bob", "30222998", "Bob@bobmail.dk");
		// Table table = new Table();
		Reservation reservation = null;

		// act
		try {
			ReservationCtrl reservationCtrl = new ReservationCtrl();
			reservation = reservationCtrl.createReservation();
		} catch (DataAccessException e) {
			fail("Couldn't create reservationCtrl object");
		}

		// assert
		assertTrue(reservation != null);
	}

	// Opret ordre
	// Skal kunnes gøre gennem ctrl.
	@Test
	void testOrder() {
		// Arrange
		Customer customer = new Customer("Bob", "30222998", "Bob@bobmail.dk");
		Table table = new Table();
		Reservation reservation = new Reservation();
		Order order = new Order();
		Product product = new Product();
		ProductType productType = new ProductType();
		productType.setId(2);
		productType.setName("pho");
		product.setType(productType);

		// Act
		order.addProduct(product, 1);

		// Assert
		assertEquals(order.getOrderLineItem().get(0).getProduct().getName(), product.getName());

	}

	@Test
	void testGuestCountAndDate() {
		Customer customer = c1;
		Customer customer2 = c2;
		Customer customer3 = c3;
		Reservation reservation = new Reservation();
		reservation.getGuestCount();
		

	}
}
