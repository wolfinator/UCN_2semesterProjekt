package db;

import java.util.List;

import model.Reservation;
import model.Table;

public interface TableDBIF {
	public List<Table> getTables();
	
	public boolean saveTableToReservation(Table t, Reservation r);
}
