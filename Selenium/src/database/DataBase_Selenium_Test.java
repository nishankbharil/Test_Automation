package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase_Selenium_Test {

	public static void main(String[] args) throws SQLException {

		
		Connection con = DriverManager.getConnection("","","");
		
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery("");
		
		while(rs.next())
		{
			String s1 = rs.getString("");
		}

	}

}
