package db;

import java.sql.*;

public class DBConnection {

	private Connection conn = null;

	private String driver = "com.hxtt.sql.access.AccessDriver";

	private String url = "jdbc:access:///res/db/Fms.mdb";

	private String userName = "admin";

	private String passWord = "admin";

	public Connection getConnection() throws Exception {

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new Exception(e);
			
		}

		return conn;
	}
}
