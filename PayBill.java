package MyPackage;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class PayBill extends JFrame implements ActionListener{
	
	Choice cmonth;
	JButton pay,back;
	String meter;
	PayBill(String meter){
		this.meter=meter;
		setLayout(null);
		setBounds(300,150,900,600);
		
		JLabel heading = new JLabel("Electricity Bill");
		heading.setFont(new Font("Elephant",Font.PLAIN,25));
		heading.setForeground(Color.BLUE);
		heading.setBounds(150,5,400,30);
		add(heading);
		
		JLabel lblmeternumber = new JLabel("Meter Number");
		lblmeternumber.setFont(new Font("Elephant",Font.PLAIN,18));
		lblmeternumber.setBounds(35,80,200,25);
		add(lblmeternumber);
		
		JLabel meternumber = new JLabel("");
		meternumber.setFont(new Font("Elephant",Font.PLAIN,18));
		meternumber.setBounds(300,80,200,25);
		add(meternumber);
		
		JLabel lblname = new JLabel("Name");
		lblname.setFont(new Font("Elephant",Font.PLAIN,18));
		lblname.setBounds(35,140,100,25);
		add(lblname);
		
		JLabel name = new JLabel("");
		name.setFont(new Font("Elephant",Font.PLAIN,18));
		name.setBounds(300,140,200,25);
		add(name);
		
		JLabel month = new JLabel("Month");
		month.setFont(new Font("Elephant",Font.PLAIN,18));
		month.setBounds(35,200,100,25);
		add(month);
		
		cmonth = new Choice();
		cmonth.setBounds(300,200,200,25);
		cmonth.setFont(new Font("Elephant",Font.PLAIN,16));
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
		add(cmonth);
		
		JLabel lblunits = new JLabel("Units");
		lblunits.setFont(new Font("Elephant",Font.PLAIN,18));
		lblunits.setBounds(35,260,100,25);
		add(lblunits);
		
		JLabel labelunits = new JLabel("");
		labelunits.setFont(new Font("Elephant",Font.PLAIN,18));
		labelunits.setBounds(300,260,200,25);
		add(labelunits);
		
		JLabel lbltotalbill = new JLabel("Total Bill");
		lbltotalbill.setFont(new Font("Elephant",Font.PLAIN,18));
		lbltotalbill.setBounds(35,320,100,25);
		add(lbltotalbill);
		
		JLabel labeltotalbill = new JLabel("");
		labeltotalbill.setFont(new Font("Elephant",Font.PLAIN,18));
		labeltotalbill.setBounds(300,320,200,25);
		add(labeltotalbill);
		
		JLabel lblstatus = new JLabel("Status");
		lblstatus.setFont(new Font("Elephant",Font.PLAIN,18));
		lblstatus.setBounds(35,380,100,25);
		add(lblstatus);
		
		JLabel labelstatus = new JLabel("");
		labelstatus.setFont(new Font("Elephant",Font.PLAIN,18));
		labelstatus.setBounds(300,380,200,25);
		labelstatus.setForeground(Color.red);
		add(labelstatus);
		
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
			while(rs.next()) {
				meternumber.setText(meter);
				name.setText(rs.getString("name"));
			}
			
			 rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
				while(rs.next()) {
					labelunits.setText(rs.getString("units"));
					labeltotalbill.setText(rs.getString("totalbill"));
					labelstatus.setText(rs.getString("status"));
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		cmonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				try {
					Conn c = new Conn();
					ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
						while(rs.next()) {
							labelunits.setText(rs.getString("units"));
							labeltotalbill.setText(rs.getString("totalbill"));
							labelstatus.setText(rs.getString("status"));
						}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		pay = new JButton("Pay");
		pay.setBackground(Color.black);
		pay.setForeground(Color.white);
		pay.setBounds(100,460,100,25);
		pay.setFont(new Font("Elephant",Font.PLAIN,16));
		pay.addActionListener(this);
		add(pay);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(230,460,100,25);
		back.setFont(new Font("Elephant",Font.PLAIN,16));
		back.addActionListener(this);
		add(back);
		
		getContentPane().setBackground(Color.white);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 =new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400,120,600,300);
		add(image);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pay) {
			try {
				Conn c = new Conn();
				c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
			}catch(Exception e) {
				e.printStackTrace();
			}
			setVisible(false);
			new Paytm(meter);
		}else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new PayBill("");

	}

}
