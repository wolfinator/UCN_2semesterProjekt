package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ctrl.DataAccessException;
import ctrl.OrderCtrl;
import ctrl.ProductCtrl;
import model.Order;
import model.OrderLineItem;
import model.Product;
import ui.ReservationUI;

import java.awt.Color;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CreateOrderView extends JFrame {

	private ProductCtrl productCtrl;
	private OrderCtrl orderCtrl;
	
	private JFrame previousFrame;
	private JFrame nextFrame;
	private ConfirmationView confirm;
	private ReservationUI uiCtrl;
	
	private Product selectedProduct;

	private JPanel contentPane;
	private JTable orderTable;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane_forret;
	private JScrollPane scrollPane_hovedret;
	private JScrollPane scrollPane_drikkevare;
	private JTable table_forret;
	private JTable table_hovedret;
	private JTable table_drikkevare;
	private JLabel lblHovedretter;
	private JLabel lblForret;
	private JLabel lblDrikkevarer;
	private JTextField totalPrice;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;

	public CreateOrderView(ReservationUI reservationUI, ConfirmationView confirmationView) {
		uiCtrl = reservationUI;
		confirm = confirmationView;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});

		try {
			productCtrl = new ProductCtrl();
			orderCtrl = new OrderCtrl();
			orderCtrl.createOrder();
		} catch (DataAccessException e1) {
			JOptionPane.showMessageDialog(null, "Fejl ved at lave Ctrlere til view\n" + e1.getMessage());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane.setBounds(516, 73, 236, 221);
		contentPane.add(scrollPane);

		orderTable = new JTable();
		scrollPane.setViewportView(orderTable);

		lblNewLabel = new JLabel("Ordre");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(516, 25, 236, 44);
		contentPane.add(lblNewLabel);

		scrollPane_forret = new JScrollPane();
		scrollPane_forret.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_forret.setBounds(10, 73, 154, 221);
		contentPane.add(scrollPane_forret);

		table_forret = new JTable();
		table_forret.getSelectionModel().addListSelectionListener(this::productSelected);
		scrollPane_forret.setViewportView(table_forret);

		scrollPane_hovedret = new JScrollPane();
		scrollPane_hovedret.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_hovedret.setBounds(177, 73, 154, 221);
		contentPane.add(scrollPane_hovedret);

		table_hovedret = new JTable();
		table_hovedret.getSelectionModel().addListSelectionListener(this::productSelected1);
		scrollPane_hovedret.setViewportView(table_hovedret);

		scrollPane_drikkevare = new JScrollPane();
		scrollPane_drikkevare.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_drikkevare.setBounds(341, 73, 154, 221);
		contentPane.add(scrollPane_drikkevare);

		table_drikkevare = new JTable();
		table_drikkevare.getSelectionModel().addListSelectionListener(this::productSelected2);
		scrollPane_drikkevare.setViewportView(table_drikkevare);

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

		JButton btnAddForret = new JButton("Add");
		btnAddForret.addActionListener(this::addProduct);
		btnAddForret.setBounds(31, 305, 89, 23);
		contentPane.add(btnAddForret);

		JButton btnAddHovedRet = new JButton("Add");
		btnAddHovedRet.addActionListener(this::addProduct);
		btnAddHovedRet.setBounds(208, 305, 89, 23);
		contentPane.add(btnAddHovedRet);

		JButton btnAddDrikkevare = new JButton("Add");
		btnAddDrikkevare.addActionListener(this::addProduct);
		btnAddDrikkevare.setBounds(376, 305, 89, 23);
		contentPane.add(btnAddDrikkevare);

		totalPrice = new JTextField();
		totalPrice.setEditable(false);
		totalPrice.setBounds(663, 306, 89, 22);
		contentPane.add(totalPrice);
		totalPrice.setColumns(10);

		lblNewLabel_1 = new JLabel("Subtotal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(568, 303, 89, 23);
		contentPane.add(lblNewLabel_1);
		
		initProducts();

	}
	
	public void initProducts() {
		try {
			List<Product> products = productCtrl.findAll();
			List<Product> forretter = products.stream().filter((p) -> p.getType().getId() == 1)
					.collect(Collectors.toList());
			List<Product> hovedretter = products.stream().filter((p) -> p.getType().getId() == 2)
					.collect(Collectors.toList());
			List<Product> drikkevarer = products.stream().filter((p) -> p.getType().getId() == 3)
					.collect(Collectors.toList());
			DefaultTableModel forretModel = (DefaultTableModel) table_forret.getModel();
			DefaultTableModel hovedretModel = (DefaultTableModel) table_hovedret.getModel();
			DefaultTableModel drikkevareModel = (DefaultTableModel) table_drikkevare.getModel();
			
			forretModel.addColumn("Navn");
			forretModel.addColumn("Pris");
			hovedretModel.addColumn("Navn");
			hovedretModel.addColumn("Pris");
			drikkevareModel.addColumn("Navn");
			drikkevareModel.addColumn("Pris");

			for (Product p : forretter) {
				forretModel.addRow(new Object[] { p.getName(), p.getPrice() });
			}
			for (Product p : hovedretter) {
				hovedretModel.addRow(new Object[] { p.getName(), p.getPrice() });
			}
			for (Product p : drikkevarer) {
				drikkevareModel.addRow(new Object[] { p.getName(), p.getPrice() });
			}

		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(null, "Fejl ved at hente produkterne\n" + e.getMessage());
		}

	}

	private void goBack(ActionEvent e) {
		setVisible(false);
		previousFrame.setVisible(true);
	}

	private void goNext(ActionEvent e) {
		setVisible(false);
		nextFrame.setVisible(true);
	}
	
	private void addProduct(ActionEvent e) {
		if(selectedProduct == null) {
			JOptionPane.showMessageDialog(null, "Vælg venligst et produkt.");
		}else {
			try {
				updateOrder(orderCtrl.addProduct(selectedProduct.getName(), 1));
			} catch (DataAccessException e1) {
				JOptionPane.showMessageDialog(null, "Fejl ved at opdatere ordre\n" + e1.getMessage());
			}
		}
		
	}

	private void updateOrder(Order order) {
		DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
		if(orderModel.getColumnCount() == 0) {
			orderModel.addColumn("Navn");
			orderModel.addColumn("Antal");
		}
		orderModel.setRowCount(0);
		
		for (OrderLineItem oli : order.getOrderLineItem()) {
			orderModel.addRow(new Object[] {oli.getProduct().getName(), oli.getQuantity()});
		}
		
		uiCtrl.setOrder(order);
		totalPrice.setText(String.valueOf(order.getTotalPrice()));
	}
	
	private void productSelected(ListSelectionEvent e) {
		Product product = new Product();
		JTable table = table_forret;
		
		product.setName((String) table.getValueAt(table.getSelectedRow(), 0));
		product.setPrice((double) table.getValueAt(table.getSelectedRow(), 1));
		
		selectedProduct = product;
	}
	
	private void productSelected1(ListSelectionEvent e) {
		Product product = new Product();
		JTable table = table_hovedret;
		
		product.setName((String) table.getValueAt(table.getSelectedRow(), 0));
		product.setPrice((double) table.getValueAt(table.getSelectedRow(), 1));
		
		selectedProduct = product;
	}
	
	private void productSelected2(ListSelectionEvent e) {
		Product product = new Product();
		JTable table = table_drikkevare;
		
		product.setName((String) table.getValueAt(table.getSelectedRow(), 0));
		product.setPrice((double) table.getValueAt(table.getSelectedRow(), 1));
		
		selectedProduct = product;
	}

	public void addTransitions(CalendarTimeView calendarTimeView, ConfirmationView confirmationView) {
		previousFrame = calendarTimeView;
		nextFrame = confirmationView;
		
	}

	public void reset() {
		DefaultTableModel orderModel = (DefaultTableModel) orderTable.getModel();
		orderModel.setRowCount(0);
		orderModel.setColumnCount(0);
		selectedProduct = null;
		table_drikkevare.clearSelection();
		table_forret.clearSelection();
		table_hovedret.clearSelection();
	}
}
