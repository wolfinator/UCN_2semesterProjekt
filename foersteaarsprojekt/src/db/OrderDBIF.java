package db;

import ctrl.DataAccessException;
import model.Order;
import model.Reservation;

public interface OrderDBIF {
	void saveOrder(Order o, Reservation r) throws DataAccessException; 
		
	}
