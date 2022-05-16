package gui;

import java.time.LocalDate;
import java.time.LocalTime;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;

public class Test {
	public static void main(String[] args) {
		try {
		ReservationCtrl rCtrl = new ReservationCtrl();
		
		rCtrl.createReservation();
		rCtrl.setGuestCountAndDate(5, LocalDate.now());
		rCtrl.setStartingTime(LocalTime.now());
		rCtrl.endReservation("Mike Wazowski", "50505050", "wazowski@mail.dk");
		}
		catch(DataAccessException e) {
			e.printStackTrace();
		}
	}
}
