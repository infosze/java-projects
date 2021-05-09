package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.Query;
import util.DatabaseConnect;

public class FaultyMachinesQuepyImpl implements Query {

	private static final String FAULTY_MACHINES_SQL = //
			"SELECT * FROM machine WHERE machine.machine_id NOT IN (SELECT machine.machine_id "
					+ "FROM machine JOIN product_movement ON product_movement.machine_id = machine.machine_id "
					+ "WHERE product_movement.time_stamp >= ? AND product_movement.time_stamp < ? "
					+ "AND machine.machine_type_id > 3 GROUP BY machine.machine_id ) AND machine.machine_type_id > 3; ";
// '2020-11-20 20:05:00'     '2020-11-20 22:05:00'

	private LocalDateTime reportStartTime() {
		LocalDateTime minus = null;
		LocalDateTime startDate = LocalDateTime.of(2020, 11, 20, 22, 15); // LocalDateTime.now(); TODO fix it!
		if (startDate.getMinute() < 10) {
			minus = startDate.minusHours(1);
		} else {
			minus = startDate;
		}
		LocalDateTime result = LocalDateTime.of(minus.getYear(), minus.getMonth(), minus.getDayOfMonth(), minus.getHour(), 10);
		return result;
	}

	@Override
	public List<List<String>> findDataForQuery() {
		Timestamp timestamp = Timestamp.valueOf(reportStartTime().minusHours(2).minusMinutes(10));
		Timestamp timestamp2 = Timestamp.valueOf(reportStartTime());
		List<List<String>> list = new ArrayList<>();
		try (PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(FAULTY_MACHINES_SQL)) {
			pstmt.setString(1, timestamp.toString());
			pstmt.setString(2, timestamp2.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				List<String> machine = new ArrayList<>();
				machine.add(rs.getString("machine.machine_id"));
				machine.add(rs.getString("machine.zipcode"));
				machine.add(rs.getString("machine.city"));
				machine.add(rs.getString("machine.address"));
				list.add(machine);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
