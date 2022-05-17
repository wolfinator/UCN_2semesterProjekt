package db;

import java.util.List;

import ctrl.DataAccessException;
import model.Reservation;
import model.Table;

public interface TableDBIF {
	public List<Table> getTables() throws DataAccessException;
	
	public List<Table> getTables(int reservationId) throws DataAccessException;
	
	public boolean saveTableToReservation(Table t, Reservation r) throws DataAccessException;
}
