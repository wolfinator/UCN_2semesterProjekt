package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ctrl.CustomerCtrl;
import ctrl.DataAccessException;
import ctrl.OrderCtrl;
import ctrl.ProductCtrl;
import ctrl.ReservationCtrl;
import ctrl.TableCtrl;
import db.CustomerDB;
import db.DBConnection;
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
	
	private static ReservationCtrl reservationCtrl;
	private static OrderCtrl orderCtrl;
	
	@BeforeAll
	private static void beforeClass() {
		try {
			reservationCtrl = new ReservationCtrl();
			orderCtrl = new OrderCtrl();
		} catch (DataAccessException e) {
			fail("Fejl ved at lave et controller objekt");
		}
	}
	
	@Test
	void testCreateReservation() {
		// Arrange
		Reservation created;
		Reservation arranged = new Reservation();

		// Act
	    created = reservationCtrl.createReservation();
	    
		// assert
		assertEquals(created, arranged);
	}

	// Opret ordre
	// Skal kunnes gøre gennem ctrl.
	@Test
	void testAddProductToOrder() {
		// Arrange
		Order createdOrder = new Order();
		OrderLineItem created;
		OrderLineItem arranged = new OrderLineItem();
		Product product = new Product();
		ProductType pt = new ProductType();
		pt.setId(3); pt.setName("Drikkevare");
		product.setId(2); product.setName("Hjemmelavet lemonade"); 
		product.setPrice(99); product.setType(null);

		// Act
		created = createdOrder.addProduct(product, 5);

		// Assert
		assertEquals(created.getProduct(), product);
		assertEquals(created.getQuantity(), 5);
	}

	@Test
	void testGuestCountAndDate() {
		//Arrange
		reservationCtrl.createReservation();
		LocalDate testDate = LocalDate.of(2000, 5, 20);
		List<LocalTime> expected = new ArrayList<>();
		int increment = 30;
		LocalTime startTime = LocalTime.of(15, 0);
		while(startTime.isBefore(LocalTime.of(19, 30))) {
			expected.add(startTime);
			startTime = startTime.plusMinutes(increment);
		}
		
		//Act
		List<LocalTime> timeReturned = reservationCtrl.setGuestCountAndDate(5, testDate);
		
		//Assert
		for (LocalTime localTime : timeReturned) {
			assertEquals(localTime, expected.get(timeReturned.indexOf(localTime)));
		}
	}
	
	@Test
	void testSetStartingTime() {
		//Arrange
		reservationCtrl.createReservation();
		LocalDate testDate = LocalDate.of(2000, 5, 20);
		LocalTime testTime = LocalTime.of(17, 0);
		reservationCtrl.setGuestCountAndDate(9, testDate);
		
		//Act
		reservationCtrl.setStartingTime(testTime);
		
		//Assert
		assertEquals(reservationCtrl.currentReservation.getDate(), LocalDateTime.of(testDate, testTime));
	}
	
	@Test
	void testAddProduct() {
		//Arrange
		reservationCtrl.createReservation();
		Order returnedOrder = null;
		OrderLineItem oli = null;
		
		//Act
		try {
			returnedOrder = reservationCtrl.addProduct("Hjemmelavet lemonade", 6);
			oli = returnedOrder.getOrderLineItem().get(0);
		} catch (DataAccessException e) {
			fail("Product name not in DB");
		}
		
		//Assert
		assertEquals(returnedOrder.getTotalPrice(), 234);
		assertEquals(oli.getQuantity(), 6);
		assertEquals(oli.getProduct().getName(), "Hjemmelavet lemonade");
	}
	
	@Test
	void testEndReservation() {
		//Arrange
		ReservationDB reservationDB = null;
		Reservation reservationFromDB = null;
		reservationCtrl.createReservation();
		LocalDate testDate = LocalDate.of(2000, 5, 5);
		LocalTime testTime = LocalTime.of(18, 0);
		reservationCtrl.setGuestCountAndDate(3, testDate);
		reservationCtrl.setStartingTime(testTime);
		String testName = "Kaywan Wu";
		String testPhoneNo = "12345678";
		String testEmail = "vu@mail.dk";
		Customer testCustomer = null;
		
		//Act
		try {
			reservationCtrl.endReservation(testName, testPhoneNo, testEmail);
			reservationDB = new ReservationDB();
			reservationFromDB = reservationDB.findReservationsByDate(testDate).get(0);
			testCustomer = reservationFromDB.getCustomer();
			
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		
		//Assert
		assertEquals(testCustomer.getFirstName(), "Kaywan");
		assertEquals(testCustomer.getLastName(), "Wu");
		assertEquals(testCustomer.getPhoneNo(), "12345678");
		assertEquals(testCustomer.getEmail(), "vu@mail.dk");
		assertEquals(reservationFromDB.getDate(), LocalDateTime.of(testDate, testTime));
		assertEquals(reservationFromDB.getGuestCount(), 3);
		
		try {
			DBConnection dbc = DBConnection.getInstance();
			String deleteQuery = "delete Customer "
					+ "from Customer c, Reservation r "
					+ "where c.id = r.customerId "
					+ "and dateTimeslot >= cast('2000-05-05' as datetime) "
					+ "and dateTimeslot < cast('2000-05-06' as datetime); ";
			PreparedStatement ps_delete = dbc.getConnection().prepareStatement(deleteQuery);
			ps_delete.execute();
		} catch (DataAccessException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail("Kunne ikke slette indsat reservation fra test");
		}
	}
}
