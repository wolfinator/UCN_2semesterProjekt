package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.DataAccessException;
import ctrl.ReservationCtrl;
import model.Reservation;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ReservationList extends JFrame {
	private static ReservationList frame;
	private JPanel contentPane;
	private ReservationCtrl reservationCtrl;
	private JList reservationList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ReservationList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		new Thread(()->{
			while(true) {
				
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

	/**
	 * Create the frame.
	 */
	public ReservationList() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		reservationList = new JList<>();
		scrollPane.setViewportView(reservationList);
		
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

	private void updateReservationList() {
		SwingUtilities.invokeLater(() -> {
			List<Reservation> reservations = null;
			try {
				reservations = reservationCtrl.findAll();
				DefaultListModel<String> list = new DefaultListModel<>();
				for (Reservation r : reservations) {
					// should probably be .toString instead
					list.addElement(r.getId() + ": Customer[" + r.getCustomer().getFirstName() + "] Date["  + r.getDate() + "] Guests[" + r.getGuestCount() + "] Note[" + r.getNote() + "]");
					
				}
				this.reservationList.setModel(list);
			} catch (DataAccessException e) {
				JOptionPane.showMessageDialog(null, "Could not update list");
				e.printStackTrace();
			}
		});
	}

	protected JList getList() {
		return reservationList;
	}
}
