package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalendarTime extends JFrame {
 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarTime frame = new CalendarTime();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public CalendarTime() {
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
				GuestCountView GuestCount = new GuestCountView();
				GuestCount.run();
			}
		});
		btnTilbage.setBounds(461, 246, 91, 40);
		contentPane.add(btnTilbage);

		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.setBounds(6, 48, 455, 302);
		contentPane.add(calendarPanel);

		JButton btnNewButton = new JButton("Videre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Confirmation Confirmation = new Confirmation();
				Confirmation.run();
			}
		});
		btnNewButton.setBounds(551, 246, 92, 40);
		contentPane.add(btnNewButton);

		TimePicker timePicker = new TimePicker();
		timePicker.setBounds(471, 84, 172, 29);
		contentPane.add(timePicker);
	}

	public static void run() {
		try {
			CalendarTime frame = new CalendarTime();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
