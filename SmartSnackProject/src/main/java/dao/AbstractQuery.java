package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractQuery implements Query {

	private static final String URLPREFIX = "jdbc:mysql://freedb.tech:3306/"; // localhost
	private static final String SCHEMA = "freedbtech_ssp"; // ssp
//	private static final String SWITCH = "?serverTimezone=UTC";
	private static final String USER = "freedbtech_tadasob";// root
	private static final String PASSWORD = "5!Yuj2wihEk!g9r";// root

	protected final Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(URLPREFIX + SCHEMA /* + SWITCH */ , USER, PASSWORD);
	}

}
