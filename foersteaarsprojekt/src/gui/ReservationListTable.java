package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;
import model.Customer;
import model.Reservation;

public class ReservationListTable extends JFrame {

	private JPanel contentPane;
	private JTable reservationTable;
	private ReservationCtrl reservationCtrl;
	private static ReservationListTable frame;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ReservationListTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		new Thread(() -> {
			while (true) {

				try {
					Thread.sleep(2000);
					frame.updateReservationList();
				} catch (InterruptedException e) {
					// should never happen
					e.printStackTrace();
				}
			}
		}).run();
	}

	private void updateReservationList() {
		try {
			List<Reservation> reservations = reservationCtrl.findAll();
			DefaultTableModel dtm = new DefaultTableModel(new Object[][] {},
					new String[] { "Id", "Customer", "PhoneNo", "Date", "Guest Count" }) {
				Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Object.class,
						Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			for (Reservation r : reservations) {
				Customer c = r.getCustomer();
				int id = r.getId();
				String customerName = c.getName();
				String phoneNo = c.getPhoneNo();
				LocalDateTime date = r.getDate();
				int guestCount = r.getGuestCount();
				
				dtm.addRow(new Object[]{id, customerName, phoneNo, date, guestCount});
			}
			reservationTable.setModel(dtm);
			reservationTable.getColumnModel().getColumn(0).setPreferredWidth(23);
			reservationTable.getColumnModel().getColumn(1).setPreferredWidth(107);
			reservationTable.getColumnModel().getColumn(2).setPreferredWidth(103);
			reservationTable.getColumnModel().getColumn(3).setPreferredWidth(109);
		} catch (DataAccessException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public ReservationListTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		reservationTable = new JTable();
		reservationTable.setDragEnabled(false);
		reservationTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Customer", "PhoneNo", "Date", "Guest Count"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		reservationTable.getColumnModel().getColumn(0).setPreferredWidth(23);
		reservationTable.getColumnModel().getColumn(1).setPreferredWidth(107);
		reservationTable.getColumnModel().getColumn(2).setPreferredWidth(103);
		reservationTable.getColumnModel().getColumn(3).setPreferredWidth(109);
		scrollPane.setViewportView(reservationTable);

		JTextArea txtTitle = new JTextArea();
		txtTitle.setEditable(false);
		txtTitle.setText("Reservation Overview");
		contentPane.add(txtTitle, BorderLayout.NORTH);
		
		initialize();
	}

	private void initialize() {
		try {
			reservationCtrl = new ReservationCtrl();
			updateReservationList();
		} catch (DataAccessException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

}
