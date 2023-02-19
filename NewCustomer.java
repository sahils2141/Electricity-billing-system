package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class NewCustomer extends JFrame implements ActionListener{
	
	JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
	JButton next,cancel;
	JLabel lblmeter;
	NewCustomer(){
		setSize(700,500);
		setLocation(400,250);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		add(p);
		
		JLabel heading =new JLabel("New Customer");
		heading.setBounds(300,10,200,20);
		heading.setFont(new Font("Elephant",Font.PLAIN,26));
		p.add(heading);
		
		JLabel lblname =new JLabel("Customer Name");
		lblname.setBounds(100,60,200,20);
		lblname.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(300,60,200,25);
		p.add(tfname);
		
		JLabel lblmeterno =new JLabel("Meter Number");
		lblmeterno.setBounds(100,100,200,20);
		lblmeterno.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeterno);
		
		lblmeter =new JLabel("");
		lblmeter.setBounds(300,100,200,20);
		lblmeter.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeter);
		
		Random ran = new Random();
		long number = ran.nextLong() % 100000000;
		lblmeter.setText("" + Math.abs(number));
		
		JLabel lbladdress =new JLabel("Address");
		lbladdress.setBounds(100,140,200,20);
		lbladdress.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lbladdress);
		
		tfaddress = new JTextField();
		tfaddress.setBounds(300,140,200,25);
		p.add(tfaddress);
		
		JLabel lblcity =new JLabel("City");
		lblcity.setBounds(100,180,200,20);
		lblcity.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblcity);
		
		tfcity = new JTextField();
		tfcity.setBounds(300,180,200,25);
		p.add(tfcity);
		
		JLabel lblstate =new JLabel("State");
		lblstate.setBounds(100,220,200,20);
		lblstate.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblstate);
		
		tfstate = new JTextField();
		tfstate.setBounds(300,220,200,25);
		p.add(tfstate);
		
		JLabel lblemail =new JLabel("Email");
		lblemail.setBounds(100,260,200,20);
		lblemail.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblemail);
		
		tfemail = new JTextField();
		tfemail.setBounds(300,260,200,25);
		p.add(tfemail);
		
		JLabel lblphone =new JLabel("Phone Number");
		lblphone.setBounds(100,300,200,20);
		lblphone.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblphone);
		
		tfphone = new JTextField();
		tfphone.setBounds(300,300,200,25);
		p.add(tfphone);
		
		next = new JButton("Next");
		next.setBounds(120,340,100,25);
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.addActionListener(this);
		p.add(next);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(250,340,100,25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		p.add(cancel);
		
		setLayout (new BorderLayout());
		add(p,"Center");
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
		Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image, "East");
		
		getContentPane().setBackground(Color.white);
		
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==next) {
			String name = tfname.getText();
			String meter = lblmeter.getText();
			String address = tfaddress.getText();
			String city = tfcity.getText();
			String state = tfstate.getText();
			String email = tfemail.getText();
			String phone = tfphone.getText();
			
			String query1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
			String query2 = "insert into login values('"+meter+"','','"+name+"','','')";
			
			try {
				Conn c = new Conn();
				c.s.executeUpdate(query1);
				c.s.executeUpdate(query2);
				
				JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
				setVisible(false);
				
				new MeterInfo(meter);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new NewCustomer();
	}

}
