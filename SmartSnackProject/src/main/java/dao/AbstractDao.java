package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {

//	private static final String URL = "jdbc:mysql://localhost:3306/todo_list?serverTimezone=UTC\";
	private static final String URLPREFIX = "jdbc:mysql://localhost:3306/";
	private static final String SCHEMA = "ssp?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	protected final Connection getConnection() throws SQLException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(URLPREFIX + SCHEMA, USER, PASSWORD);
	}

}
