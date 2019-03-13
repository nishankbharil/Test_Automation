package Lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test_Data_Base {

	public static void main(String[] args) {
		try {
			String databaseUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // For Oracle XE db
			// String databaseUrl = "jdbc:mysql:@localhost:3306:<Your MySQL db name>"; //
			// For MySql db
			Connection conn = DriverManager.getConnection(databaseUrl, "<Your db username>", "<Your db password>");

			int rows = getRowCount(conn, "tab"); // Get row count in table 'tab'
			System.out.println("Count of rows in table tab: " + rows);

			List<HashMap<String, String>> result = executeSelectQuery(conn, "Select * from tab");
			for (HashMap<String, String> map : result) {
				for (String key : map.keySet())
					System.out.print(key + "=" + map.get(key) + "  ");

				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method: - returns row count in given table if table exist and there is
	 * no exception. - returns -1 if specified table doesn't exist or there is any
	 * exception.
	 */
	public static int getRowCount(Connection conn, String tableName) {
		try {
			PreparedStatement preStatement = conn.prepareStatement("Select count(*) from " + tableName);
			ResultSet result = preStatement.executeQuery();
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/*
	 * Returns result of select query as ArrayList of HashMap<Column_Name, Value>
	 */
	public static List<HashMap<String, String>> executeSelectQuery(Connection conn, String query) {
		if (query.replaceAll(" ", "").toLowerCase().startsWith("selectcount(")) {
			String execptionMessage = "Cannot use this method for 'Select count(<COLUMN_NAME>) from <TABLE_NAME>'. Please use method getRowCount(String slelectQuery)";
			System.out.println(execptionMessage);
			return null;
		}

		if (query.endsWith(";")) // remove trailing semicolon (if any)
			query = query.substring(0, query.length() - 1);

		try {
			PreparedStatement preStatement = conn.prepareStatement(query);
			ResultSet result = preStatement.executeQuery();

			ResultSetMetaData rsmd = result.getMetaData();
			List<String> columns = new ArrayList<String>();
			for (int col = 1; col <= rsmd.getColumnCount(); col++) {
				String name = rsmd.getColumnName(col);
				columns.add(name);
			}

			List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

			while (result.next()) {
				HashMap<String, String> rowData = new HashMap<String, String>();
				for (String colName : columns) {
					rowData.put(colName,
							result.getObject(colName) == null ? null : result.getObject(colName).toString());
				}

				data.add(rowData);
			}

			return data;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}