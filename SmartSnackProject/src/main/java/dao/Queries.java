package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Queries extends SqlConnectionService {

	private static final String SOLD_OUT_COIN_MACHINE_SQL = "SELECT * FROM ssp.machine JOIN ssp.coin_movement ON ssp.coin_movement.machine_id = ssp.machine.machine_id WHERE coin_movement.time_stamp > ? AND coin_movement.time_stamp < ? AND coin_quantity = 0 GROUP BY ssp.machine.machine_id;";

	public List<List<String>> findSoldOutCoinMachine(LocalDate localDate) {
		Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
		Timestamp timestamp2 = Timestamp.valueOf(localDate.plusDays(1).atStartOfDay());
		List<List<String>> soldOutCoinMachines = new ArrayList<>();
		List<String> machine = new ArrayList<>();
		try (PreparedStatement pstmt = getConnection().prepareStatement(SOLD_OUT_COIN_MACHINE_SQL)) {
			pstmt.setString(1, timestamp.toString());
			pstmt.setString(2, timestamp2.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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

}
