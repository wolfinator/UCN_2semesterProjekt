package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Confirmation extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Mobil;
	private JTextField textField_Navn;
	private JTextField textField_Email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmation frame = new Confirmation();
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
	public Confirmation() {
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
		lblNewLabel.setIcon(new ImageIcon(Confirmation.class.getResource("/gui/Pictures/ramen.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Confirmation.class.getResource("/gui/Pictures/ramen.png")));
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
		lblNewLabel_Sted.setBounds(90, 110, 46, 30);
		panelAdress.add(lblNewLabel_Sted);
		
		JLabel lblNewLabel_AG = new JLabel("Antal gæster:");
		lblNewLabel_AG.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_AG.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_AG.setBounds(90, 140, 109, 30);
		panelAdress.add(lblNewLabel_AG);
		
		JLabel lblNewLabel_DT = new JLabel("Dato og tid:");
		lblNewLabel_DT.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_DT.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_DT.setBounds(330, 110, 93, 30);
		panelAdress.add(lblNewLabel_DT);
		
		JLabel lblNewLabel_Navn = new JLabel("Navn");
		lblNewLabel_Navn.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Navn.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Navn.setBounds(90, 180, 46, 30);
		panelAdress.add(lblNewLabel_Navn);
		
		JLabel lblNewLabel_Mobil = new JLabel("Mobil");
		lblNewLabel_Mobil.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Mobil.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Mobil.setBounds(330, 180, 46, 30);
		panelAdress.add(lblNewLabel_Mobil);
		
		textField_Mobil = new JTextField();
		textField_Mobil.setColumns(10);
		textField_Mobil.setBounds(328, 205, 150, 25);
		panelAdress.add(textField_Mobil);
		
		textField_Navn = new JTextField();
		textField_Navn.setColumns(10);
		textField_Navn.setBounds(88, 205, 150, 25);
		panelAdress.add(textField_Navn);
		
		JLabel lblNewLabel_Email = new JLabel("Email");
		lblNewLabel_Email.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Email.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_Email.setBounds(90, 230, 46, 30);
		panelAdress.add(lblNewLabel_Email);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		textField_Email.setBounds(88, 255, 150, 25);
		panelAdress.add(textField_Email);
		
		JButton btnNewButton = new JButton("Bekræft");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnNewButton.setBounds(225, 310, 120, 35);
		panelAdress.add(btnNewButton);
	}

	public void run() {
		try {
			Confirmation frame = new Confirmation();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		
	}

	}
}	
