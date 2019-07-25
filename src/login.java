import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;


public class login{
	public String city;
	public String cindate;
	public String coutdate;
	public int noofrooms;
	static String name;

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the application.
	 */
	Connection conne = null;
	private JTextField textField;
	private JPasswordField passwordField;
	public login() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(new Color(224, 255, 255));
		frame.setTitle("Login page");
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(100, 100, 837, 644);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(505, 160, 225, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Updateuser uu = new Updateuser();
				uu.setVisible(true);
			}
		});
		btnForgotPassword.setBounds(598, 429, 158, 25);
		frame.getContentPane().add(btnForgotPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblUsername.setBounds(379, 160, 116, 49);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblPassword.setBounds(380, 271, 98, 49);
		frame.getContentPane().add(lblPassword);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(505, 271, 225, 49);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = textField.getText();
				String pass = passwordField.getText();

			if(!(user.equals("")) && !(pass.equals(""))) {
				try {
					conne = sqliteconnect.dbconnector();
					String query = "select * from hotel where username=? and password = ?";
					PreparedStatement ps = conne.prepareStatement(query);
					ps.setString(1,textField.getText());
					ps.setString(2,passwordField.getText());
					
					ResultSet r = ps.executeQuery();
					int count = 0;
					while(r.next()) {
						count++;
					}
					if(count == 1) {
						name = textField.getText();
						frame.dispose();
						viewmybook vb = new viewmybook();
						vb.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"Sorry, recheck the username and password you entered:!");
					}
					r.close();
					ps.close();
					conne.close();
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
				finally {
						  if (conne != null) {
						    try {
						      conne.close(); // <-- This is important
						    } catch (SQLException e) {
						    	JOptionPane.showMessageDialog(null,e);
						    }
						  }
				}
			}
				else {
					JOptionPane.showMessageDialog(null,"Fill all fields");
				}
			}
		});
		btnLogin.setBounds(552, 366, 124, 35);
		frame.getContentPane().add(btnLogin);
	
		ImageIcon img  = new ImageIcon(this.getClass().getResource("/LoginPage.png"));
		
		JButton btnNewUser = new JButton("New User?");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Newuser nu = new Newuser();
				nu.setVisible(true);
			}
		});
		btnNewUser.setBounds(477, 429, 111, 25);
		frame.getContentPane().add(btnNewUser);
		
		JLabel label = new JLabel("");
		label.setIcon(img);
		label.setBounds(0, 0, 828, 611);
		frame.getContentPane().add(label);
	}
}