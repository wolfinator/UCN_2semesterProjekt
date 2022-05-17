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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuestCountView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestCountView frame = new GuestCountView();
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
	public GuestCountView() {
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
		lblNewLabel.setIcon(new ImageIcon(GuestCountView.class.getResource("/gui/Pictures/ramen.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(GuestCountView.class.getResource("/gui/Pictures/ramen.png")));
		lblNewLabel_1.setBounds(302, 22, 298, 267);
		panel1.add(lblNewLabel_1);
		
		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(0, 0, 600, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("  Antal personer");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel_2.setBounds(0, 120, 600, 30);
		panelAdress.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setBounds(50, 180, 70, 50);
		panelAdress.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("2");
		btnNewButton_2.setBounds(160, 180, 70, 50);
		panelAdress.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.setBounds(270, 180, 70, 50);
		panelAdress.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setBounds(380, 180, 70, 50);
		panelAdress.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setBounds(490, 180, 70, 50);
		panelAdress.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setBounds(50, 260, 70, 50);
		panelAdress.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("7");
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.setBounds(160, 260, 70, 50);
		panelAdress.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.setBounds(270, 260, 70, 50);
		panelAdress.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setBackground(Color.WHITE);
		btnNewButton_9.setBounds(380, 260, 70, 50);
		panelAdress.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("10");
		btnNewButton_10.setBackground(Color.WHITE);
		btnNewButton_10.setBounds(490, 260, 70, 50);
		panelAdress.add(btnNewButton_10);
		
		JLabel lblNewLabel_2_1 = new JLabel("  Er i over 10 personer? ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(0, 322, 600, 30);
		panelAdress.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Ring til 98122888");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(0, 342, 600, 30);
		panelAdress.add(lblNewLabel_2_1_1);
	}

	public void run() {
		try {
			GuestCountView frame = new GuestCountView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		
	}

	}
}	
