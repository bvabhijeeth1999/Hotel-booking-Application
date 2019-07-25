import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class afterbook extends JFrame {
	JLabel lblNewLabel;
	static int count1;
	private JPanel contentPane;
	private JTextField textField;
	static String aadhar;
	static long aadhar1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					afterbook frame = new afterbook();
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
	public afterbook() {
		connect = sqliteconnect.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAadharNumber = new JLabel("AADHAR NUMBER:");
		lblAadharNumber.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblAadharNumber.setBounds(111, 272, 158, 19);
		contentPane.add(lblAadharNumber);
		
		textField = new JTextField();
		textField.setBounds(302, 272, 265, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(56, 172, 725, 16);
		contentPane.add(lblNewLabel);
		
		if(hoteldisplay.count == -1) {
			count1 = hoteldisplay.count;
			lblNewLabel.setText("If you want to continue with the waiting list proceed with the book option");
			hoteldisplay.count = 0;
		}
		else {
			count1 = hoteldisplay.count;
			lblNewLabel.setText("Enter your aadhar number and you are good to go");
		}
		

		
		JButton btnProceed = new JButton("BOOK");
		btnProceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aadhar = textField.getText();
				
				int h = aadhar.length();
				if(h==12) {
				try {
					aadhar1 = Long.parseLong(aadhar);
		
				try{
						String query = "insert into pastuserbook (cityname,cin,cout,noofrooms,username,hotel,aadharnumber,waitinglist) values (?,?,?,?,?,?,?,?)";
						PreparedStatement ps = connect.prepareStatement(query);
						ps.setString(1,hotelInfo.citynam);
						ps.setString(2,hoteldisplay.df.format(hotelInfo.checkin));
						ps.setString(3,hoteldisplay.df.format(hotelInfo.checkout));
						ps.setString(4,Integer.toString(hotelInfo.noofrooms));
						ps.setString(5,hotelInfo.username);
						ps.setString(6,hoteldisplay.hname);
						ps.setString(7,aadhar);
						if(count1 == -1) {
							ps.setInt(8,1);
						}
						else {
							ps.setInt(8,0);
						}
						ps.execute();
						//JOptionPane.showMessageDialog(null,hotelInfo.username);
						ps.close();
						viewmybook.bajaj = 0;
						dispose();
						afteraadhar ar = new afteraadhar();
						ar.setVisible(true);
				}
				catch(Exception e5) {
					JOptionPane.showMessageDialog(null,e5);
				}
				finally {
					
					if (connect != null) {
					    try {
					      connect.close(); // <-- This is important
					    } catch (SQLException e6) {
					    	JOptionPane.showMessageDialog(null,e6);
					    }
					  }
				}
				}
				
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"aadhar number should contain only integers");
				}
				}
			else {
				JOptionPane.showMessageDialog(null,"please enter valid aadhar number(12 digits)");
			
			}
			}
		});
		btnProceed.setBounds(384, 326, 97, 25);
		contentPane.add(btnProceed);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				hoteldisplay hd = new hoteldisplay();
				hd.setVisible(true);
			}
		});
		btnGoBack.setBounds(26, 13, 97, 25);
		contentPane.add(btnGoBack);
		
		lblNewLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/afterbook.png"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(0, 0, 933, 634);
		contentPane.add(lblNewLabel);
	
		
	
	
	
	
	
	}
}
