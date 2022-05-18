package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;
import model.Customer;
import model.Order;
import model.OrderLineItem;
import model.Product;
import model.ProductType;
import model.Reservation;
import model.Table;

class JUnitTestReservation {

	// UnitTest for at se om reservation virker.

	@Test
	void testCreateReservation() {
		// Arrange
		//Customer customer = new Customer("Bob", "30222998", "Bob@bobmail.dk");
		//Table table = new Table();
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
	void TestOrder() {
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
}
