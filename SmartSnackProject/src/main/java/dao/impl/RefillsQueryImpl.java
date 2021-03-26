package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractQuery;

public class RefillsQueryImpl extends AbstractQuery{
	
	private static final String UPLOADS_SQL = //
			"SELECT machine.machine_id, machine.country, machine.zipcode, machine.city, machine.address "
					+ "FROM ssp.machine JOIN ssp.product_movement ON ssp.product_movement.machine_id = ssp.machine.machine_id "
					+ "WHERE product_movement.time_stamp >= ? AND product_movement.time_stamp < ? "
					+ "AND product_movement.difference > 0 GROUP BY product_movement.machine_id ORDER BY sum(product_movement.difference) DESC;";
	private LocalDate startDate;
	private LocalDate endDate;
	
	public RefillsQueryImpl(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public List<List<String>> findDataForQuery() {
		Timestamp timestamp = Timestamp.valueOf(startDate.atStartOfDay());
		Timestamp timestamp2 = Timestamp.valueOf(endDate.plusDays(1).atStartOfDay());
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
