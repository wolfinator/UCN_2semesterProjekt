package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateOrderView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JLabel lblHovedretter;
	private JLabel lblForret;
	private JLabel lblDrikkevarer;
	private JTextField textField;
	private JLabel lblNewLabel_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrderView frame = new CreateOrderView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CreateOrderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane.setBounds(516, 73, 236, 221);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("Ordre");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(516, 25, 236, 44);
		contentPane.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_1.setBounds(10, 73, 154, 221);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_2.setBounds(177, 73, 154, 221);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_3.setBounds(341, 73, 154, 221);
		contentPane.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		lblHovedretter = new JLabel("Forret");
		lblHovedretter.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHovedretter.setHorizontalAlignment(SwingConstants.CENTER);
		lblHovedretter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHovedretter.setBounds(10, 25, 154, 44);
		contentPane.add(lblHovedretter);
		
		lblForret = new JLabel("Hovedret");
		lblForret.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblForret.setHorizontalAlignment(SwingConstants.CENTER);
		lblForret.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForret.setBounds(177, 25, 154, 44);
		contentPane.add(lblForret);
		
		lblDrikkevarer = new JLabel("Drikkevarer");
		lblDrikkevarer.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblDrikkevarer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrikkevarer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDrikkevarer.setBounds(341, 25, 154, 44);
		contentPane.add(lblDrikkevarer);
		
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(this::goBack);
		btnTilbage.setBounds(10, 366, 89, 23);
		contentPane.add(btnTilbage);
		
		JButton btnVidere = new JButton("Videre");
		btnVidere.addActionListener(this::goNext);
		btnVidere.setBounds(663, 366, 89, 23);
		contentPane.add(btnVidere);
		
		JButton btnSkip = new JButton("Skip");
		btnSkip.addActionListener(this::goNext);
		btnSkip.setBounds(579, 366, 89, 23);
		contentPane.add(btnSkip);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(31, 305, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.setBounds(208, 305, 89, 23);
		contentPane.add(btnAdd_1);
		
		JButton btnAdd_2 = new JButton("Add");
		btnAdd_2.setBounds(376, 305, 89, 23);
		contentPane.add(btnAdd_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(663, 306, 89, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Subtotal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(568, 303, 89, 23);
		contentPane.add(lblNewLabel_1);

	}
	
	private void goBack(ActionEvent e) {
		setVisible(false);
		LocationView.calendarTimeView.setVisible(true);
	}
	
	private void goNext(ActionEvent e) {
		setVisible(false);
		LocationView.confirmationView.setVisible(true);
	}
}
