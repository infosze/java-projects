package dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.UserDAO;
import util.DatabaseConnect;

public class ChangePasswordImpl implements UserDAO {

	private static final String CHANGE_USER_PASSWORD_SQL = //
			"UPDATE freedbtech_ssp.users SET password = ?, salt = ? WHERE users.user_name = ?;";

	private String userName;
	private String password;
	private String salt;

	public ChangePasswordImpl(String userName, String password, String salt) {
		this.userName = userName;
		this.password = password;
		this.salt = salt;
	}

	@Override
	public int updateUserPassword() {
		int updatedNumber = -1;
		try (PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(CHANGE_USER_PASSWORD_SQL)) {
			pstmt.setString(3, userName);
			pstmt.setString(1, password);
			pstmt.setString(2, salt);
			updatedNumber = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedNumber;
	}

}
