package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.JButton;

public class Calendar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar frame = new Calendar();
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
	
	public Calendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.setBounds(6, 48, 477, 302);
		contentPane.add(calendarPanel);
		
		JButton btnNewButton = new JButton("Videre");
		btnNewButton.setBounds(508, 246, 117, 40);
		contentPane.add(btnNewButton);
		
		TimePicker timePicker = new TimePicker();
		timePicker.setBounds(482, 84, 161, 29);
		contentPane.add(timePicker);
	}
}
