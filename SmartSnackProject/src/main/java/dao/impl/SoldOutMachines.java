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

	private static final String CREATE_VIEW_STATEMENT = "CREATE VIEW view_1query AS SELECT machine_id, country, zipcode, city, address, max_sortiment, min_sortiment //"
			+ "FROM machine NATURAL JOIN machine_type;";
	private static final String CREATE_VIEW2_STATEMENT = "CREATE VIEW view_2query AS SELECT machine_id, country, zipcode, city, address, product_id, ABS(max_sortiment - min_sortiment) //"
			+ "AS difference FROM product_movement NATURAL JOIN view_1query WHERE active_report=true AND product_quantity = 0 HAVING count(product_id) >= difference;";
	private static final String FIND_SOLDOUT_MACHINES_STATEMENT = "SELECT machine_id, country, zipcode, city, address, product_id FROM product_movement //"
			+ "NATURAL JOIN machine WHERE active_report = true AND machine_id IN (SELECT machine_id FROM view_2query) AND product_quantity = 0 GROUP BY product_id;";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soldOutMachines;
	}

}
