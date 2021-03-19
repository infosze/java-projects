package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Queries extends SqlConnectionService {

	private final String sqlStatement1 = "CREATE VIEW my_view AS SELECT machine_id, product_id, SUM(difference) as total_diff FROM product_movement WHERE difference < 0 AND time_stamp >= ? AND time_stamp < ? group by machine_id, product_id;";
	private final String sqlStatement2 = "SELECT machine_id, city, zipcode, address, product_id, name, total_diff FROM my_view NATURAL JOIN machine NATURAL JOIN product WHERE (machine_id,  total_diff) IN (SELECT machine_id, MIN(total_diff) FROM my_view group by machine_id);";
	private final String sqlStatement3 = "DROP VIEW my_view";

	public List<List<String>> findQueryData(int year, int month) {
		List<Timestamp> timeStamps = createTimeStamps(year, month);
		List<List<String>> popularProducts = new ArrayList<List<String>>();
		try (Connection con = getConnection();
				PreparedStatement pstmts1 = con.prepareStatement(sqlStatement1);
				PreparedStatement pstmts2 = con.prepareStatement(sqlStatement2);
				PreparedStatement pstmts3 = con.prepareStatement(sqlStatement3)) {
			String timest1 = timeStamps.get(0).toString();
			String timest2 = timeStamps.get(1).toString();
			pstmts1.setString(1, timest1);
			pstmts1.setString(2, timest2);
			pstmts1.execute();
			ResultSet rs = pstmts2.executeQuery();
			while (rs.next()) {
				popularProducts.add(List.of(//
						String.valueOf(rs.getString("machine_id")), //
						String.valueOf(rs.getString("zipcode")), //
						rs.getString("city"), //
						rs.getString("address"), //
						String.valueOf(rs.getString("product_id")), //
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

	private List<Timestamp> createTimeStamps(int year, int month) {
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

	private static final String SOLD_OUT_COIN_MACHINE_SQL = //
			"SELECT machine.machine_id, machine.country, machine.zipcode, machine.city, machine.address FROM ssp.machine JOIN ssp.coin_movement "
					+ "ON ssp.coin_movement.machine_id = ssp.machine.machine_id WHERE coin_movement.time_stamp >= ? "
					+ "AND coin_movement.time_stamp < ? AND coin_quantity = 0 GROUP BY ssp.machine.machine_id;";

	public List<List<String>> findSoldOutCoinMachine(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		Timestamp timestamp2 = Timestamp.valueOf(localDate.plusDays(1).atStartOfDay());
		List<List<String>> soldOutCoinMachines = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(SOLD_OUT_COIN_MACHINE_SQL)) {
			pstmt.setString(1, timestamp.toString());
			pstmt.setString(2, timestamp2.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				List<String> machine = new ArrayList<>();
				machine.add(rs.getString("machine.machine_id"));
				machine.add(rs.getString("machine.country"));
				machine.add(rs.getString("machine.zipcode"));
				machine.add(rs.getString("machine.city"));
				machine.add(rs.getString("machine.address"));
				soldOutCoinMachines.add(machine);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soldOutCoinMachines;
	}

	private static final String UPLOADS_SQL = //
			"SELECT machine.machine_id, machine.country, machine.zipcode, machine.city, machine.address "
					+ "FROM ssp.machine JOIN ssp.product_movement ON ssp.product_movement.machine_id = ssp.machine.machine_id "
					+ "WHERE product_movement.time_stamp >= ? AND product_movement.time_stamp < ? "
					+ "AND product_movement.difference > 0 GROUP BY product_movement.machine_id ORDER BY sum(product_movement.difference) DESC;";

	public List<List<String>> findUploads(LocalDate startLocalDate, LocalDate endLocalDate) {
		Timestamp timestamp = Timestamp.valueOf(startLocalDate.atStartOfDay());
		Timestamp timestamp2 = Timestamp.valueOf(endLocalDate.plusDays(1).atStartOfDay());
		List<List<String>> findUploadsMachines = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(UPLOADS_SQL)) {
			pstmt.setString(1, timestamp.toString());
			pstmt.setString(2, timestamp2.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				List<String> machine = new ArrayList<>();
				machine.add(rs.getString("machine.machine_id"));
				machine.add(rs.getString("machine.country"));
				machine.add(rs.getString("machine.zipcode"));
				machine.add(rs.getString("machine.city"));
				machine.add(rs.getString("machine.address"));
				findUploadsMachines.add(machine);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findUploadsMachines;
	}

}
