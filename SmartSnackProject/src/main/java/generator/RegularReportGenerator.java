package generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseConnect;

public class RegularReportGenerator {

	private static final String ACTIVE_REPORTS_QUERY = "SELECT machine_id, product_id, product_quantity FROM product_movement_test WHERE active_report = 1;";
	private static final String PREVIOUS_REPORT_UPDATE = "UPDATE product_movement_test SET active_report = 0 WHERE active_report = 1 "
			+ "AND machine_id = ? AND product_id = ?;";
	private static final String NEW_REPORT_INSERT = "INSERT INTO product_movement_test (machine_id, product_id, product_quantity, difference) "
			+ "VALUES (?, ?, ?, ?);";

	public void run() {
		try (Connection con = DatabaseConnect.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(ACTIVE_REPORTS_QUERY);
				PreparedStatement pstmt2 = con.prepareStatement(PREVIOUS_REPORT_UPDATE);
				PreparedStatement pstmt3 = con.prepareStatement(NEW_REPORT_INSERT)) {
			ResultSet rs = pstmt1.executeQuery();
			con.setAutoCommit(false);
			while (rs.next()) {
				String machineId = rs.getString(1);
				int productId = rs.getInt(2);
				int productQuant = rs.getInt(3);
				pstmt2.setString(1, machineId);
				pstmt2.setInt(2, productId);
				pstmt2.executeUpdate();
				pstmt3.setString(1, machineId);
				pstmt3.setInt(2, productId);
				int difference = productQuant == 0 ? 0 : calculateDiff(productQuant);
				pstmt3.setInt(3, productQuant - difference);
				pstmt3.setInt(4, difference);
				pstmt2.addBatch();
				pstmt3.addBatch();
			}
			pstmt2.executeBatch();
			pstmt3.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			System.err.println("Hiba az adatbázisműveletben");
			e.printStackTrace();
		}
	}

	private int calculateDiff(int productQuant) {
		return productQuant >= 3 ? (int) (Math.random() * 4) : (int) (Math.random() * 2);
	}

}
