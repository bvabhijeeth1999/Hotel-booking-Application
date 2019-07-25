import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;
import java.text.*;
import java.util.Calendar;

public class hotelInfo extends JFrame {
	static String username;
	static String citynam = new String();
	static Date checkin;
	static Date checkout;
	static int noofrooms;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JComboBox comboBox;
	private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	static long days1;
	static int noofpeople;
	/**
	 * Launch the application.
	 */

	
	private JTextField textField;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hotelInfo window = new hotelInfo();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connect;
	private JTextField textField_1;
	public void fillComboBox(){
		
		connect = sqliteconnect.dbconnector();

		try {
			String query = "select * from cities";
			PreparedStatement pst = connect.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				comboBox.addItem(rs.getString("city name"));
			}
			
			rs.close();
			pst.close();
		}
		catch(Exception ei){
			JOptionPane.showMessageDialog(null,"Exception caught:" + ei);
		}
	}

	/**
	 * Create the frame.
	 */
	
	public hotelInfo() {
		username = login.name;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChooseTheHotel = new JLabel("Choose your city from below:");
		lblChooseTheHotel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblChooseTheHotel.setBounds(81, 30, 334, 56);
		contentPane.add(lblChooseTheHotel);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(127, 111, 213, 22);
		contentPane.add(comboBox);
		fillComboBox();
		
		
		JLabel lblCheckInDate = new JLabel("CHECK  IN   DATE");
		lblCheckInDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblCheckInDate.setBounds(81, 222, 202, 22);
		contentPane.add(lblCheckInDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(315, 223, 100, 22);
		contentPane.add(dateChooser);

		textField_1 = new JTextField();
		textField_1.setBounds(315, 317, 100, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		
				
		
		
		JLabel lblCheckOutDate = new JLabel("CHECK OUT DATE");
		lblCheckOutDate.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblCheckOutDate.setBounds(81, 270, 202, 22);
		contentPane.add(lblCheckOutDate);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(315, 270, 100, 22);
		contentPane.add(dateChooser_1);
		
		
		
		textField = new JTextField();
		textField.setBounds(315, 363, 103, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNumberOfRooms = new JLabel("NUMBER OF ROOMS");
		lblNumberOfRooms.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNumberOfRooms.setBounds(81, 363, 202, 22);
		contentPane.add(lblNumberOfRooms);
		

		
		JButton btnCheckAvailability = new JButton("PROCEED");
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				noofpeople = Integer.parseInt(textField_1.getText());
				noofrooms = Integer.parseInt(textField.getText());
				if(noofpeople <= (2*noofrooms) ) {
				try {
				java.sql.Date sqlDate3 = new java.sql.Date(dateChooser.getDate().getTime());
				checkin = sqlDate3;
				java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				long diff = checkin.getTime() - today.getTime();
				days1 = (diff/(1000*60*60*24));
				}
				catch(Exception e4) {
					
				}
				
			
				
				if(days1 >= 0) {
				try {
					citynam = comboBox.getSelectedItem().toString();
					java.sql.Date sqlDate = new java.sql.Date(dateChooser.getDate().getTime());
					checkin = sqlDate;
					java.sql.Date sqlDate1 = new java.sql.Date(dateChooser_1.getDate().getTime());
					checkout = sqlDate1;
					noofrooms = Integer.parseInt(textField.getText());
				
		     
					if(checkin.compareTo(checkout) <=0) {
						dispose();
			        
						hoteldisplay hd = new hoteldisplay();
						hd.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Ensure that check-in date is before check-out date");
					}
				
				}
				catch(Exception ei){
					JOptionPane.showMessageDialog(null,"Fill all fields");
				}
				finally {
					  if (connect != null) {
					    try {
					      connect.close(); // <-- This is important
					    } catch (SQLException e) {
					    	JOptionPane.showMessageDialog(null,e);
					    }
					  }
					  
				}
				}
				else {
					JOptionPane.showMessageDialog(null,"checkin date cannot be before today's date");
				}
			}
				else {
					JOptionPane.showMessageDialog(null, "Each room can be occupied by a maximum of 2 people");
				}
				}
				catch(Exception e23) {
					JOptionPane.showMessageDialog(null,"no of people/rooms should be an integer");
				}
			}
		});
		btnCheckAvailability.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCheckAvailability.setBounds(259, 414, 156, 25);
		contentPane.add(btnCheckAvailability);
	
		
		ImageIcon img  = new ImageIcon(this.getClass().getResource("/HotelInfo.png"));
		
		if(viewmybook.bajaj == 0) {
			JButton btnGoBack = new JButton("Go back");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					viewmybook vmb = new viewmybook();
					vmb.setVisible(true);
				}
			});
			btnGoBack.setBounds(12, 13, 97, 25);
			contentPane.add(btnGoBack);
		}
		
		JLabel lblNoOfPersons = new JLabel("NO OF PERSONS");
		lblNoOfPersons.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNoOfPersons.setBounds(81, 317, 202, 22);
		contentPane.add(lblNoOfPersons);
		
		
		JLabel label = new JLabel("");
		label.setIcon(img);
		label.setBounds(-35, 0, 710, 606);
		contentPane.add(label);
	
	
	}
}
