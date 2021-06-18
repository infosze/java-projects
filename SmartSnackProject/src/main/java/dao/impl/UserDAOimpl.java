package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDAO;
import entity.User;
import util.DatabaseConnect;

public class UserDAOimpl implements UserDAO {

	private static final String CHANGE_USER_PASSWORD_SQL = //
			"UPDATE freedbtech_ssp.users SET password = ?, salt = ? WHERE users.user_name = ?;";

	private String userName;
	private String password;
	private String salt;

	public UserDAOimpl() {
	}

	public UserDAOimpl(String userName, String password, String salt) {
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

	private static final String FIND_USER_BY_NAME = //
			"SELECT * FROM freedbtech_ssp.users WHERE users.user_name = ?;";

	@Override
	public User findUserByName(String name) {
		User user = new User();
		try (PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(FIND_USER_BY_NAME)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setEmail(rs.getString("e-mail"));
				user.setRole(rs.getString("role"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editUser(User user) {
		int updatedNumber = -1;
		try (PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(CHANGE_USER_PASSWORD_SQL)) {
			pstmt.setString(3, user.getName());
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getSalt());
			updatedNumber = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedNumber;
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
