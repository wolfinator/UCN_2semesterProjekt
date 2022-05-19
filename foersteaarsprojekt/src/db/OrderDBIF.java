package db;

import ctrl.DataAccessException;
import model.Order;
import model.Reservation;

public interface OrderDBIF {
	boolean saveOrder(Order o, Reservation r) throws DataAccessException;
	
		
	}
