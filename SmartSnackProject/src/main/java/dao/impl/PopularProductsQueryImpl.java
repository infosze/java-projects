package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractQuery;

public class PopularProductsQueryImpl extends AbstractQuery{
	
	private static final String CREATE_VIEW_STATEMENT = "CREATE VIEW my_view AS SELECT machine_id, product_id, SUM(difference) as total_diff FROM product_movement WHERE difference < 0 AND time_stamp >= ? AND time_stamp < ? group by machine_id, product_id;";
	private static final String FIND_POPULAR_PRODUCTS_STATEMENT = "SELECT machine_id, city, zipcode, address, product_id, name, total_diff FROM my_view NATURAL JOIN machine NATURAL JOIN product WHERE (machine_id,  total_diff) IN (SELECT machine_id, MIN(total_diff) FROM my_view group by machine_id);";
	private static final String DROP_VIEW_STATEMENT = "DROP VIEW my_view";
	
	private int year;
	private int month;
	
	

	public PopularProductsQueryImpl(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}

	@Override
	public List<List<String>> findDataForQuery() {
		List<Timestamp> timeStamps = createTimeStamps();
		List<List<String>> popularProducts = new ArrayList<List<String>>();
		try (Connection con = getConnection();
				PreparedStatement pstmts1 = con.prepareStatement(CREATE_VIEW_STATEMENT);
				PreparedStatement pstmts2 = con.prepareStatement(FIND_POPULAR_PRODUCTS_STATEMENT);
				PreparedStatement pstmts3 = con.prepareStatement(DROP_VIEW_STATEMENT)) {
			String timest1 = timeStamps.get(0).toString();
			String timest2 = timeStamps.get(1).toString();
			pstmts1.setString(1, timest1);
			pstmts1.setString(2, timest2);
			pstmts1.execute();
			ResultSet rs = pstmts2.executeQuery();
			while (rs.next()) {
				popularProducts.add(List.of(//
						rs.getString("machine_id"), //
						String.valueOf(rs.getInt("zipcode")), //
						rs.getString("city"), //
						rs.getString("address"), //
						String.valueOf(rs.getInt("product_id")), //
						rs.getString("name"), //
						String.valueOf(rs.getInt("total_diff") * (-1))));
			}
			pstmts3.execute();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return popularProducts;
	}
	
	private List<Timestamp> createTimeStamps() {
		List<Timestamp> timeStamps = new ArrayList<>();
		Timestamp start = month == 0 ? Timestamp.valueOf(LocalDate.of(year, 1, 1).atStartOfDay())
				: Timestamp.valueOf(LocalDate.of(year, month, 1).atStartOfDay());
		Timestamp end = month == 0 ? Timestamp.valueOf(LocalDate.of(year + 1, 1, 1).atStartOfDay())
				: month == 12 ? Timestamp.valueOf(LocalDate.of(year + 1, 1, 1).atStartOfDay())
						: Timestamp.valueOf(LocalDate.of(year, month + 1, 1).atStartOfDay());
		timeStamps.add(start);
		timeStamps.add(end);
		return timeStamps;
	}

}
