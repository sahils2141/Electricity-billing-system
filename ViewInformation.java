package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class ViewInformation extends JFrame implements ActionListener{
	
	JButton cancel;
	ViewInformation(String meter){
		setBounds(350,150,850,650);
		getContentPane().setBackground(Color.white);
		
		setLayout(null);
		
		JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
		heading.setBounds(250,0,500,40);
		heading.setFont(new Font("Elephant",Font.BOLD,18));
		add(heading);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(70,80,100,25);
		lblname.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblname);
		
		JLabel name = new JLabel("");
		name.setBounds(250,80,100,25);
		name.setFont(new Font("Elephant",Font.PLAIN,15));
		add(name);
		
		JLabel lblmeternumber = new JLabel("Meter Number");
		lblmeternumber.setBounds(70,140,150,25);
		lblmeternumber.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblmeternumber);
		
		JLabel meternumber = new JLabel("");
		meternumber.setBounds(250,140,150,25);
		meternumber.setFont(new Font("Elephant",Font.PLAIN,15));
		add(meternumber);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(70,200,150,25);
		lbladdress.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lbladdress);
		
		JLabel address = new JLabel("");
		address.setBounds(250,200,150,25);
		address.setFont(new Font("Elephant",Font.PLAIN,15));
		add(address);
		
		JLabel lblcity = new JLabel("City");
		lblcity.setBounds(70,260,150,25);
		lblcity.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblcity);
		
		JLabel city = new JLabel("");
		city.setBounds(250,260,150,25);
		city.setFont(new Font("Elephant",Font.PLAIN,15));
		add(city);
		
		JLabel lblstate = new JLabel("State");
		lblstate.setBounds(450,80,150,25);
		lblstate.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblstate);
		
		JLabel state = new JLabel("");
		state.setBounds(650,80,150,25);
		state.setFont(new Font("Elephant",Font.PLAIN,15));
		add(state);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(450,140,150,25);
		lblemail.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblemail);
		
		JLabel email = new JLabel("");
		email.setBounds(650,140,150,25);
		email.setFont(new Font("Elephant",Font.PLAIN,15));
		add(email);
		
		JLabel lblphone = new JLabel("Phone");
		lblphone.setBounds(450,200,150,25);
		lblphone.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblphone);
		
		JLabel phone = new JLabel("");
		phone.setBounds(650,200,150,25);
		phone.setFont(new Font("Elephant",Font.PLAIN,15));
		add(phone);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
			while(rs.next()) {
				name.setText(rs.getString("name"));
				address.setText(rs.getString("address"));
				city.setText(rs.getString("city"));
				state.setText(rs.getString("state"));
				email.setText(rs.getString("email"));
				phone.setText(rs.getString("phone"));
				meternumber.setText(rs.getString("meter_no"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		cancel = new JButton("Cancel");
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		cancel.setBounds(250,350,100,25);
		add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(20,350,600,300);
		add(image);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		setVisible(false);
	}
	public static void main(String[] args) {
		new ViewInformation("");
	}

}
