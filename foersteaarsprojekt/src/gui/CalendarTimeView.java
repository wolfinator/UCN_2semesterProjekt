package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

import ctrl.DataAccessException;
import ui.ReservationUI;

import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class CalendarTimeView extends JFrame {

	private JPanel contentPane;

	private JFrame previousFrame;
	private JFrame nextFrame;
	private ConfirmationView confirm;
	private ReservationUI uiCtrl;

	private JComboBox comboBox;

	private LocalTime timeSelected;

	private JButton btnNext;
	private CalendarPanel calendarPanel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CalendarTimeView frame = new CalendarTimeView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param confirmationView
	 * @param reservationUI
	 */

	public CalendarTimeView(ReservationUI reservationUI, ConfirmationView confirmationView) {
		uiCtrl = reservationUI;
		confirm = confirmationView;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(this::goBack);
		btnTilbage.setBounds(395, 246, 91, 40);
		contentPane.add(btnTilbage);

		calendarPanel = new CalendarPanel();
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent arg0) {
				dateSelected(arg0);
			}

			public void yearMonthChanged(YearMonthChangeEvent arg0) {
			}
		});
		calendarPanel.setBounds(90, 47, 298, 302);
		contentPane.add(calendarPanel);

		btnNext = new JButton("Videre");
		btnNext.setEnabled(false);
		btnNext.addActionListener(this::goNext);
		btnNext.setBounds(490, 246, 92, 40);
		contentPane.add(btnNext);

		comboBox = new JComboBox();
		comboBox.addItemListener(this::itemSelected);
		comboBox.setBounds(395, 71, 187, 22);
		contentPane.add(comboBox);
	}

	private void itemSelected(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object item = e.getItem();
			timeSelected = (LocalTime) item;
		}
	}

	private void displayTimes(List<LocalTime> availableTimeSlots) {
		comboBox.removeAllItems();
		comboBox.setModel(new DefaultComboBoxModel(availableTimeSlots.toArray()));
		timeSelected = (LocalTime) comboBox.getSelectedItem();
		btnNext.setEnabled(true);
	}

	private void dateSelected(CalendarSelectionEvent e) {
		int guestCount = ((GuestCountView) previousFrame).guestCount;
		uiCtrl.setGuestCountAndDate(guestCount, e.getNewDate());
		try {
			displayTimes(uiCtrl.findAvailableTimes());
		} catch (DataAccessException e1) {
			JOptionPane.showMessageDialog(null,
					"Fejl ved at s?tte dato og antal g?ster p? reservation\n" + e1.getMessage());
		}
	}

	private void goBack(ActionEvent e) {
		setVisible(false);
		previousFrame.setVisible(true);
	}

	private void goNext(ActionEvent e) {
		setVisible(false);
		try {
			uiCtrl.setStartingTime(timeSelected);
			confirm.textField_DT.setText(calendarPanel.getSelectedDate().toString() + " " + timeSelected.toString());
			nextFrame.setVisible(true);
		} catch (DataAccessException e1) {
			setVisible(true);
			JOptionPane.showMessageDialog(null, "Fejl ved at s?tte tid p? reservation\n" + e1.getMessage());
		}
	}

	public void addTransitions(GuestCountView guestCountView, CreateOrderView createOrderView) {
		previousFrame = guestCountView;
		nextFrame = createOrderView;

	}

	public void reset() {
		btnNext.setEnabled(false);
		comboBox.removeAllItems();
		
	}
}
