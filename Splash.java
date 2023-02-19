package MyPackage;

import javax.swing.*;

public class Splash extends JFrame implements Runnable{
	Thread t;
	Splash(){
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
		
		JLabel image = new JLabel(i1);
		add(image);
		
		setVisible(true);
		
		int a =1;
		
		for(int i = 1;i<500;i++, a+=1) {
			setSize(i,i);
			setLocation(900 - i,400 - (i/2));
			
			try {
				Thread.sleep(3);
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
		t = new Thread(this);
		t.start();
		
		
		setVisible(true);
		
	}
	public void run() {
		try {
			Thread.sleep(1000);
			setVisible(false);
			
			new Login();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Splash();

	}

}
