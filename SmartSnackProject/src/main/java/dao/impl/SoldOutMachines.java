package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Query;
import util.DatabaseConnect;

public class SoldOutMachines implements Query {

	private static final String CREATE_VIEW_STATEMENT = "CREATE VIEW view_1query AS SELECT machine_id, country, zipcode, city, address, "
			+ "(max_sortiment - min_sortiment) AS difference FROM machine NATURAL JOIN machine_type;";
	private static final String CREATE_VIEW2_STATEMENT = "CREATE VIEW view_2query AS SELECT machine_id, COUNT(product_id) AS missing_products "
			+ "FROM product_movement WHERE product_quantity = 0 AND active_report = 1 GROUP BY machine_id;";
	private static final String FIND_SOLDOUT_MACHINES_STATEMENT = "SELECT product_movement.machine_id, country, zipcode, city, address, product_id "
			+ "FROM product_movement INNER JOIN view_1query ON product_movement.machine_id = view_1query.machine_id "
			+ "WHERE product_quantity = 0 AND active_report = true AND product_movement.machine_id "
			+ "IN (SELECT view_1query.machine_id FROM view_1query NATURAL JOIN view_2query WHERE missing_products >= difference);";
	private static final String DROP_VIEW_STATEMENT = "DROP VIEW view_1query, view_2query";

	@Override
	public List<List<String>> findDataForQuery() {
		List<List<String>> soldOutMachines = new ArrayList<>();
		try (Connection con = DatabaseConnect.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(CREATE_VIEW_STATEMENT);
				PreparedStatement pstmt2 = con.prepareStatement(CREATE_VIEW2_STATEMENT);
				PreparedStatement pstmt3 = con.prepareStatement(FIND_SOLDOUT_MACHINES_STATEMENT);
				PreparedStatement pstmt4 = con.prepareStatement(DROP_VIEW_STATEMENT)) {
			pstmt1.execute();
			pstmt2.execute();
			ResultSet rs = pstmt3.executeQuery();
			while (rs.next()) {
				soldOutMachines.add(List.of(//
						rs.getString("machine_id"), //
						rs.getString("country"), //
						String.valueOf(rs.getInt("zipcode")), //
						rs.getString("city"), //
						rs.getString("address"), //
						String.valueOf(rs.getInt("product_id"))));
			}
			pstmt4.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soldOutMachines;
	}

}
