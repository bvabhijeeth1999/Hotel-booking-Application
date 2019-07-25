import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.*;
import java.util.Calendar;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.time.*;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class mybookings extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	static String bookidcancel;
	static String cancelhotelname;
	static String cancelcityname;
	static int cancelwaitinglist;
	static int ct = 0;
	static int n3=1;
	static Date checin;
	static Date checout;
	static Date datein1;
	static Date dateout1;
	static Date cancelcheckin;
	static int bn;
	static int ar;
	static int count2= 0;
	static int n21,sum21;
	static long days;
	static int apple = 0;
	static int cu = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mybookings frame = new mybookings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connect;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JButton btnExit;
	private JButton btnGoBack;
	private JButton btnModify;
	private JButton btnCheckValidity;
	/**
	 * Create the frame.
	 */
	public mybookings() {
		connect = sqliteconnect.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(220, 118, 718, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(220, 156, 718, 25);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 199, 701, 455);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try {
			String query="select * from pastuserbook where username =?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1,login.name);
			ResultSet rs=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			}
		catch(Exception e5) {
				JOptionPane.showMessageDialog(null,e5);
			}
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = sqliteconnect.dbconnector();
				bookidcancel = textField.getText();
				try{
				
					String query3 = "select hotel,cityname,waitinglist,cin from pastuserbook where bookingid = ?";
					PreparedStatement ps3 = connect.prepareStatement(query3);
					ps3.setString(1,bookidcancel);
					ResultSet rs3 = ps3.executeQuery();
					
					while(rs3.next()) {
						cu = 1;
						cancelhotelname = rs3.getString(1);
						cancelcityname = rs3.getString(2);
						cancelwaitinglist = rs3.getInt(3);
						java.sql.Date sqlDate55 = new java.sql.Date(df.parse(rs3.getString(4)).getTime());
						cancelcheckin = sqlDate55;
					}
					
					ps3.close();
					rs3.close();
					
					
					if(cu == 1) {
					
					String query = "delete from pastuserbook where bookingid = '"+textField.getText()+"' and username = '"+login.name+"' ";
					PreparedStatement ps = connect.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null,"you have successfully cancelled");
					ps.close();
					
					String query1="select * from pastuserbook where username =?";
					PreparedStatement ps1 = connect.prepareStatement(query1);
					ps1.setString(1,login.name);
					ResultSet rs1=ps1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					ps1.close();
					rs1.close();
					//JOptionPane.showMessageDialog(null,"cancelwaitinglist:" + cancelwaitinglist);
					
				if(cancelwaitinglist==0) {
					//JOptionPane.showMessageDialog(null,"count2:" + count2);
					while(count2!=1) {
						String query2 = "select noofrooms,cin,cout,bookingid from pastuserbook where hotel = ? and cityname = ? and waitinglist = ?";
						PreparedStatement ps2 = connect.prepareStatement(query2);
						ps2.setString(1,cancelhotelname);
						ps2.setString(2,cancelcityname);
						ps2.setInt(3,1);
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()) {
							apple = 1;
							ct++;
							n3 = rs2.getInt(1);
							java.sql.Date sqlDate1 = new java.sql.Date(df.parse(rs2.getString(2)).getTime());
							checin = sqlDate1;
							java.sql.Date sqlDate2 = new java.sql.Date(df.parse(rs2.getString(3)).getTime());
							checout = sqlDate2;
							bn = rs2.getInt(4);
							if(ct==1) {
								//JOptionPane.showMessageDialog(null,n3);
								ct = 0;
								break;
							}
					}
					ps2.close();
					rs2.close();
					//done here
					
					
					String query4 = "select noofrooms,cin,cout from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
					PreparedStatement ps4 = connect.prepareStatement(query4);
					ps4.setString(1,cancelhotelname);
					ps4.setString(2,cancelcityname);
					ps4.setInt(3,0);
					
					ResultSet rs4 = ps4.executeQuery();
					sum21 = 0;
					n21 = 0;
					
					while(rs4.next()) {
						n21 = rs4.getInt(1);
						java.sql.Date sqlDate3 = new java.sql.Date(df.parse(rs4.getString(2)).getTime());
						datein1 = sqlDate3;
						java.sql.Date sqlDate4 = new java.sql.Date(df.parse(rs4.getString(3)).getTime());
						dateout1 = sqlDate4;

//					System.out.println(checin.compareTo(dateout1) <=0);
//					System.out.println(checin.compareTo(datein1) >=0 );
//					System.out.println(checout.compareTo(dateout1) <=0);
//					System.out.println(checout.compareTo(datein1) >=0);
					if(apple == 1) {
						if(((checin.compareTo(dateout1) <=0) && (checin.compareTo(datein1) >=0 )) || ((checout.compareTo(dateout1) <=0) && (checout.compareTo(datein1) >=0)) || ((datein1.compareTo(checout) <=0) && (datein1.compareTo(checin) >=0 )) || ((dateout1.compareTo(checout) <=0) && (dateout1.compareTo(checin) >=0 )) ){
							//System.out.println("inside if loop");
							sum21+=n21;
						}
					apple = 0;
					}
					
						
					}
					ps4.close();
					rs4.close();
					
					String query5 = "select noofrooms from hotelrooms where hotelname=? and cityname = ?";
					PreparedStatement ps5 = connect.prepareStatement(query5);
					ps5.setString(1,cancelhotelname);
					ps5.setString(2,cancelcityname);
					
					int totalrooms2;
					ResultSet rs5 = ps5.executeQuery();
					totalrooms2 = Integer.parseInt(rs5.getString(1));
					//System.out.println(totalrooms2 + " " + sum21);
					
					ar = totalrooms2-sum21;
					
					//System.out.println(ar);
					//System.out.println(n3);
					
					ps5.close();
					rs5.close();
					
					
					//JOptionPane.showMessageDialog(null,"required number of rooms:"+n3);
					int waiting = 0;
					String query40 = "select * from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
					PreparedStatement ps40 = connect.prepareStatement(query40);
					ps40.setString(1,cancelhotelname);
					ps40.setString(2,cancelcityname);
					ps40.setInt(3,1);
					
					ResultSet rs40 = ps40.executeQuery();
					
					while(rs40.next()) {
						waiting++;
					}
					ps40.close();
					rs40.close();
					
					if(ar>=n3){
						String query6 = "update pastuserbook set waitinglist = '"+0+"' where bookingid =?";
						PreparedStatement ps6 = connect.prepareStatement(query6);
						ps6.setInt(1,bn);
						ps6.execute();
						ps6.close();
					}
					
					String query100="select * from pastuserbook where username =?";
					PreparedStatement ps100 = connect.prepareStatement(query100);
					ps100.setString(1,login.name);
					ResultSet rs100=ps100.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs100));
					ps100.close();
					rs100.close();
					
					//JOptionPane.showMessageDialog(null,"waiting list:" + waiting);
					
					if(waiting!=0){
						if(ar>=n3){
//							String query6 = "update pastuserbook set waitinglist = '"+0+"' where bookingid =?";
//							PreparedStatement ps6 = connect.prepareStatement(query6);
//							ps6.setInt(1,bn);
//							ps6.execute();
//							ps6.close();
							//JOptionPane.showMessageDialog(null,bn);
							count2 = 0;
						}
						else {
							count2 = 1;
						}
					}
					else {
						count2 =1;
					}
					//JOptionPane.showMessageDialog(null,"count2:"+ count2);
				}
				count2 = 0;
					String query10="select * from pastuserbook where username =?";
					PreparedStatement ps10 = connect.prepareStatement(query10);
					ps10.setString(1,login.name);
					ResultSet rs10=ps10.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs10));
					ps10.close();
					rs10.close();
				}
				cu = 0;
				String query11="select * from pastuserbook where username =?";
				PreparedStatement ps11 = connect.prepareStatement(query11);
				ps11.setString(1,login.name);
				ResultSet rs11=ps11.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs11));
				ps11.close();
				rs11.close();
				
			}
					else {
						
						JOptionPane.showMessageDialog(null,"Please enter Valid Booking ID");
					}
			}
				catch(Exception e5) {
					
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
	});
		btnNewButton_1.setBounds(65, 118, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblEnterBookingId = new JLabel("Enter Booking ID:");
		lblEnterBookingId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblEnterBookingId.setBounds(34, -1, 175, 63);
		contentPane.add(lblEnterBookingId);
		
		textField = new JTextField();
		textField.setBounds(37, 75, 151, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(886, 630, 97, 25);
		contentPane.add(btnExit);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				viewmybook vb = new viewmybook();
				vb.setVisible(true);
			}
		});
		btnGoBack.setBounds(886, 575, 97, 25);
		contentPane.add(btnGoBack);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Mybookings.png"));
		
		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = sqliteconnect.dbconnector();
				bookidcancel = textField.getText();
				try{
					
					
					String query3 = "select hotel,cityname,waitinglist,cin from pastuserbook where bookingid = ?";
					PreparedStatement ps3 = connect.prepareStatement(query3);
					ps3.setString(1,bookidcancel);
					ResultSet rs3 = ps3.executeQuery();
					
					while(rs3.next()) {
						cu =1;
						cancelhotelname = rs3.getString(1);
						cancelcityname = rs3.getString(2);
						cancelwaitinglist = rs3.getInt(3);
						java.sql.Date sqlDate55 = new java.sql.Date(df.parse(rs3.getString(4)).getTime());
						cancelcheckin = sqlDate55;
					}
					
					ps3.close();
					rs3.close();
					
					if(cu == 1) {
					java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					long diff = cancelcheckin.getTime() - today.getTime();
					days = (diff/(1000*60*60*24)) + 1;
					
					if(days>=3) {
						
						
						String query = "delete from pastuserbook where bookingid = '"+textField.getText()+"' and username = '"+login.name+"' ";
						PreparedStatement ps = connect.prepareStatement(query);
						ps.execute();
						ps.close();
						
						String query1="select * from pastuserbook where username =?";
						PreparedStatement ps1 = connect.prepareStatement(query1);
						ps1.setString(1,login.name);
						ResultSet rs1=ps1.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
						ps1.close();
						rs1.close();
						//JOptionPane.showMessageDialog(null,"cancelwaitinglist:" + cancelwaitinglist);
						
					if(cancelwaitinglist==0) {
						//JOptionPane.showMessageDialog(null,"count2:" + count2);
						while(count2!=1) {
							//JOptionPane.showMessageDialog(null,"inwhile");
							
							String query2 = "select noofrooms,cin,cout,bookingid from pastuserbook where hotel = ? and cityname = ? and waitinglist = ?";
							PreparedStatement ps2 = connect.prepareStatement(query2);
							ps2.setString(1,cancelhotelname);
							ps2.setString(2,cancelcityname);
							ps2.setInt(3,1);
							ResultSet rs2 = ps2.executeQuery();
							while(rs2.next()) {
								apple = 1;
								ct++;
								n3 = rs2.getInt(1);
								java.sql.Date sqlDate1 = new java.sql.Date(df.parse(rs2.getString(2)).getTime());
								//JOptionPane.showMessageDialog(null,"ollala");
								checin = sqlDate1;
								java.sql.Date sqlDate2 = new java.sql.Date(df.parse(rs2.getString(3)).getTime());
								checout = sqlDate2;
								bn = rs2.getInt(4);
								if(ct==1) {
									//JOptionPane.showMessageDialog(null,n3);
									ct = 0;
									break;
								}
						}
						ps2.close();
						rs2.close();
						//done here
					
						
						String query4 = "select noofrooms,cin,cout from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
						PreparedStatement ps4 = connect.prepareStatement(query4);
						ps4.setString(1,cancelhotelname);
						ps4.setString(2,cancelcityname);
						ps4.setInt(3,0);
						
						ResultSet rs4 = ps4.executeQuery();
						sum21 = 0;
						n21 = 0;
						
						while(rs4.next()) {
							n21 = rs4.getInt(1);
							java.sql.Date sqlDate3 = new java.sql.Date(df.parse(rs4.getString(2)).getTime());
							datein1 = sqlDate3;
							java.sql.Date sqlDate4 = new java.sql.Date(df.parse(rs4.getString(3)).getTime());
							dateout1 = sqlDate4;
						
							//JOptionPane.showMessageDialog(null,"days1 : " +days);
						if(apple == 1) {
							if(((checin.before(dateout1)) && (checin.after(datein1))) || ((checout.before(dateout1)) && (checout.after(datein1))) || ((datein1.before(checout)) && (datein1.after(checin))) || ((dateout1.before(checout)) && (dateout1.after(checin))) ){
								//System.out.println("inside if loop");
								sum21+=n21;
							}
							apple = 0;
						}
							
						}
						ps4.close();
						rs4.close();
					//	JOptionPane.showMessageDialog(null,"days2 : " +days);
						
						String query5 = "select noofrooms from hotelrooms where hotelname=? and cityname = ?";
						PreparedStatement ps5 = connect.prepareStatement(query5);
						ps5.setString(1,cancelhotelname);
						ps5.setString(2,cancelcityname);
						
						int totalrooms2;
						ResultSet rs5 = ps5.executeQuery();
						totalrooms2 = Integer.parseInt(rs5.getString(1));
						//System.out.println(totalrooms2 + " " + sum21);
						
						ar = totalrooms2-sum21;
						
						//System.out.println(ar);
						//System.out.println(n3);
						
						ps5.close();
						rs5.close();
						
						
						//JOptionPane.showMessageDialog(null,"required number of rooms:"+n3);
						int waiting = 0;
						String query40 = "select * from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
						PreparedStatement ps40 = connect.prepareStatement(query40);
						ps40.setString(1,cancelhotelname);
						ps40.setString(2,cancelcityname);
						ps40.setInt(3,1);
						
						ResultSet rs40 = ps40.executeQuery();
						
						while(rs40.next()) {
							waiting++;
						}
						ps40.close();
						rs40.close();
						
						if(ar>=n3){
							String query6 = "update pastuserbook set waitinglist = '"+0+"' where bookingid =?";
							PreparedStatement ps6 = connect.prepareStatement(query6);
							ps6.setInt(1,bn);
							ps6.execute();
							ps6.close();
						}
						
						String query100="select * from pastuserbook where username =?";
						PreparedStatement ps100 = connect.prepareStatement(query100);
						ps100.setString(1,login.name);
						ResultSet rs100=ps100.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs100));
						ps100.close();
						rs100.close();
						
						//JOptionPane.showMessageDialog(null,"waiting list:" + waiting);
						
						if(waiting!=0){
							if(ar>=n3){
								count2 = 0;
							}
							else {
								count2 = 1;
							}
						}
						else {
							count2 =1;
						}
						//JOptionPane.showMessageDialog(null,"count2:"+ count2);
					}
					count2 = 0;
						String query10="select * from pastuserbook where username =?";
						PreparedStatement ps10 = connect.prepareStatement(query10);
						ps10.setString(1,login.name);
						ResultSet rs10=ps10.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs10));
						ps10.close();
						rs10.close();
					}
					String query11="select * from pastuserbook where username =?";
					PreparedStatement ps11 = connect.prepareStatement(query11);
					ps11.setString(1,login.name);
					ResultSet rs11=ps11.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs11));
					ps11.close();
					rs11.close();
					//JOptionPane.showMessageDialog(null,"days : " +days);
					viewmybook.bajaj = 1;
					dispose();
					hotelInfo vmb1 = new hotelInfo();
					vmb1.setVisible(true);
					
					}
					else {
						JOptionPane.showMessageDialog(null,"Sorry, you cannot modify");
					}
				
					cu = 0;

					}
					else {
						JOptionPane.showMessageDialog(null,"Please enter Valid Booking ID");
					}
		}
			catch(Exception e5) {
				e5.printStackTrace();
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
		});
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModify.setBounds(65, 156, 97, 25);
		contentPane.add(btnModify);
		
		btnCheckValidity = new JButton("Check Validity");
		btnCheckValidity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect = sqliteconnect.dbconnector();
				bookidcancel = textField.getText();
				try {
			
				
				String query3 = "select hotel,cityname,waitinglist,cin from pastuserbook where bookingid = ?";
				PreparedStatement ps3 = connect.prepareStatement(query3);
				ps3.setString(1,bookidcancel);
				ResultSet rs3 = ps3.executeQuery();
				
				while(rs3.next()) {
					cu = 1;
					cancelhotelname = rs3.getString(1);
					cancelcityname = rs3.getString(2);
					cancelwaitinglist = rs3.getInt(3);
					java.sql.Date sqlDate55 = new java.sql.Date(df.parse(rs3.getString(4)).getTime());
					cancelcheckin = sqlDate55;
				}
				
				ps3.close();
				rs3.close();
				
				if(cu == 1) {
					
					java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					//JOptionPane.showMessageDialog(null,cancelcheckin + " " + today);
					long diff = cancelcheckin.getTime() - today.getTime();
					days = (diff/(1000*60*60*24)) + 1;
					if(days>=3) {
						lblNewLabel_1.setText("You can cancel for free");
						lblNewLabel_2.setText("You can modify");
					}
					else {
						lblNewLabel_1.setText("You will be refunded with only 50% of your booking price");
						lblNewLabel_2.setText("You cannot modify");
					}
					cu = 0;
				}
				else {
					JOptionPane.showMessageDialog(null,"Please enter Valid Booking ID");
				}
				}
				catch(Exception e10) {
					
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
		});
		btnCheckValidity.setBounds(210, 78, 121, 25);
		contentPane.add(btnCheckValidity);
		
				
				JLabel lblNewLabel = new JLabel("New label");
				lblNewLabel.setIcon(img);
				lblNewLabel.setBounds(0, 0, 1073, 717);
				contentPane.add(lblNewLabel);
	}
}
