import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.toedter.calendar.*;
public class Newuser extends JFrame {
	static DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	private JPanel contentPane;
	private JTextField textField;
	static long days;
	static java.sql.Date sqlDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Newuser frame = new Newuser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



/**
 * Java email validation program
 * 
 * @author pankaj
 *
 */
	



	/**
	 * Create the frame.
	 */
	Connection connect = null;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public Newuser() {
		
		
		//code which connects to the database
		connect = sqliteconnect.dbconnector();
		//end of the connection code.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		EmailValidator ev = new EmailValidator();
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblUsername.setBounds(145, 279, 124, 22);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblPassword.setBounds(145, 312, 124, 22);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(324, 389, 169, 68);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(324, 470, 169, 22);
		contentPane.add(dateChooser);
	
		

		JButton btnSave = new JButton("Save and Go back");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if(!(textField.getText().equals("")) && !(textField_2.getText().equals("")) && !(textField_3.getText().equals("")) && !(textField_4.getText().equals("")) && !(textField_5.getText().equals(""))) {
				try {
				sqlDate = new java.sql.Date(dateChooser.getDate().getTime());
				java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				long diff = today.getTime() - sqlDate.getTime();
				days = (diff/(1000*60*60*24));
				}
				catch(Exception e5) {
					//JOptionPane.showMessageDialog(null,"Fill all fields");
				}
				
				
				if(days>0 && ev.validateEmail(textField_5.getText())) {
				try {
					String query = "insert into hotel (username,password,name,dateofbirth,address,emailid) values (?,?,?,?,?,?)";
					PreparedStatement pst = connect.prepareStatement(query);
					pst.setString(1,textField_3.getText());
					pst.setString(2,textField_2.getText());
					pst.setString(3,textField_4.getText());
					
					pst.setString(4,df.format(sqlDate));
					pst.setString(5,textField.getText());
					pst.setString(6,textField_5.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Saved Successfully");
					
					pst.close();
					connect.close();
					dispose();
					login lo = new login();
					lo.frame.setVisible(true);
					
				}
				catch(Exception ei){
					JOptionPane.showMessageDialog(null,"Exception caught:" + ei);
				}
				finally {
					if (connect != null) {
					    try {
					      connect.close(); // <-- This is important
					    } catch (SQLException e1) {
					    	JOptionPane.showMessageDialog(null,e1);
					    }
					  }
				
				}
				}
				else {
					if( ev.validateEmail(textField_5.getText())) {
						JOptionPane.showMessageDialog(null,"Please enter valid date of birth");
					}
					else {
						JOptionPane.showMessageDialog(null,"Please enter valid Email Address");
					}
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Fill all Fields");
				}
			}
		});
		btnSave.setBounds(273, 548, 182, 34);
		contentPane.add(btnSave);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblName.setBounds(145, 170, 124, 22);
		contentPane.add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(324, 317, 169, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("DOB");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateOfBirth.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblDateOfBirth.setBounds(145, 468, 124, 22);
		contentPane.add(lblDateOfBirth);
		
		textField_3 = new JTextField();
		textField_3.setBounds(324, 281, 169, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblAddress.setBounds(145, 390, 124, 27);
		contentPane.add(lblAddress);
		
		textField_4 = new JTextField();
		textField_4.setText("");
		textField_4.setBounds(324, 170, 169, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblEmailId = new JLabel("Email Id");
		lblEmailId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblEmailId.setBounds(145, 206, 124, 22);
		contentPane.add(lblEmailId);
		
		textField_5 = new JTextField();
		textField_5.setBounds(324, 206, 169, 22);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		ImageIcon img  = new ImageIcon(this.getClass().getResource("/NewUser.png"));
		
		JButton btnNewButton = new JButton("Go back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				login lo = new login();
				lo.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(316, 595, 97, 25);
		contentPane.add(btnNewButton);
		
		
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		label.setIcon(img);
		label.setBounds(0, 0, 718, 682);
		contentPane.add(label);
	}
}
