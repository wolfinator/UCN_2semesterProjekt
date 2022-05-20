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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuestCountView extends JFrame {

	private JPanel contentPane;

	public int guestCount;

	/**
	 * Create the frame.
	 * 
	 * @param string
	 * @param locationView
	 */
	public GuestCountView() {
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
		lblLeftPicture.setIcon(new ImageIcon(GuestCountView.class.getResource("/ramen.png")));

		JLabel lblRightPicture = new JLabel("");
		lblRightPicture.setIcon(new ImageIcon(GuestCountView.class.getResource("/ramen.png")));
		lblRightPicture.setBounds(302, 22, 298, 267);
		panelPicture.add(lblRightPicture);

		JPanel panelAdress = new JPanel();
		panelAdress.setBackground(Color.WHITE);
		panelAdress.setBounds(0, 0, 600, 372);
		contentPane.add(panelAdress);
		panelAdress.setLayout(null);

		JLabel lblGuestCountText = new JLabel("  Antal personer");
		lblGuestCountText.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestCountText.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblGuestCountText.setBounds(0, 115, 600, 30);
		panelAdress.add(lblGuestCountText);

		JButton btnSelect1 = new JButton("1");
		btnSelect1.setBackground(Color.LIGHT_GRAY);
		btnSelect1.addActionListener(this::guestCountSelected);
		btnSelect1.setBounds(50, 170, 70, 50);
		panelAdress.add(btnSelect1);

		JButton btnSelect2 = new JButton("2");
		btnSelect2.setBackground(Color.LIGHT_GRAY);
		btnSelect2.addActionListener(this::guestCountSelected);
		btnSelect2.setBounds(160, 170, 70, 50);
		panelAdress.add(btnSelect2);

		JButton btnSelect3 = new JButton("3");
		btnSelect3.setBackground(Color.LIGHT_GRAY);
		btnSelect3.addActionListener(this::guestCountSelected);
		btnSelect3.setBounds(270, 170, 70, 50);
		panelAdress.add(btnSelect3);

		JButton btnSelect4 = new JButton("4");
		btnSelect4.setBackground(Color.LIGHT_GRAY);
		btnSelect4.addActionListener(this::guestCountSelected);
		btnSelect4.setBounds(380, 170, 70, 50);
		panelAdress.add(btnSelect4);

		JButton btnSelect5 = new JButton("5");
		btnSelect5.setBackground(Color.LIGHT_GRAY);
		btnSelect5.addActionListener(this::guestCountSelected);
		btnSelect5.setBounds(490, 170, 70, 50);
		panelAdress.add(btnSelect5);

		JButton btnSelect6 = new JButton("6");
		btnSelect6.addActionListener(this::guestCountSelected);
		btnSelect6.setBackground(Color.LIGHT_GRAY);
		btnSelect6.setBounds(50, 250, 70, 50);
		panelAdress.add(btnSelect6);

		JButton btnSelect7 = new JButton("7");
		btnSelect7.addActionListener(this::guestCountSelected);
		btnSelect7.setBackground(Color.LIGHT_GRAY);
		btnSelect7.setBounds(160, 250, 70, 50);
		panelAdress.add(btnSelect7);

		JButton btnSelect8 = new JButton("8");
		btnSelect8.addActionListener(this::guestCountSelected);
		btnSelect8.setBackground(Color.LIGHT_GRAY);
		btnSelect8.setBounds(270, 250, 70, 50);
		panelAdress.add(btnSelect8);

		JButton btnSelect9 = new JButton("9");
		btnSelect9.addActionListener(this::guestCountSelected);
		btnSelect9.setBackground(Color.LIGHT_GRAY);
		btnSelect9.setBounds(380, 250, 70, 50);
		panelAdress.add(btnSelect9);

		JButton btnSelect10 = new JButton("10");
		btnSelect10.addActionListener(this::guestCountSelected);
		btnSelect10.setBackground(Color.LIGHT_GRAY);
		btnSelect10.setBounds(490, 250, 70, 50);
		panelAdress.add(btnSelect10);

		JLabel lblOver10Personer = new JLabel("  Er i over 10 personer? ");
		lblOver10Personer.setHorizontalAlignment(SwingConstants.CENTER);
		lblOver10Personer.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblOver10Personer.setBounds(0, 311, 590, 30);
		panelAdress.add(lblOver10Personer);

		JLabel lblRingTilTlf = new JLabel("Ring til 98122888");
		lblRingTilTlf.setHorizontalAlignment(SwingConstants.CENTER);
		lblRingTilTlf.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblRingTilTlf.setBounds(0, 326, 590, 30);
		panelAdress.add(lblRingTilTlf);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(this::goBack);
		btnTilbage.setBounds(20, 315, 91, 40);
		panelAdress.add(btnTilbage);
	}

	private void guestCountSelected(ActionEvent e) {
		setVisible(false);
		JButton buttonThatWasClicked = (JButton) e.getSource();
		String btnText = buttonThatWasClicked.getText();
		LocationView.confirmationView.textField_AG.setText(btnText);
		guestCount = Integer.parseInt(btnText);
		LocationView.calendarTimeView.setVisible(true);
	}

	private void goBack(ActionEvent e) {
		setVisible(false);
		LocationView.locationView.setVisible(true);
	}
}
