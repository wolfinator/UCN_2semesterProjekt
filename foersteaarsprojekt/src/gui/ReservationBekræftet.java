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
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationBekræftet extends JFrame {

	private JPanel contentPane;
	private JTextField textMobil;
	private JTextField textNavn;
	private JTextField textEmail;
	private JTextField antalGæster;
	private JTextField datoTid;
	private JTextField textSted;

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
	 */
	public ReservationBekræftet() {
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
		
		JLabel lblSted = new JLabel("Sted:");
		lblSted.setHorizontalAlignment(SwingConstants.LEFT);
		lblSted.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSted.setBounds(377, 117, 46, 30);
		panelAdress.add(lblSted);
		
		JLabel lblGuestCount = new JLabel("Antal g\u00E6ster:");
		lblGuestCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuestCount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGuestCount.setBounds(330, 158, 93, 30);
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
		btnLuk.setFont(new Font("Dialog", Font.BOLD, 15));
		btnLuk.setActionCommand("Bekr\u00E6ft");
		btnLuk.setBounds(285, 317, 93, 40);
		panelAdress.add(btnLuk);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNote.setBounds(371, 217, 46, 30);
		panelAdress.add(lblNote);
		
		antalGæster = new JTextField();
		antalGæster.setHorizontalAlignment(SwingConstants.CENTER);
		antalGæster.setEditable(false);
		antalGæster.setColumns(10);
		antalGæster.setBorder(new LineBorder(new Color(0, 0, 0)));
		antalGæster.setBounds(433, 163, 63, 25);
		panelAdress.add(antalGæster);
		
		datoTid = new JTextField();
		datoTid.setHorizontalAlignment(SwingConstants.CENTER);
		datoTid.setEditable(false);
		datoTid.setColumns(10);
		datoTid.setBorder(new LineBorder(new Color(0, 0, 0)));
		datoTid.setBounds(433, 76, 122, 25);
		panelAdress.add(datoTid);
		
		JTextPane textPaneNote = new JTextPane();
		textPaneNote.setEditable(false);
		textPaneNote.setEnabled(false);
		textPaneNote.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneNote.setBounds(427, 224, 205, 88);
		panelAdress.add(textPaneNote);
		
		textSted = new JTextField();
		textSted.setHorizontalAlignment(SwingConstants.CENTER);
		textSted.setEditable(false);
		textSted.setColumns(10);
		textSted.setBorder(new LineBorder(new Color(0, 0, 0)));
		textSted.setBounds(433, 122, 122, 25);
		panelAdress.add(textSted);
		
		JLabel lblOrdreBekrftet = new JLabel("Ordre Bekr\u00E6ftet");
		lblOrdreBekrftet.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrdreBekrftet.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblOrdreBekrftet.setBounds(241, 23, 164, 30);
		panelAdress.add(lblOrdreBekrftet);
	}
}
