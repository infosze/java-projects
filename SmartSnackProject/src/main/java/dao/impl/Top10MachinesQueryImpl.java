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

public class Top10MachinesQueryImpl extends AbstractQuery{
	
	private static final String TOP_MACHINES_STATEMENT = "SELECT machine_id, SUM((-1) * difference * unit_price) AS total_income FROM product_movement NATURAL JOIN product WHERE difference < 0 AND time_stamp >= ? AND time_stamp <= ? group by machine_id order by total_income DESC limit 10;";
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Top10MachinesQueryImpl(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public List<List<String>> findDataForQuery() {
		List<List<String>> topMachines = new ArrayList<List<String>>();
		try (Connection con = getConnection(); PreparedStatement pstmts1 = con.prepareStatement(TOP_MACHINES_STATEMENT)) {
			pstmts1.setString(1, Timestamp.valueOf(startDate.atStartOfDay()).toString());
			pstmts1.setString(2, Timestamp.valueOf(endDate.atTime(23, 59, 59)).toString());
			ResultSet rs = pstmts1.executeQuery();
			int count = 1;
			while (rs.next()) {
				topMachines.add(List.of(//
						String.valueOf(count++), //
						rs.getString("machine_id"), //
						String.valueOf(rs.getInt("total_income"))));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topMachines;
	}

}
