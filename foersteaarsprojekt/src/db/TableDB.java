package db;

import java.util.List;

import model.Reservation;
import model.Table;

public class TableDB implements TableDBIF{

	@Override
	public List<Table> getTables() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean saveTableToReservation(Table t, Reservation r){
		boolean res = true;
		
		return res;
	}

}
