import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;


public class viewmybook extends JFrame {
	static int bajaj = 0;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewmybook frame = new viewmybook();
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
	public viewmybook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("My Bookings");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mybookings mb = new mybookings();
				mb.setVisible(true);
			}
		});
		btnNewButton.setBounds(246, 252, 239, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Book a new room");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				hotelInfo hi = new hotelInfo();
				hi.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(246, 365, 239, 55);
		contentPane.add(btnNewButton_1);
		ImageIcon img  = new ImageIcon(this.getClass().getResource("/viewmybook.png"));
		
		
			JButton btnExit = new JButton("Log Out");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					login ln = new login();
					ln.frame.setVisible(true);
				}
			});
			btnExit.setBounds(12, 13, 97, 25);
			contentPane.add(btnExit);
		
			JLabel label = new JLabel("");
			label.setIcon(img);
			label.setBounds(0, 0, 758, 588);
			contentPane.add(label);
	
	
	
	}

}
