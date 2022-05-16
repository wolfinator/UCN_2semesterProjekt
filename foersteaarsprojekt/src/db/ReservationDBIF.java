package db;

import model.Reservation;

import java.time.LocalDate;
import java.util.List;

import ctrl.DataAccessException;

public interface ReservationDBIF {
	List<Reservation> findReservationsByDate(LocalDate date) throws DataAccessException;
	
	boolean saveReservation(Reservation reservation) throws DataAccessException;
}
