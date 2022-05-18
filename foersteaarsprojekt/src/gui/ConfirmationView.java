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

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.event.ActionListener;
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
	 */
	public ConfirmationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(0, 0, 600, 100);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 271, 100);
		panel1.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(ConfirmationView.class.getResource("/gui/Pictures/ramen.png")));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ConfirmationView.class.getResource("/gui/Pictures/ramen.png")));
		lblNewLabel_1.setBounds(302, 22, 298, 267);
		panel1.add(lblNewLabel_1);

		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(0, 0, 600, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);

		JLabel lblNewLabel_Sted = new JLabel("Sted:");
		lblNewLabel_Sted.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Sted.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Sted.setBounds(125, 110, 46, 30);
		panelAdress.add(lblNewLabel_Sted);

		JLabel lblNewLabel_AG = new JLabel("Antal g\u00E6ster:");
		lblNewLabel_AG.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_AG.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_AG.setBounds(125, 140, 93, 30);
		panelAdress.add(lblNewLabel_AG);

		JLabel lblNewLabel_DT = new JLabel("Dato og tid:");
		lblNewLabel_DT.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_DT.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_DT.setBounds(350, 110, 93, 30);
		panelAdress.add(lblNewLabel_DT);

		JLabel lblNewLabel_Navn = new JLabel("Navn");
		lblNewLabel_Navn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Navn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Navn.setBounds(125, 180, 46, 30);
		panelAdress.add(lblNewLabel_Navn);

		JLabel lblNewLabel_Mobil = new JLabel("Mobil");
		lblNewLabel_Mobil.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Mobil.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Mobil.setBounds(125, 280, 46, 30);
		panelAdress.add(lblNewLabel_Mobil);

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

		JButton btnNewButton = new JButton("Bekr\u00E6ft");
		btnNewButton.setActionCommand("Bekr\u00E6ft");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bekræftReservation();
			}

			
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnNewButton.setBounds(408, 311, 93, 40);
		panelAdress.add(btnNewButton);

		JLabel lblNewLabel_Note = new JLabel("Note");
		lblNewLabel_Note.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Note.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Note.setBounds(350, 180, 46, 30);
		panelAdress.add(lblNewLabel_Note);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LocationView.calendarTimeView.setVisible(true);
			}
		});
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane.setBounds(350, 205, 205, 88);
		panelAdress.add(textPane);
	}
	
	private void bekræftReservation() {
		try {
			LocationView.reservationCtrl.endReservation(
					textField_Navn.getText(), 
					textField_Mobil.getText(), 
					textField_Email.getText());
			
			JOptionPane.showMessageDialog(null, "Reservation bekræftet");
			LocationView.calendarTimeView.comboBox.removeAllItems();
			LocationView.calendarTimeView.btnNext.setEnabled(false);
			this.setVisible(false);
			LocationView.locationView.setVisible(true);
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(null, "Fejl ved at gemme reservationen\n" + e.getMessage());
		}
		
	}
}
