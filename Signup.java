package MyPackage;

import java.awt.*;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {
	JButton create,back;
	Choice accountType;
	JTextField meter, username,name,password;
	Signup(){
		super("Signup");
		
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 650, 300);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		add(panel);
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"Create-Account"));
		
		JLabel heading = new JLabel("Create Account As");
		heading.setBounds(50, 50, 140, 20);
		heading.setForeground(Color.DARK_GRAY);
		heading.setFont(new Font("Times New Roman",Font.BOLD,14));
		panel.add(heading);
		
		accountType = new Choice();
		accountType.add("Admin");
		accountType.add("Customer");
		accountType.setBounds(200, 50, 140, 25);
		panel.add(accountType);
		
		
		JLabel lblmeter = new JLabel("Meter Number");
		lblmeter.setBounds(50, 80, 140, 20);
		lblmeter.setForeground(Color.DARK_GRAY);
		lblmeter.setFont(new Font("Times New Roman",Font.BOLD,14));
		lblmeter.setVisible(false);
		panel.add(lblmeter);
		
		meter = new JTextField();
		meter.setBounds(200, 80, 140, 25);
		meter.setVisible(false);
		panel.add(meter);
		
		
		JLabel lblusername = new JLabel("User name");
		lblusername.setBounds(50, 110, 140, 20);
		lblusername.setForeground(Color.DARK_GRAY);
		lblusername.setFont(new Font("Times New Roman",Font.BOLD,14));
		panel.add(lblusername);
		
		username = new JTextField();
		username.setBounds(200, 110, 140, 25);
		panel.add(username);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(50, 140, 140, 20);
		lblname.setForeground(Color.DARK_GRAY);
		lblname.setFont(new Font("Times New Roman",Font.BOLD,14));
		panel.add(lblname);
		
		name = new JTextField();
		name.setBounds(200, 140, 140, 25);
		panel.add(name);
		
		meter.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {}
			@Override
			public void focusLost(FocusEvent fe) {
			
				try {
					Conn c = new Conn();
					ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
					while (rs.next()) {
						name.setText(rs.getString("name"));
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(50, 170, 140, 20);
		lblpassword.setForeground(Color.DARK_GRAY);
		lblpassword.setFont(new Font("Times New Roman",Font.BOLD,14));
		panel.add(lblpassword);
		
		password = new JTextField();
		password.setBounds(200, 170, 140, 25);
		panel.add(password);
		
		accountType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				String user = accountType.getSelectedItem();
				if(user.equals("Customer")) {
					lblmeter.setVisible(true);
					meter.setVisible(true);
					name.setEditable(false);
				}else {
					lblmeter.setVisible(false);
					meter.setVisible(false);
					name.setEditable(true);
				}
			}
		});
		
		create = new JButton("Create");
		create.setBackground(Color.black);
		create.setForeground(Color.white);
		create.setFont(new Font("tahoma",Font.BOLD,14));
		create.setBounds(70, 220, 100, 23);
		create.addActionListener(this);
		panel.add(create);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setFont(new Font("tahoma",Font.BOLD,14));
		back.setBounds(190, 220, 100, 23);
		back.addActionListener(this);
		panel.add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signup1.png"));
		Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image  = new JLabel(i3);
		image.setBounds(370,30,250,250);
		panel.add(image);
		
		setSize(650,400);
		setLocation(400,150);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		 if(ae.getSource()==create) {
			String atype = accountType.getSelectedItem();
			String susername = username.getText();
			String sname = name.getText();
			String spassword = password.getText();
			String smeter = meter.getText();
			
			try {
				Conn c = new Conn();
				String query = null;
				if(atype.equals("Customer")) {
					query = "update login set username = '"+susername+"',password = '"+spassword+"',user= '"+atype+"'where meter_no = '"+smeter+"'";
				}
				else if(atype.equals("Admin")) {
					query = "insert into login values('01','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
				}
				
				
				c.s.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Account created Successfully");
				
				setVisible(false);
				new Login();
			}catch(Exception e) {
//				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Please enter meter no.");
				username.setText("");
				password.setText("");
			}
			
		}else if (ae.getSource()==back) {
			setVisible(false);
			new Login();
		}
	}
	

	public static void main(String[] args) {
		new Signup();
	}

}
