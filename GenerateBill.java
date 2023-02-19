package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class GenerateBill extends JFrame implements ActionListener{
	String meter;
	JButton bill,print;
	Choice cmonth;
	JTextArea area;
	GenerateBill(String meter){
		this.meter = meter;
		setSize(500,800);
		setLocation(550,30);
		
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JLabel heading = new JLabel("Generate Bill");
		heading.setFont(new Font("Elephant",Font.PLAIN,18));
		JLabel meternumber = new JLabel(meter);
		
		cmonth = new Choice();
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
		
		area = new JTextArea(50,15);
		area.setText("\n\n\t--------Click on the----------\n\t Generate Bill Button to get\n\t the bill of selected month");
		area.setFont(new Font("Times New Roman", Font.PLAIN,18));
		
		JScrollPane pane = new JScrollPane(area);
		bill = new JButton ("Generate Bill");
		bill.addActionListener(this);
		panel.add(heading);
		
		panel.add(meternumber);
		panel.add(cmonth);
		
		add(panel,"North");
		
		add(pane,"Center");
		add(bill,"South");
		
		print = new JButton("Print");
		print.addActionListener(this);
		panel.add(print);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
			Conn c = new Conn();
			String month = cmonth.getSelectedItem();
			area.setText("\tRelince Power Limited\n\n ELECTRICITY BILL GENERATED FOR THE MONTH OF \n\t"+month+",2022 \n\n");
			ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
			if(rs.next()) {
				area.append("\n    Customer Name : "+ rs.getString("name"));
				area.append("\n    Meter Number  : "+ rs.getString("meter_no"));
				area.append("\n    Address  : "+ rs.getString("address"));
				area.append("\n    City          : "+ rs.getString("city"));
				area.append("\n    State         : "+ rs.getString("state"));
				area.append("\n    Email         : "+ rs.getString("email"));
				area.append("\n    Phone Number  : "+ rs.getString("phone"));
				area.append("\n------------------------------------------------");
				area.append("\n");
				
			}
			
			rs = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");
			if(rs.next()) {
				area.append("\n    Meter Location : "+ rs.getString("meter_location"));
				area.append("\n    Meter Type  : "+ rs.getString("meter_type"));
				area.append("\n    Phase Code  : "+ rs.getString("phase_code"));
				area.append("\n    Bill Type          : "+ rs.getString("bill_type"));
				area.append("\n    Days         : "+ rs.getString("days"));
				
				area.append("\n------------------------------------------------");
				area.append("\n");
				
			}
			
			rs = c.s.executeQuery("select * from tax");
			if(rs.next()) {
				area.append("\n");
				area.append("\n    Cost Per Unit : "+ rs.getString("cost_per_unit"));
				area.append("\n    Meter Rent  : "+ rs.getString("meter_rent"));
				area.append("\n    Service Charge  : "+ rs.getString("service_charge"));
				area.append("\n    Service Tax          : "+ rs.getString("service_tax"));
				area.append("\n    Swacch Bharat  Cess        : "+ rs.getString("swacch_bharat_cess"));
				area.append("\n    Fixed tax: "+ rs.getString("fixed_tax"));
			}
			
			rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+month+"'");
			if(rs.next()) {
				area.append("\n");
				area.append("\n    Current Month : "+ rs.getString("month"));
				area.append("\n    Units Cunsumed  : "+ rs.getString("units"));
				area.append("\n    Total Charge  : "+ rs.getString("totalbill"));
				area.append("\n-----------------------------------------------");
				area.append("\n    Total Payble: "+ rs.getString("totalbill"));
				area.append("\n");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			area.print();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GenerateBill("");
	}

}
