package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Order;

public interface OrderDbIF {
	public boolean insert(Order o) throws DataAccessException;
	
	//public Order findByOrderNo(String orderNo) throws DataAccessException;
	
	//public List<Order> findAll() throws DataAccessException;
}
