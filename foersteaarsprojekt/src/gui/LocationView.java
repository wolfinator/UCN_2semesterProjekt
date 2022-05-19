package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.ReservationCtrl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;

public class LocationView extends JFrame {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1129087465639106253L;

	private JPanel contentPane;
	
	public static LocationView locationView;
	public static GuestCountView guestCountView;
	public static CalendarTimeView calendarTimeView;
	public static ConfirmationView confirmationView;
	public static ReservationCtrl reservationCtrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					locationView = new LocationView();
					guestCountView = new GuestCountView();
					calendarTimeView = new CalendarTimeView();
					confirmationView = new ConfirmationView();
					
					reservationCtrl = new ReservationCtrl();
					
					locationView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBackground(Color.DARK_GRAY);
		panelLogo.setBounds(0, 0, 300, 372);
		contentPane.add(panelLogo);
		panelLogo.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(LocationView.class.getResource("/PhoSaigonLogo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 300, 372);
		panelLogo.add(lblLogo);
		
		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(300, 0, 300, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);
		
		JLabel lblText = new JLabel("Bestil bord p\u00E5");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblText.setBounds(66, 40, 165, 55);
		panelAdress.add(lblText);
		
		JButton btnLocationAalborg = new JButton("Aalborg");
		btnLocationAalborg.addActionListener(this::locationSelected);
		btnLocationAalborg.setBounds(66, 118, 165, 45);
		panelAdress.add(btnLocationAalborg);
		
		JButton btnLocationRanders = new JButton("Randers");
		btnLocationRanders.addActionListener(this::locationSelected);
		btnLocationRanders.setBounds(66, 175, 165, 45);
		panelAdress.add(btnLocationRanders);
		
		JButton btnLocationVejle = new JButton("Vejle");
		btnLocationVejle.addActionListener(this::locationSelected);
		btnLocationVejle.setBounds(66, 232, 165, 45);
		panelAdress.add(btnLocationVejle);
		
		JPanel Borderpanel = new JPanel();
		Borderpanel.setBounds(0, 0, 600, 322);
		contentPane.add(Borderpanel);
	}

	private void locationSelected(ActionEvent e) {
		setVisible(false);
		reservationCtrl.createReservation();
		JButton buttonThatWasClicked = ((JButton) e.getSource());
		confirmationView.textField_Sted.setText(buttonThatWasClicked.getText());
		guestCountView.setVisible(true);
	}
	
}
