package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;
import model.Table;

public class Test {
	public static void main(String[] args) {
		
		try {
		ReservationCtrl rCtrl = new ReservationCtrl();
		
		/*
		rCtrl.createReservation();
		rCtrl.setGuestCountAndDate(5, LocalDate.now());
		rCtrl.setStartingTime(LocalTime.of(15, 0));
		List<LocalTime> timeSlot = rCtrl.findAvailableTimes();
		for(LocalTime t : timeSlot) {
			System.out.println(t);
		}
		*/
		
		rCtrl.createReservation();
		rCtrl.setGuestCountAndDate(5, LocalDate.now());
		rCtrl.setStartingTime(LocalTime.of(17, 0));
		rCtrl.endReservation("Hr Mand", "70102030", "dinMandmand@mail.dk");
		
		}
		
		catch(DataAccessException e) {
			e.printStackTrace();
		}
	}
}
