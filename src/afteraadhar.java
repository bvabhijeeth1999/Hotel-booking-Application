import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class afteraadhar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					afteraadhar frame = new afteraadhar();
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
	Connection connect;
	public afteraadhar() {
		connect = sqliteconnect.dbconnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewMyBookings = new JButton("View My Bookings");
		btnViewMyBookings.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnViewMyBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				mybookings mb = new mybookings();
				mb.setVisible(true);
				
			}
		});
		btnViewMyBookings.setBounds(211, 246, 207, 25);
		contentPane.add(btnViewMyBookings);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(502, 446, 106, 33);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/aadharafter_1.png"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(-75, 0, 933, 634);
		contentPane.add(lblNewLabel);


	}
}
