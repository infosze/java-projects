package queries.queriesImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import dao.AbstractDAO;
import queries.Query;

public class PopularProductsQuery implements Query {
	
	private static final String URLPREFIX = "jdbc:mysql://localhost:3306/";
	private static final String SCHEMA = "ssp";
	private static final String SWITCH = "?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	protected final Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return DriverManager.getConnection(URLPREFIX + SCHEMA + SWITCH, USER, PASSWORD);
	}

	private final String sqlStatement1 = "CREATE VIEW my_view AS SELECT machine_id, product_id, SUM(difference) as total_diff FROM product_movement WHERE difference < 0 group by machine_id, product_id;";
	private final String sqlStatement2 = "SELECT machine_id, zipcode, city, address, product_id, name, total_diff FROM my_view NATURAL JOIN machine NATURAL JOIN product WHERE (machine_id,  total_diff) IN (SELECT machine_id, MIN(total_diff) FROM my_view group by machine_id);";
	private final String sqlStatement3 = "DROP VIEW my_view";
	private final Integer year;
	private final Integer month;

	public PopularProductsQuery(int year, int month) {
		this.year = year;
		this.month = month;
	}

	@Override
	public List<List<String>> findQueryData() {
		List<List<String>> popularProducts = new ArrayList<List<String>>();
		try (Connection con = getConnection();
				PreparedStatement pstmts1 = con.prepareStatement(sqlStatement1);
				PreparedStatement pstmts2 = con.prepareStatement(sqlStatement2);
				PreparedStatement pstmts3 = con.prepareStatement(sqlStatement3)) {
			pstmts1.execute();
			ResultSet rs = pstmts2.executeQuery();
			while (rs.next()) {
				popularProducts.add(List.of(//
						String.valueOf(rs.getInt(1)), //
						String.valueOf(rs.getInt(2)), //
						rs.getString(3), //
						rs.getString(4),// 
						String.valueOf(rs.getInt(5)), //
						rs.getString(6), //
						String.valueOf(rs.getInt(7))));
			}
			pstmts3.execute();
		} catch (SQLException e) {

		}

		return popularProducts;
	}

}
