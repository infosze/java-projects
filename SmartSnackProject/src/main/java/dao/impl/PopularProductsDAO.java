package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entity.PopularProduct;

public class PopularProductsDAO extends AbstractDAO {

	private final String sqlStatement1 = "CREATE VIEW my_view AS SELECT machine_id, product_id, SUM(difference) as total_diff FROM product_movement WHERE difference < 0 group by machine_id, product_id;";
	private final String sqlStatement2 = "SELECT machine_id, zipcode, city, address, product_id, name, total_diff FROM my_view NATURAL JOIN machine NATURAL JOIN product WHERE (machine_id,  total_diff) IN (SELECT machine_id, MIN(total_diff) FROM my_view group by machine_id);";
	private final String sqlStatement3 = "DROP VIEW my_view";
	private final Integer year;
	private final Integer month;

	public PopularProductsDAO(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public ArrayList<PopularProduct> getPopularProducts() {
		List<PopularProduct> popularProducts = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement pstmts1 = con.prepareStatement(sqlStatement1);
				PreparedStatement pstmts2 = con.prepareStatement(sqlStatement2);
				PreparedStatement pstmts3 = con.prepareStatement(sqlStatement3)) {
			pstmts1.execute();
			ResultSet rs = pstmts2.executeQuery();
			while (rs.next()) {
				popularProducts.add(new PopularProduct(//
						rs.getInt(1), //
						rs.getInt(2), //
						rs.getString(3), //
						rs.getString(4),
						rs.getInt(5), //
						rs.getString(6), //
						rs.getInt(7)));
			}
			pstmts3.execute();
		} catch (SQLException e) {

		}

		return null;
	}

}
