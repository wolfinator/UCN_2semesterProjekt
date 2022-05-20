package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ctrl.DataAccessException;
import ui.ReservationUI;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class ConfirmationView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Mobil;
	private JTextField textField_Navn;
	private JTextField textField_Email;
	
	public JTextField textField_AG;
	public JTextField textField_Sted;
	public JTextField textField_DT;
	
	private JTextPane textPaneNote;
	
	private JFrame previousFrame;
	private JFrame nextFrame;
	private ReservationUI uiCtrl;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfirmationView frame = new ConfirmationView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param reservationUI 
	 */
	public ConfirmationView(ReservationUI reservationUI) {
		uiCtrl = reservationUI;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPicture = new JPanel();
		panelPicture.setBackground(Color.DARK_GRAY);
		panelPicture.setBounds(0, 0, 600, 100);
		contentPane.add(panelPicture);
		panelPicture.setLayout(null);

		JLabel lblLeftPicture = new JLabel("");
		lblLeftPicture.setBounds(0, 0, 271, 100);
		panelPicture.add(lblLeftPicture);
		lblLeftPicture.setIcon(new ImageIcon(ConfirmationView.class.getResource("/ramen.png")));

		JLabel lblRightPicture = new JLabel("");
		lblRightPicture.setIcon(new ImageIcon(ConfirmationView.class.getResource("/ramen.png")));
		lblRightPicture.setBounds(302, 22, 298, 267);
		panelPicture.add(lblRightPicture);

		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(0, 0, 600, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);

		JLabel lblSted = new JLabel("Sted:");
		lblSted.setHorizontalAlignment(SwingConstants.LEFT);
		lblSted.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSted.setBounds(125, 110, 46, 30);
		panelAdress.add(lblSted);

		JLabel lblGuestCount = new JLabel("Antal g\u00E6ster:");
		lblGuestCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuestCount.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGuestCount.setBounds(125, 140, 93, 30);
		panelAdress.add(lblGuestCount);

		JLabel lblDateTime = new JLabel("Dato og tid:");
		lblDateTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateTime.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateTime.setBounds(350, 110, 93, 30);
		panelAdress.add(lblDateTime);

		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNavn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNavn.setBounds(125, 180, 46, 30);
		panelAdress.add(lblNavn);

		JLabel lblMobil = new JLabel("Mobil");
		lblMobil.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobil.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMobil.setBounds(125, 280, 46, 30);
		panelAdress.add(lblMobil);

		textField_Mobil = new JTextField();
		textField_Mobil.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_Mobil.setColumns(10);
		textField_Mobil.setBounds(123, 314, 158, 25);
		panelAdress.add(textField_Mobil);

		textField_Navn = new JTextField();
		textField_Navn.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_Navn.setColumns(10);
		textField_Navn.setBounds(123, 205, 158, 25);
		panelAdress.add(textField_Navn);

		JLabel lblNewLabel_Email = new JLabel("Email");
		lblNewLabel_Email.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Email.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Email.setBounds(125, 230, 46, 30);
		panelAdress.add(lblNewLabel_Email);

		textField_Email = new JTextField();
		textField_Email.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_Email.setColumns(10);
		textField_Email.setBounds(123, 255, 158, 25);
		panelAdress.add(textField_Email);

		JButton btnConfirm = new JButton("Bekr\u00E6ft");
		btnConfirm.setActionCommand("Bekr\u00E6ft");
		btnConfirm.addActionListener(this::bekræftReservation);
		btnConfirm.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnConfirm.setBounds(408, 311, 93, 40);
		panelAdress.add(btnConfirm);

		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNote.setBounds(350, 180, 46, 30);
		panelAdress.add(lblNote);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(this::goBack);
		btnTilbage.setBounds(15, 312, 91, 40);
		panelAdress.add(btnTilbage);

		textField_AG = new JTextField();
		textField_AG.setHorizontalAlignment(SwingConstants.CENTER);
		textField_AG.setEditable(false);
		textField_AG.setColumns(10);
		textField_AG.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_AG.setBounds(218, 145, 63, 25);
		panelAdress.add(textField_AG);

		textField_Sted = new JTextField();
		textField_Sted.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Sted.setEditable(false);
		textField_Sted.setColumns(10);
		textField_Sted.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_Sted.setBounds(171, 117, 110, 25);
		panelAdress.add(textField_Sted);

		textField_DT = new JTextField();
		textField_DT.setHorizontalAlignment(SwingConstants.CENTER);
		textField_DT.setEditable(false);
		textField_DT.setColumns(10);
		textField_DT.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_DT.setBounds(433, 117, 122, 25);
		panelAdress.add(textField_DT);
		
		textPaneNote = new JTextPane();
		textPaneNote.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneNote.setBounds(350, 205, 205, 88);
		panelAdress.add(textPaneNote);
	}
	
	private void bekræftReservation(ActionEvent e) {
		try {
			uiCtrl.setNote(textPaneNote.getText());
			
			String navn = textField_Navn.getText();
			String mobil = textField_Mobil.getText();
			String email = textField_Email.getText();
			
			int tryParseNumber = Integer.parseInt(mobil);
			
			uiCtrl.endReservation(navn, mobil, email);

			this.setVisible(false);
			nextFrame.setVisible(true);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Indtast venligst et gyldigt telefon nummer");
		} catch (DataAccessException e1) {
			JOptionPane.showMessageDialog(null, "Fejl ved at gemme reservationen\n" + e1.getMessage());
		}
		
	}
	
	private void goBack(ActionEvent e) {
		setVisible(false);
		previousFrame.setVisible(true);
	}

	public void addTransitions(CreateOrderView createOrderView, ReservationBekræftet reservationBekræftet) {
		previousFrame = createOrderView;
		nextFrame = reservationBekræftet;
	}

	public void reset() {
		textField_AG.setText("");
		textField_Sted.setText("");
		textField_DT.setText("");
		
		textField_Mobil.setText("");
		textField_Navn.setText("");
		textField_Email.setText("");
		
		textPaneNote.setText("");
	}
}
