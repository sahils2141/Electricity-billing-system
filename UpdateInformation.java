package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UpdateInformation extends JFrame implements ActionListener{
	
	JTextField tfaddress,tfstate,tfcity,tfphone,tfemail;
	JButton update,cancel;
	String meter;
	JLabel name;
	UpdateInformation(String meter){
		
	this.meter = meter;
		setBounds(300,150,1050,450);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
		heading.setBounds(110,0,450,30);
		heading.setFont(new Font("Elephant",Font.PLAIN,18));
		heading.setForeground(Color.RED);
		add(heading);
		
		JLabel lblname = new JLabel("Name");
		lblname.setBounds(30,70,100,25);
		lblname.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblname);
		
		name = new JLabel("");
		name.setBounds(230,70,200,25);
		name.setFont(new Font("Elephant",Font.PLAIN,15));
		add(name);
		
		JLabel lblmeternumber = new JLabel("Meter Number");
		lblmeternumber.setBounds(30,110,150,25);
		lblmeternumber.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblmeternumber);
		
		JLabel meternumber = new JLabel("");
		meternumber.setBounds(230,110,200,25);
		meternumber.setFont(new Font("Elephant",Font.PLAIN,15));
		add(meternumber);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setBounds(30,150,100,25);
		lbladdress.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lbladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(230,150,200,25);
		tfaddress.setFont(new Font("Elephant",Font.PLAIN,15));
		add(tfaddress);
		
		JLabel lblcity = new JLabel("City");
		lblcity.setBounds(30,190,100,25);
		lblcity.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblcity);
		
		tfcity = new JTextField();
		tfcity.setBounds(230,190,200,25);
		tfcity.setFont(new Font("Elephant",Font.PLAIN,15));
		add(tfcity);
		
		JLabel lblstate = new JLabel("State");
		lblstate.setBounds(30,230,100,25);
		lblstate.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblstate);
		
		tfstate = new JTextField();
		tfstate.setBounds(230,230,200,25);
		tfstate.setFont(new Font("Elephant",Font.PLAIN,15));
		add(tfstate);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setBounds(30,270,100,25);
		lblemail.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(230,270,200,25);
		tfemail.setFont(new Font("Elephant",Font.PLAIN,15));
		add(tfemail);
		
		JLabel lblphone = new JLabel("Phone");
		lblphone.setBounds(30,310,100,25);
		lblphone.setFont(new Font("Elephant",Font.PLAIN,15));
		add(lblphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(230,310,200,25);
		tfphone.setFont(new Font("Elephant",Font.PLAIN,15));
		add(tfphone);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
			while(rs.next()) {
				name.setText(rs.getString("name"));
				tfaddress.setText(rs.getString("address"));
				tfcity.setText(rs.getString("city"));
				tfstate.setText(rs.getString("state"));
				tfemail.setText(rs.getString("email"));
				tfphone.setText(rs.getString("phone"));
				meternumber.setText(rs.getString("meter_no"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		update = new JButton("Update");
		update.setBackground(Color.black);
		update.setForeground(Color.white);
		update.setBounds(70,360,100,25);
		add(update);
		update.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		cancel.setBounds(230,360,100,25);
		add(cancel);
		cancel.addActionListener(this);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
		Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(550,50,400,300);
		add(image);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == update){
			String address = tfaddress.getText();
			String city = tfcity.getText();
			String state = tfstate.getText();
			String email = tfemail.getText();
			String phone = tfphone.getText();
			
			try {
				Conn c = new Conn();
				c.s.executeUpdate("update customer set address = '"+address+"',city = '"+city+"',state = '"+state+"',email = '"+email+"',phone = '"+phone+"' where meter_no = '"+meter+"'");
				JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
				setVisible(false);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new UpdateInformation("");

	}

}
