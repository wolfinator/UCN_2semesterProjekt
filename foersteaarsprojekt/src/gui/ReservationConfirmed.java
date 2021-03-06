package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.Customer;
import model.Reservation;
import ui.ReservationUI;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationConfirmed extends JFrame {

	private JPanel contentPane;
	private JTextField textMobil;
	private JTextField textNavn;
	private JTextField textEmail;
	private JTextField antalGaester;
	private JTextField datoTid;
	
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
//					ReservationBekræftet frame = new ReservationBekræftet();
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
	public ReservationConfirmed(ReservationUI reservationUI) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelAdress = new JPanel();
		panelAdress.setLayout(null);
		panelAdress.setBackground(Color.WHITE);
		contentPane.add(panelAdress, BorderLayout.CENTER);
		
		JLabel lblGuestCount = new JLabel("Antal g\u00E6ster:");
		lblGuestCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuestCount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGuestCount.setBounds(332, 117, 93, 30);
		panelAdress.add(lblGuestCount);
		
		JLabel lblDateTime = new JLabel("Dato og tid:");
		lblDateTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateTime.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDateTime.setBounds(350, 76, 93, 30);
		panelAdress.add(lblDateTime);
		
		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNavn.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNavn.setBounds(39, 71, 46, 30);
		panelAdress.add(lblNavn);
		
		JLabel lblMobil = new JLabel("Mobil");
		lblMobil.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobil.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMobil.setBounds(38, 158, 46, 30);
		panelAdress.add(lblMobil);
		
		textMobil = new JTextField();
		textMobil.setEditable(false);
		textMobil.setColumns(10);
		textMobil.setBorder(new LineBorder(new Color(0, 0, 0)));
		textMobil.setBounds(94, 163, 158, 25);
		panelAdress.add(textMobil);
		
		textNavn = new JTextField();
		textNavn.setEditable(false);
		textNavn.setColumns(10);
		textNavn.setBorder(new LineBorder(new Color(0, 0, 0)));
		textNavn.setBounds(95, 76, 158, 25);
		panelAdress.add(textNavn);
		
		JLabel lblNewLabel_Email = new JLabel("Email");
		lblNewLabel_Email.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Email.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_Email.setBounds(39, 117, 46, 30);
		panelAdress.add(lblNewLabel_Email);
		
		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
		textEmail.setBounds(95, 115, 158, 25);
		panelAdress.add(textEmail);
		
		JButton btnLuk = new JButton("Luk");
		btnLuk.addActionListener(this::goNext);
		btnLuk.setFont(new Font("Dialog", Font.BOLD, 15));
		btnLuk.setActionCommand("Bekr\u00E6ft");
		btnLuk.setBounds(285, 317, 93, 40);
		panelAdress.add(btnLuk);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNote.setBounds(371, 217, 46, 30);
		panelAdress.add(lblNote);
		
		antalGaester = new JTextField();
		antalGaester.setHorizontalAlignment(SwingConstants.CENTER);
		antalGaester.setEditable(false);
		antalGaester.setColumns(10);
		antalGaester.setBorder(new LineBorder(new Color(0, 0, 0)));
		antalGaester.setBounds(435, 122, 87, 25);
		panelAdress.add(antalGaester);
		
		datoTid = new JTextField();
		datoTid.setHorizontalAlignment(SwingConstants.CENTER);
		datoTid.setEditable(false);
		datoTid.setColumns(10);
		datoTid.setBorder(new LineBorder(new Color(0, 0, 0)));
		datoTid.setBounds(433, 76, 122, 25);
		panelAdress.add(datoTid);
		
		textPaneNote = new JTextPane();
		textPaneNote.setEditable(false);
		textPaneNote.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneNote.setBounds(427, 224, 205, 88);
		panelAdress.add(textPaneNote);
		
		JLabel lblOrdreBekrftet = new JLabel("Ordre Bekr\u00E6ftet");
		lblOrdreBekrftet.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdreBekrftet.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblOrdreBekrftet.setBounds(241, 23, 164, 30);
		panelAdress.add(lblOrdreBekrftet);
	}

	public void setReservation(Reservation r) {
		Customer c = r.getCustomer();
		textMobil.setText(c.getPhoneNo());
		textNavn.setText(c.getName());
		textEmail.setText(c.getEmail());
		antalGaester.setText(String.valueOf(r.getGuestCount()));
		datoTid.setText(r.getDate().toString());
		textPaneNote.setText(r.getNote());
	}
	
	private void goNext(ActionEvent e) {
		setVisible(false);
		nextFrame.setVisible(true);
	}

	public void addTransitions(LocationView locationView) {
		nextFrame = locationView;
		
	}
}
