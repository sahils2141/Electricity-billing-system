package MyPackage;

import java.sql.*;
import java.sql.DriverManager;

public class Conn {
	
	Connection c;
	Statement s;
	Conn(){
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs","root","Sahil@1880");
			s=c.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
	}

}
