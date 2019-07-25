import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.text.*;

public class hoteldisplay extends JFrame {
	static String hname;
	private JPanel contentPane;
	static DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
	private int availablerooms;
	private int availablerooms1;
	private int availablerooms2;
	static int count;
	static int totalrooms,totalrooms1,totalrooms2;
	static int co2,co1,co;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hoteldisplay frame = new hoteldisplay();
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
	public hoteldisplay() {
		connect = sqliteconnect.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(29, 34, 269, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(29, 250, 269, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		label.setBounds(29, 464, 269, 30);
		contentPane.add(label);
		
		JLabel lblPic = new JLabel("pic1");
		lblPic.setBounds(29, 75, 285, 135);
		contentPane.add(lblPic);
		
		JLabel lblPic_1 = new JLabel("Pic2");
		lblPic_1.setBounds(34, 291, 285, 135);
		contentPane.add(lblPic_1);
		
		JLabel lblPic_2 = new JLabel("Pic3");
		lblPic_2.setBounds(29, 505, 285, 135);
		contentPane.add(lblPic_2);
		
		JLabel label_11 = new JLabel("");
		label_11.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		label_11.setBounds(204, 438, 72, 16);
		contentPane.add(label_11);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		lblNewLabel_5.setBounds(204, 223, 72, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Book Antiqua", Font.BOLD, 14));
		lblNewLabel_6.setBounds(204, 650, 72, 16);
		contentPane.add(lblNewLabel_6);
		
		if(hotelInfo.citynam.equals("hyderabad")){
			lblNewLabel.setText("Taj Krishna");			
			lblNewLabel_1.setText("Park Hyath");			
			label.setText("Great Mayor");
			lblNewLabel_5.setText("4.85(314 users)");
			lblNewLabel_6.setText("4.10(516 users)");
			label_11.setText("4.25(421 users)");
			ImageIcon img1  = new ImageIcon(this.getClass().getResource("/TajKrishnaHyd.png"));
			ImageIcon img2  = new ImageIcon(this.getClass().getResource("/ParkHyattHyd.png"));
			ImageIcon img3  = new ImageIcon(this.getClass().getResource("/GreatMayorHyd.png"));
			lblPic.setIcon(img1);
			lblPic_1.setIcon(img2);
			lblPic_2.setIcon(img3);
			
		}
		else if(hotelInfo.citynam.equals("mumbai")) {
			lblNewLabel.setText("Taj Mahal Palace");
			lblNewLabel_1.setText("The Oberoi");
			label.setText("The Regis");
			lblNewLabel_5.setText("4.70(422 users)");
			lblNewLabel_6.setText("4.56(730 users)");
			label_11.setText("4.65(633 users)");
			ImageIcon img1  = new ImageIcon(this.getClass().getResource("/TajMahalMum.png"));
			ImageIcon img2  = new ImageIcon(this.getClass().getResource("/OberoiMum.png"));
			ImageIcon img3  = new ImageIcon(this.getClass().getResource("/St-Regis-Mum.png"));
			lblPic.setIcon(img1);
			lblPic_1.setIcon(img2);
			lblPic_2.setIcon(img3);
			
		}
		else if(hotelInfo.citynam.equals("banglore")) {
			lblNewLabel.setText("Vivantha Whitefield");
			lblNewLabel_1.setText("Gateway Field");
			label.setText("Taj West End");
			lblNewLabel_5.setText("4.95(512 users)");
			lblNewLabel_6.setText("4.23(212 users)");
			label_11.setText("4.65(498 users)");
			ImageIcon img1  = new ImageIcon(this.getClass().getResource("/Vivanta-by-Taj-Whitefield-Ban.png"));
			ImageIcon img2  = new ImageIcon(this.getClass().getResource("/WhitefieldBan.png"));
			ImageIcon img3  = new ImageIcon(this.getClass().getResource("/TajWestBan.png"));
			lblPic.setIcon(img1);
			lblPic_1.setIcon(img2);
			lblPic_2.setIcon(img3);
			
		}
		
		JLabel lblAverageUserRating = new JLabel("AVERAGE USER RATING :");
		lblAverageUserRating.setBackground(SystemColor.desktop);
		lblAverageUserRating.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAverageUserRating.setBounds(29, 223, 169, 16);
		contentPane.add(lblAverageUserRating);
		
		JLabel lblAverageUserRatingno = new JLabel("AVERAGE USER RATING :");
		lblAverageUserRatingno.setBackground(SystemColor.desktop);
		lblAverageUserRatingno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAverageUserRatingno.setBounds(29, 646, 169, 22);
		contentPane.add(lblAverageUserRatingno);
		
		JLabel lblAverageUserRating_1 = new JLabel("AVERAGE USER RATING :");
		lblAverageUserRating_1.setBackground(SystemColor.desktop);
		lblAverageUserRating_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAverageUserRating_1.setBounds(29, 437, 173, 16);
		contentPane.add(lblAverageUserRating_1);
		
		JLabel lblPrice = new JLabel("PRICE:");
		lblPrice.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblPrice.setBounds(535, 165, 44, 16);
		contentPane.add(lblPrice);
		
		JLabel lblPrice_2 = new JLabel("PRICE:");
		lblPrice_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblPrice_2.setBounds(535, 370, 44, 16);
		contentPane.add(lblPrice_2);
		
		JLabel lblPrice_1 = new JLabel("PRICE:");
		lblPrice_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblPrice_1.setBounds(535, 599, 44, 16);
		contentPane.add(lblPrice_1);
		
		JLabel label_5 = new JLabel("New label");
		label_5.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		label_5.setBounds(591, 370, 133, 16);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("New label");
		label_6.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		label_6.setBounds(591, 599, 133, 16);
		contentPane.add(label_6);
		
		JLabel lblTotalCost = new JLabel("TOTAL COST:");
		lblTotalCost.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblTotalCost.setBounds(494, 194, 81, 16);
		contentPane.add(lblTotalCost);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		lblNewLabel_3.setBounds(591, 194, 133, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_3 = new JLabel("TOTAL COST:");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		label_3.setBounds(494, 396, 81, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		label_4.setBounds(591, 396, 133, 16);
		contentPane.add(label_4);
		
		JLabel label_7 = new JLabel("TOTAL COST:");
		label_7.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		label_7.setBounds(494, 628, 81, 16);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		label_8.setBounds(591, 628, 133, 16);
		contentPane.add(label_8);
		
		JLabel lblAmen = new JLabel("AMENITIES:");
		lblAmen.setBackground(SystemColor.desktop);
		lblAmen.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAmen.setBounds(363, 67, 87, 16);
		contentPane.add(lblAmen);
		
		JLabel lblNewLabel_4 = new JLabel("Complimentary Breakfast,Wifi,Swimming pool");
		lblNewLabel_4.setFont(new Font("Sitka Small", Font.ITALIC, 15));
		lblNewLabel_4.setBounds(445, 69, 369, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel label_9 = new JLabel("AMENITIES:");
		label_9.setBackground(SystemColor.desktop);
		label_9.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		label_9.setBounds(363, 278, 87, 16);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("AMENITIES:");
		label_10.setBackground(SystemColor.desktop);
		label_10.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		label_10.setBounds(363, 515, 87, 16);
		contentPane.add(label_10);
		
		JLabel lblComplimentaryBreakfastwifi = new JLabel("Complimentary Breakfast,Wifi");
		lblComplimentaryBreakfastwifi.setFont(new Font("Sitka Small", Font.ITALIC, 15));
		lblComplimentaryBreakfastwifi.setBounds(445, 281, 369, 16);
		contentPane.add(lblComplimentaryBreakfastwifi);
		
		JLabel lblWifi = new JLabel("Wifi");
		lblWifi.setFont(new Font("Sitka Small", Font.ITALIC, 15));
		lblWifi.setBounds(445, 518, 369, 16);
		contentPane.add(lblWifi);
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		lblNewLabel_2.setBounds(591, 165, 133, 16);
		contentPane.add(lblNewLabel_2);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(194, 439, 56, 16);
		contentPane.add(label_1);
		
	
		
		try {
		
			String query = "select price from hotelrooms where cityname = ? and hotelname = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1,hotelInfo.citynam);
			ps.setString(2,lblNewLabel.getText());
			ResultSet r = ps.executeQuery();
			//JOptionPane.showMessageDialog(null,lblNewLabel.getText());
			String pr;
			while(r.next()) {
				pr = r.getString(1);
				lblNewLabel_3.setText(Integer.toString(Integer.parseInt(pr)*hotelInfo.noofrooms));
				lblNewLabel_2.setText(pr);
			}
			r.close();
			ps.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
		}
		try {
			String query = "select price from hotelrooms where cityname = ? and hotelname = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1,hotelInfo.citynam);
			ps.setString(2,lblNewLabel_1.getText());
			ResultSet r = ps.executeQuery();
			String p;
			while(r.next()) {
				p = r.getString(1);
				label_4.setText(Integer.toString(Integer.parseInt(p)*hotelInfo.noofrooms));
				label_5.setText(p);
			}
			r.close();
			ps.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
		}
		try {
			String query = "select price from hotelrooms where cityname = ? and hotelname = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1,hotelInfo.citynam);
			ps.setString(2,label.getText());
			ResultSet r = ps.executeQuery();
			String p;
			while(r.next()) {
				p = r.getString(1);
				label_8.setText(Integer.toString(Integer.parseInt(p)*hotelInfo.noofrooms));
				label_6.setText(p);
			}
			r.close();
			ps.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null,e1);
		}
	
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(326, 159, 181, 29);
		contentPane.add(lblNewLabel_7);
						
		JLabel label_2 = new JLabel("");
						label_2.setBounds(331, 364, 176, 29);
						contentPane.add(label_2);
						
						JLabel label_13 = new JLabel("");
						label_13.setBounds(326, 593, 181, 29);
						contentPane.add(label_13);
						
		JButton btnNewButton = new JButton("CHECK AVAILABILITY");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select noofrooms,cin,cout from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
					PreparedStatement ps = connect.prepareStatement(query);
					ps.setString(1,lblNewLabel.getText());
					ps.setString(2,hotelInfo.citynam);
					ps.setInt(3,0);
					
					ResultSet r2 = ps.executeQuery();
					int sum2 = 0;
					int n2;
					while(r2.next()) {
						n2 = r2.getInt(1);
						java.sql.Date sqlDate = new java.sql.Date(df.parse(r2.getString(2)).getTime());
						Date datein = sqlDate;
						java.sql.Date sqlDate1 = new java.sql.Date(df.parse(r2.getString(3)).getTime());
						Date dateout = sqlDate1;
						if((hotelInfo.checkin.before(dateout) && hotelInfo.checkin.after(datein)) || (hotelInfo.checkout.before(dateout) && hotelInfo.checkout.after(datein)) || (datein.before(hotelInfo.checkout) && datein.after(hotelInfo.checkin)) || (dateout.before(hotelInfo.checkout) && dateout.after(hotelInfo.checkin))){
							sum2+=n2;
						}
					}
					ps.close();
					r2.close();
					
					String query1 = "select noofrooms from hotelrooms where hotelname=? and cityname = ?";
					PreparedStatement ps1 = connect.prepareStatement(query1);
					ps1.setString(1,lblNewLabel.getText());
					ps1.setString(2,hotelInfo.citynam);
					
					ResultSet r = ps1.executeQuery();
				//	System.out.println(r.getString(1));
					totalrooms2 = Integer.parseInt(r.getString(1));
				//	System.out.println(totalrooms2 + " " + sum2);
					if(totalrooms2 >= hotelInfo.noofrooms) {
						availablerooms2 = totalrooms2-sum2;
						lblNewLabel_7.setText("Available rooms: "+Integer.toString(availablerooms2));	
					}
					else {
						lblNewLabel_7.setText("hotel occupancy exceeded");
					}
					ps1.close();
					r.close();
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(null,e);
					}
			}
		});
		btnNewButton.setBounds(326, 111, 201, 25);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("CHECK AVAILABILITY");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select noofrooms,cin,cout from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
					PreparedStatement ps = connect.prepareStatement(query);
					ps.setString(1,lblNewLabel_1.getText());
					ps.setString(2,hotelInfo.citynam);
					ps.setInt(3,0);
					
					ResultSet r2 = ps.executeQuery();
					int sum2 = 0;
					int n2;
					while(r2.next()) {
						n2 = r2.getInt(1);
						java.sql.Date sqlDate = new java.sql.Date(df.parse(r2.getString(2)).getTime());
						Date datein = sqlDate;
						java.sql.Date sqlDate1 = new java.sql.Date(df.parse(r2.getString(3)).getTime());
						Date dateout = sqlDate1;
						if((hotelInfo.checkin.before(dateout) && hotelInfo.checkin.after(datein)) || (hotelInfo.checkout.before(dateout) && hotelInfo.checkout.after(datein)) || (datein.before(hotelInfo.checkout) && datein.after(hotelInfo.checkin)) || (dateout.before(hotelInfo.checkout) && dateout.after(hotelInfo.checkin))){
							sum2+=n2;
						}
					}
					ps.close();
					r2.close();
					
					String query1 = "select noofrooms from hotelrooms where hotelname=? and cityname = ?";
					PreparedStatement ps1 = connect.prepareStatement(query1);
					ps1.setString(1,lblNewLabel_1.getText());
					ps1.setString(2,hotelInfo.citynam);
					
					ResultSet r4 = ps1.executeQuery();
				//	System.out.println(r4.getString(1));
					totalrooms1 = Integer.parseInt(r4.getString(1));
				//	System.out.println(totalrooms2 + " " + sum2);
					
					if(totalrooms1 > hotelInfo.noofrooms) {
						availablerooms1 = totalrooms1-sum2;
						label_2.setText("Available rooms: "+Integer.toString(availablerooms1));
					
					
					
					
					
					
					}
					else {
						label_2.setText("hotel occupancy exceeded");
					}
					
					ps1.close();
					r4.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
			}
		});
		button.setBounds(331, 317, 192, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CHECK AVAILABILITY");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select noofrooms,cin,cout from pastuserbook where hotel=? and cityname = ? and waitinglist = ?";
					PreparedStatement ps = connect.prepareStatement(query);
					ps.setString(1,label.getText());
					ps.setString(2,hotelInfo.citynam);
					ps.setInt(3,0);
					
					ResultSet r = ps.executeQuery();
					int sum = 0;
					int n;
					while(r.next()) {
						n = r.getInt(1);
						java.sql.Date sqlDate = new java.sql.Date(df.parse(r.getString(2)).getTime());
						Date datein = sqlDate;
						java.sql.Date sqlDate1 = new java.sql.Date(df.parse(r.getString(3)).getTime());
						Date dateout = sqlDate1;
						if((hotelInfo.checkin.before(dateout) && hotelInfo.checkin.after(datein)) || (hotelInfo.checkout.before(dateout) && hotelInfo.checkout.after(datein)) || (datein.before(hotelInfo.checkout) && datein.after(hotelInfo.checkin)) || (dateout.before(hotelInfo.checkout) && dateout.after(hotelInfo.checkin))){
							sum+=n;
						}
					}
					ps.close();
					r.close();
					
					String query1 = "select noofrooms from hotelrooms where hotelname=? and cityname = ?";
					PreparedStatement ps1 = connect.prepareStatement(query1);
					ps1.setString(1,label.getText());
					ps1.setString(2,hotelInfo.citynam);
					
					ResultSet r1 = ps1.executeQuery();
				//	System.out.println(r1.getString(1));
					totalrooms = Integer.parseInt(r1.getString(1));
				//	System.out.println(totalrooms + " " + sum);
					
					if(totalrooms > hotelInfo.noofrooms) {
						availablerooms = totalrooms-sum;
					
						label_13.setText("Available rooms: "+Integer.toString(availablerooms));
						}
					else {
						label_13.setText("hotel occupancy exceeded");
					}
					
					ps1.close();
					r1.close();
					
				
					
					
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(null,e);
					}
			}
		});
		button_1.setBounds(326, 548, 201, 25);
		contentPane.add(button_1);
	
		JButton btnBook = new JButton("PROCEED");
		btnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(totalrooms2 >= hotelInfo.noofrooms) {
				if(hotelInfo.noofrooms <= availablerooms2) {
					try {
						hname = lblNewLabel.getText();
					//	JOptionPane.showMessageDialog(null,hname);
						dispose();
						afterbook af = new afterbook();
						af.setVisible(true);
					}
					catch(Exception e3) {
						JOptionPane.showMessageDialog(null,e3);
					}
					finally {
						
						if (connect != null) {
						    try {
						      connect.close(); // <-- This is important
						    } catch (SQLException e4) {
						    	JOptionPane.showMessageDialog(null,e4);
						    }
						  }
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Required number of rooms not available");
					hname = lblNewLabel.getText();
					count = -1;
					dispose();
					afterbook af = new afterbook();
					af.setVisible(true);
				}
				
				
				}	
				else {
					JOptionPane.showMessageDialog(null,"required number of rooms are greater than hotel occupancy");
				}
			}
		});
		btnBook.setBounds(554, 111, 97, 25);
		contentPane.add(btnBook);
		
		
		
		
		
		JButton btnProceed = new JButton("PROCEED");
		btnProceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(totalrooms1 >= hotelInfo.noofrooms) {
				if(hotelInfo.noofrooms <= availablerooms1) {
					try {
						hname  = lblNewLabel_1.getText();
						dispose();
						afterbook af = new afterbook();
						af.setVisible(true);
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
				else {
					JOptionPane.showMessageDialog(null,"Required number of rooms not available");
					hname = lblNewLabel_1.getText();
					count = -1;
					dispose();
					afterbook af = new afterbook();
					af.setVisible(true);
				}
			}
				else {
					JOptionPane.showMessageDialog(null,"required number of rooms are greater than hotel occupancy");
				}
			}
		});
		btnProceed.setBounds(550, 317, 97, 25);
		contentPane.add(btnProceed);
		
		
		
		
		
		JButton btnProceed_1 = new JButton("PROCEED");
		btnProceed_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProceed_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(totalrooms >= hotelInfo.noofrooms) {
				if(hotelInfo.noofrooms <= availablerooms) {
					try {
						hname  = label.getText();
						dispose();
						afterbook af = new afterbook();
						af.setVisible(true);
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}
					finally {
						
						if (connect != null) {
						    try {
						      connect.close(); // <-- This is important
						    } catch (SQLException e2) {
						    	JOptionPane.showMessageDialog(null,e2);
						    }
						  }
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Required number of rooms not available");
					hname = label.getText();
					count = -1;
					dispose();
					afterbook af = new afterbook();
					af.setVisible(true);
				}
			}
				else {
					JOptionPane.showMessageDialog(null,"required number of rooms are greater than hotel occupancy");
				}
			}
		});
		btnProceed_1.setBounds(554, 548, 97, 25);
		contentPane.add(btnProceed_1);
		
		
	
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/HotelDisp1.png"));
		
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				co = 0;
				co1 = 0;
				co2 = 0;
				dispose();
				hotelInfo hii = new hotelInfo();
				hii.setVisible(true);
			}
		});
		btnGoBack.setBounds(0, 0, 97, 25);
		contentPane.add(btnGoBack);
				
	
										JLabel label_12 = new JLabel("");
										label_12.setIcon(img);
										label_12.setBounds(0, 0, 824, 716);
										contentPane.add(label_12);
		
	
	
	
	
	}
	}

