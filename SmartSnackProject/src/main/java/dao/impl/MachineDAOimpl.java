package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.MachineDAO;
import entity.Machine;
import util.DatabaseConnect;

public class MachineDAOimpl implements MachineDAO {

	private static final String OFFLINE = //
			"SELECT * FROM machine WHERE machine.machine_id NOT IN (SELECT machine.machine_id "
					+ "FROM machine JOIN product_movement ON product_movement.machine_id = machine.machine_id "
					+ "WHERE product_movement.time_stamp >= ? AND product_movement.time_stamp < ? "
					+ "AND machine.machine_type_id > 3 GROUP BY machine.machine_id ) AND machine.machine_type_id > 3; ";
// '2020-11-20 20:05:00'     '2020-11-20 22:05:00'

	private static final String SOLD_OUT_PRODUCT = "SELECT * FROM freedbtech_ssp.machine;"; // TODO fix it SQL query
	private static final String SOLD_OUT_COIN = "SELECT * FROM freedbtech_ssp.machine;"; // TODO fix it SQL query

	@Override
	public List<Machine> getOfflineMachines() {
		Timestamp timestamp = Timestamp.valueOf(reportStartTime().minusHours(2).minusMinutes(10));
		Timestamp timestamp2 = Timestamp.valueOf(reportStartTime());
		List<Machine> result = new ArrayList<>();
		try (PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(OFFLINE)) {
			pstmt.setString(1, timestamp.toString());
			pstmt.setString(2, timestamp2.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(new Machine(//
						rs.getString("machine_id"), //
						rs.getInt("machine_type_id"), //
						rs.getString("country"), //
						rs.getInt("zipcode"), //
						rs.getString("city"), //
						rs.getString("address")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Machine> getSoldOutProductMachines() {
		return findMachines(SOLD_OUT_PRODUCT);
//		return new ArrayList<>();
	}

	@Override
	public List<Machine> getSoldOutCoinMachines() {
		return findMachines(SOLD_OUT_COIN);
//		return new ArrayList<>();
	}

	private LocalDateTime reportStartTime() {
		LocalDateTime minus = null;
		LocalDateTime startDate = LocalDateTime.of(2020, 11, 20, 22, 15); // LocalDateTime.now(); TODO fix it!
		if (startDate.getMinute() < 10) {
			minus = startDate.minusHours(1);
		} else {
			minus = startDate;
		}
		LocalDateTime result = LocalDateTime.of(minus.getYear(), minus.getMonth(), minus.getDayOfMonth(),
				minus.getHour(), 10);
		return result;
	}

	private List<Machine> findMachines(String sqlStatement) {
		List<Machine> result = new ArrayList<>();
		try (Connection con = DatabaseConnect.getConnection(); //
				PreparedStatement stm = con.prepareStatement(sqlStatement); //
				ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				result.add(new Machine(//
						rs.getString("machine_id"), //
						rs.getInt("machine_type_id"), //
						rs.getString("country"), //
						rs.getInt("zipcode"), //
						rs.getString("city"), //
						rs.getString("address")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

}
