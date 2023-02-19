package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
	
	JTextField tfname,tfaddress,tfunit;
	JButton submit,cancel;
	JLabel lblname ,labeladdress;
	Choice meternumber, cmonth;
	CalculateBill(){
		setSize(700,500);
		setLocation(400,250);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		add(p);
		
		JLabel heading =new JLabel("Calculate Electrisity Bill");
		heading.setBounds(200,10,600,20);
		heading.setFont(new Font("Elephant",Font.PLAIN,26));
		p.add(heading);
		
		JLabel lblmeternumber =new JLabel("Meter Number");
		lblmeternumber.setBounds(100,60,200,20);
		lblmeternumber.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeternumber);
		
		meternumber = new Choice();
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()) {
				meternumber.add(rs.getString("meter_no"));
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		meternumber.setBounds(300,60,200,20);
		meternumber.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(meternumber);
		
		JLabel lblmeter =new JLabel("Name");
		lblmeter.setBounds(100,100,200,20);
		lblmeter.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeter);
		
		lblname =new JLabel("");
		lblname.setBounds(300,100,200,20);
		lblname.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblname);
		
		JLabel lbladdress =new JLabel("Address");
		lbladdress.setBounds(100,140,200,20);
		lbladdress.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lbladdress);
		
		labeladdress = new JLabel();
		labeladdress.setBounds(300,140,200,25);
		labeladdress.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(labeladdress);
		
		try {
			Conn c = new Conn();
			ResultSet rs =c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"'");
			while(rs.next())
			{
				lblname.setText(rs.getString("name"));
				labeladdress.setText(rs.getString("address"));
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		meternumber.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				try {
					Conn c = new Conn();
					ResultSet rs =c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"'");
					while(rs.next())
					{
						lblname.setText(rs.getString("name"));
						labeladdress.setText(rs.getString("address"));
					}
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblcity =new JLabel("Unit Cunsumed");
		lblcity.setBounds(100,180,200,20);
		lblcity.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblcity);
		
		tfunit = new JTextField();
		tfunit.setBounds(300,180,200,25);
		p.add(tfunit);
		
		JLabel lblstate =new JLabel("Month");
		lblstate.setBounds(100,220,200,20);
		lblstate.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblstate);
		
		cmonth = new Choice();
		cmonth.setBounds(300,220,200,25);
		cmonth.add("Junuary");
		cmonth.add("February");
		cmonth.add("March");
		cmonth.add("April");
		cmonth.add("May");
		cmonth.add("June");
		cmonth.add("July");
		cmonth.add("August");
		cmonth.add("September");
		cmonth.add("October");
		cmonth.add("November");
		cmonth.add("December");
		p.add(cmonth);
		
		
		submit = new JButton("Submit");
		submit.setBounds(120,340,100,25);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		submit.addActionListener(this);
		p.add(submit);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(280,340,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		p.add(cancel);
		
		setLayout (new BorderLayout());
		add(p,"Center");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/calculate.png"));
		Image i2 = i1.getImage().getScaledInstance(150, 350, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image, "East");
		
		getContentPane().setBackground(Color.white);
		
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==submit) {
			String meter = meternumber.getSelectedItem();
			String unit = tfunit.getText();
			String month = cmonth.getSelectedItem();
			
			int totalbill = 0;
			int unit_consume = Integer.parseInt(unit);
			
			String query = "select * from tax";
			
			try {
				Conn c = new Conn();
				ResultSet rs = c.s.executeQuery(query);
				
				while(rs.next()) {
					totalbill +=unit_consume * Integer.parseInt(rs.getString("cost_per_unit"));
					totalbill+= Integer.getInteger(rs.getString("meter_rent"));
					totalbill+= Integer.getInteger(rs.getString("service_charge"));
					totalbill+= Integer.getInteger(rs.getString("service_tax"));
					totalbill+= Integer.getInteger(rs.getString("swacch_bharat_cess"));
					totalbill+= Integer.getInteger(rs.getString("fixed_tax"));
				
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String query2 = "insert into bill values('"+meter+"','"+month+"','"+unit+"','"+totalbill+"','Not Paid')";
			try {
				Conn c = new Conn();
				c.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
				setVisible(false);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new CalculateBill();
	}

}
