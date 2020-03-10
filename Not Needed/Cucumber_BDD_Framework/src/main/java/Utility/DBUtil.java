package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	Connection con;
	Statement stmt;
	ResultSet rs;

	public DBUtil(String strURL,String strUser,String strPassword)
	{
		try {
			// Load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Create the connection object
			con = DriverManager.getConnection(strURL,strUser, strPassword);

			// Create the statement object
			stmt = con.createStatement();			

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean verifyTableColumnValueInDB(String strTableName,String strColName, String strRecordValue) throws SQLException
	{
		Boolean blReturn = null;
		
			// Execute query			
			rs = stmt.executeQuery("SELECT * FROM "+ strTableName +" where " + strColName + " = '" + strRecordValue + "'");
			rs.next();
			if((rs.getString(1).length()) >= 1)			
				blReturn = true;
			else
				blReturn = false;
			return blReturn;		
	}
	
	
	public boolean verifyRecordInDB(String strTableName,String strColName, String strRecordValue) throws SQLException
	{
		Boolean blReturn = null;

		// Execute query			
		rs = stmt.executeQuery("SELECT * FROM "+ strTableName +" where " + strColName + " = '" + strRecordValue + "'");
		//while (rs.next())
		rs.next();
		//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		if((rs.getString(1).length()) >= 1)			
			blReturn = true;
		else 
			blReturn = false;
		return blReturn;		
	}

	public String verifyReportingCycleInDB(String strFileReportingCycle ) throws SQLException
	{
		int ReportingCycle =Integer.parseInt(strFileReportingCycle);
		// Execute query              
		rs = stmt.executeQuery("SELECT Status FROM TBL_CSPR_REPORTINGCYCLE WHERE NAME='" + strFileReportingCycle + "'");
		/*while(rs.next()){
                      value=rs.getString(1);
                }*/
		rs.next();
		//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		if((rs.getString(1).length()) >= 1)                  
			return rs.getString(1);
		else
			return null;

	}

	

	public String verifyColumnValueNotDeleted(String strTableName,String strColName1, String strColName2,String strValue1, String strValue2,String Isdelete, String value ) throws SQLException
	{
		// Execute query       
		//STATUS
		//CODE
		
		
		rs = stmt.executeQuery("SELECT COUNT(*) FROM "+ strTableName + " WHERE " + strColName1 + "='" + strValue1 + "' and " + strColName2 + "='" + strValue2 + "' and " + Isdelete + "='"+ value + "'");
		rs.next();
		return rs.getString(1);
		
	}
	public String verifyTableColumnValueNotInDB(String strTableName,String strColName, String strRecordValue ) throws SQLException
	{
		System.out.println("strRecordValue = " + strRecordValue);

		//int ReportingCycle =Integer.parseInt(strRecordValue);
		// Execute query              
		rs = stmt.executeQuery("SELECT COUNT(*) FROM TBL_CSPR_REPORTINGCYCLE WHERE NAME='" + strRecordValue + "'");
		/*while(rs.next()){
              value=rs.getString(1);
        }*/
		rs.next();
		//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		if( (rs.getString(1).length()) >= 1)                  
			return rs.getString(1);
		else
			return null;

	}
	public String verifyReportingCycleInDB1(String strFileReportingCycle ) throws SQLException
	{
		int ReportingCycle =Integer.parseInt(strFileReportingCycle);
		// Execute query                                             
		rs = stmt.executeQuery("SELECT Status FROM TBL_CSPR_REPORTINGCYCLE WHERE NAME='" + strFileReportingCycle + "'");
		/*while(rs.next()){
                                                 value=rs.getString(1);
                                  }*/
		rs.next();
		//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		if( (rs.getString(1).length()) >= 1)                                
			return rs.getString(1);
		else
			return null;
	}
	public String verifyReportingCycleInDB2(String strFileReportingCycle ) throws SQLException
	{
		int ReportingCycle =Integer.parseInt(strFileReportingCycle);
		// Execute query                                             
		rs = stmt.executeQuery("SELECT Status FROM TBL_CSPR_REPORTINGCYCLE WHERE NAME='" + strFileReportingCycle + "'");
		/*while(rs.next()){
                                                 value=rs.getString(1);
                                  }*/
		rs.next();
		//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		if( (rs.getString(1).length()) >= 1)                                
			return rs.getString(1);
		else
			return null;
	}

	public boolean verifyRCValuesInDB(String strTableName,String strColName1,String strColName2,String strColName3,String strColName4,String strColName5, String strRecordValue1,String strRecordValue2,String strRecordValue3,String strRecordValue4,String strRecordValue5) throws SQLException
	{
		Boolean blReturn = null;
		
			// Execute query			
			rs = stmt.executeQuery("SELECT * FROM "+ strTableName +" where " + strColName1 + " = '"+strRecordValue1 + "' AND" + strColName1 + " = '"+strRecordValue1 +"' AND "
					+ strColName2 + " = '"+strRecordValue2 +"' AND "
					+ strColName3 + " = '"+strRecordValue3 +"' AND "
					+ strColName4 + " = '"+strRecordValue4 +"' AND "
					+ strColName5 + " = '"+strRecordValue5+"'" );
			//while (rs.next())
			rs.next();
			//System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			if( (rs.getString(1).length()) >= 1)			
				blReturn = true;
			else
				blReturn = false;
			return blReturn;		
	}
	

	public void finalize() throws SQLException 
	{		
		System.out.println("Book instance is getting destroyed");
		// step5 close the connection object
		con.close();
	}
}



