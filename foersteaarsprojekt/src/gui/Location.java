package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Location extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location frame = new Location();
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
	public Location() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.DARK_GRAY);
		panel1.setBounds(0, 0, 300, 372);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Location.class.getResource("/Pictures/Skærmbillede 2022-05-12 kl. 13.13.24.png")));
		lblNewLabel_1.setBounds(0, 0, 300, 372);
		panel1.add(lblNewLabel_1);
		
		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(300, 0, 300, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bestil bord på");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNewLabel.setBounds(66, 40, 165, 55);
		panelAdress.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aalborg");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GuestCountView GuestCount = new GuestCountView();
				GuestCount.run();
			}
		});
		btnNewButton.setBounds(66, 118, 165, 45);
		panelAdress.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Randers");
		btnNewButton_1.setBounds(66, 175, 165, 45);
		panelAdress.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vejle");
		btnNewButton_2.setBounds(66, 232, 165, 45);
		panelAdress.add(btnNewButton_2);
		
		JPanel Borderpanel = new JPanel();
		Borderpanel.setBounds(0, 0, 600, 322);
		contentPane.add(Borderpanel);
	}
}
