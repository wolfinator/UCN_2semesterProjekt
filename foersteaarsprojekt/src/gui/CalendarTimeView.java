package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
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

import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CalendarTimeView extends JFrame {

	private JPanel contentPane;
	
	private JComboBox comboBox;
	
	public LocalTime timeSelected;

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
	 */

	public CalendarTimeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LocationView.guestCountView.setVisible(true);
			}
		});
		btnTilbage.setBounds(395, 246, 91, 40);
		contentPane.add(btnTilbage);

		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent arg0) {
				LocationView.reservationCtrl
				.setGuestCountAndDate(LocationView.guestCountView.guestCount, arg0.getNewDate());
				try {
					displayTimes(LocationView.reservationCtrl.findAvailableTimes());
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public void yearMonthChanged(YearMonthChangeEvent arg0) {
			}
		});
		calendarPanel.setBounds(90, 47, 298, 302);
		contentPane.add(calendarPanel);

		JButton btnNewButton = new JButton("Videre");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					LocationView.reservationCtrl.setStartingTime(timeSelected);
					LocationView.confirmationView.textField_DT.setText(
							calendarPanel.getSelectedDate().toString() + " " + timeSelected.toString()
							);
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LocationView.confirmationView.setVisible(true);
			}
		});
		btnNewButton.setBounds(490, 246, 92, 40);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					  if(!btnNewButton.isEnabled()) btnNewButton.setEnabled(true);
			          Object item = e.getItem();
			          timeSelected = (LocalTime) item;
			       }
			}
		});
		comboBox.setBounds(395, 71, 187, 22);
		contentPane.add(comboBox);
	}
	private void displayTimes(List<LocalTime> availableTimeSlots) {
		comboBox.removeAllItems();
		comboBox.setModel(new DefaultComboBoxModel(availableTimeSlots.toArray()));
	}
}
