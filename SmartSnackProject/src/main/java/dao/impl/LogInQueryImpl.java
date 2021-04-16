package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractQuery;

public class LogInQueryImpl extends AbstractQuery {

	private static final String USER_PASSWORD_SQL = //
			"SELECT user_name, password, salt FROM ssp.users WHERE users.user_name = ?;";

	private String userName;

	public LogInQueryImpl(String userName) {
		this.userName = userName;
	}

	@Override
	public List<List<String>> findDataForQuery() {
		List<List<String>> findUserAndPassword = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(USER_PASSWORD_SQL)) {
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				List<String> user = new ArrayList<>();
				user.add(rs.getString("user_name"));
				user.add(rs.getString("password"));
				user.add(rs.getString("salt"));
				findUserAndPassword.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findUserAndPassword;
	}

}
