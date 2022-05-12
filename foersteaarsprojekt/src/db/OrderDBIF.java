package db;

import ctrl.DataAccessException;
import model.Order;

public interface OrderDBIF {
	void saveOrder(Order o) throws DataAccessException; 
		
	}
