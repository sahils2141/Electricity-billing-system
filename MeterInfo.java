package MyPackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MeterInfo extends JFrame implements ActionListener{
	
	JTextField tfname,tfaddress,tfstate,tfcity,tfemail,tfphone;
	JButton next,cancel;
	JLabel lblmeter;
	Choice meterlocation, metertype,phasecode,billtype;
	String meternumber;
	MeterInfo(String meternumber){
		this.meternumber = meternumber;
		setSize(700,500);
		setLocation(400,250);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBackground(Color.WHITE);
		add(p);
		
		JLabel heading =new JLabel("Meter Information");
		heading.setBounds(280,10,400,20);
		heading.setFont(new Font("Elephant",Font.PLAIN,26));
		p.add(heading);
		
		JLabel lblname =new JLabel("Meter Number");
		lblname.setBounds(100,60,200,20);
		lblname.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblname);
		
		JLabel lblmeternumber =new JLabel(meternumber);
		lblmeternumber.setBounds(300,60,200,20);
		lblmeternumber.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeternumber);
		
		JLabel lblmeterlo =new JLabel("Meter Location");
		lblmeterlo.setBounds(100,100,200,20);
		lblmeterlo.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmeterlo);
		
		meterlocation = new Choice();
		meterlocation.add("Outside");
		meterlocation.add("Inside");
		meterlocation.setBounds(300,100,200,25);
		p.add(meterlocation);
		
		
		JLabel lblmetertype =new JLabel("Meter Type");
		lblmetertype.setBounds(100,140,200,20);
		lblmetertype.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblmetertype);
		
		metertype= new Choice();
		metertype.add("Electric Meter");
		metertype.add("Solar Meter");
		metertype.add("Smart Meter");
		metertype.setBounds(300,140,200,25);
		p.add(metertype);
		
		JLabel lblphasecode =new JLabel("Phase Code");
		lblphasecode.setBounds(100,180,200,20);
		lblphasecode.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblphasecode);
		
		phasecode= new Choice();
		phasecode.add("011");
		phasecode.add("022");
		phasecode.add("033");
		phasecode.add("044");
		phasecode.add("055");
		phasecode.add("066");
		phasecode.add("077");
		phasecode.add("088");
		phasecode.add("099");
		phasecode.setBounds(300,180,200,25);
		p.add(phasecode);
		
		JLabel lblbilltype =new JLabel("Bill Type");
		lblbilltype.setBounds(100,220,200,20);
		lblbilltype.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblbilltype);
		
		billtype= new Choice();
		billtype.add("Normal");
		billtype.add("Industrial");
		billtype.setBounds(300,220,200,25);
		p.add(billtype);
		
		JLabel lblday =new JLabel("Days");
		lblday.setBounds(100,260,200,20);
		lblday.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblday);
		
		JLabel lbldays =new JLabel("30 Days");
		lbldays.setBounds(300,260,200,20);
		lbldays.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lbldays);
		
		
		JLabel note =new JLabel("Note");
		note.setBounds(100,300,200,20);
		note.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(note);
		
		JLabel lblphones =new JLabel("By default bill is calculated for 30 days only");
		lblphones.setBounds(180,300,400,20);
		lblphones.setFont(new Font("Elephant",Font.PLAIN,18));
		p.add(lblphones);;
		
		next = new JButton("Submit");
		next.setBounds(250,340,100,25);
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.addActionListener(this);
		p.add(next);

		
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
			String meter = meternumber;
			String location = meterlocation.getSelectedItem();
			String type = metertype.getSelectedItem();
			String code = phasecode.getSelectedItem();
			String typebill= billtype.getSelectedItem();
			String days = "30";
			
			String query = "insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
			
			try {
				Conn c = new Conn();
				c.s.executeUpdate(query);
				
				JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
				setVisible(false);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new MeterInfo("");
	}

}
