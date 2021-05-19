package generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.DatabaseConnect;

public class FirstReportGenerator {

	private static final String MACHINE_TYPES = "SELECT machine_type_id, max_sortiment FROM machine_type;";
	private static final String PRODUCTS_FOR_MACHINES = "SELECT product_id FROM provided_product_tpyes "
			+ "JOIN product ON provided_product_tpyes.product_type_id = product.product_type_id WHERE is_provided = 1 AND machine_type_id = ?;";
	private static final String MACHINES_WITH_MACHINE_TYPE = "SELECT machine_id FROM machine WHERE machine_type_id = ?;";
	private static final String INSERT_FIRST_REPORT = "INSERT INTO product_movement_test (machine_id, product_id, product_quantity, difference) "
			+ "VALUES (?, ?, 20, 0);";

	public void run() {
		try {
			Map<Integer, Integer> machineTypes = getMachineTypesWithSortiment();
			Set<Integer> types = machineTypes.keySet();
			for (int type : types) {
				insertDataIntoReports(type, machineTypes.get(type));
			}
		} catch (SQLException e) {
			System.err.println("Hiba az adatbázisműveletben");
			e.printStackTrace();
		}
	}

	private void insertDataIntoReports(int machineType, int sortiment) throws SQLException {
		List<String> machines = getMachineIds(machineType);
		List<Integer> products = getProductsForMachineType(machineType);
		try (Connection con = DatabaseConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_FIRST_REPORT)) {
			con.setAutoCommit(false);
			for (String machineId : machines) {
				List<Integer> productPool = new LinkedList<>(products);
				Collections.shuffle(productPool);
				for (int i = 0; i < sortiment; i++) {
					pstmt.setString(1, machineId);
					pstmt.setInt(2, productPool.remove(0));
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		}
	}

	List<String> getMachineIds(int machineType) throws SQLException {
		List<String> machines = new ArrayList<>();
		try (Connection con = DatabaseConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(MACHINES_WITH_MACHINE_TYPE)) {
			pstmt.setInt(1, machineType);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				machines.add(rs.getString(1));
			}
		}
		return machines;
	}

	private List<Integer> getProductsForMachineType(int machineType) throws SQLException {
		List<Integer> products = new LinkedList<>();
		try (Connection con = DatabaseConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(PRODUCTS_FOR_MACHINES)) {
			pstmt.setInt(1, machineType);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				products.add(rs.getInt(1));
			}
		}
		return products;
	}

	private Map<Integer, Integer> getMachineTypesWithSortiment() throws SQLException {
		Map<Integer, Integer> machineTypes = new HashMap<>();
		try (Connection con = DatabaseConnect.getConnection(); PreparedStatement pstmt = con.prepareStatement(MACHINE_TYPES)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				machineTypes.put(rs.getInt(1), rs.getInt(2));
			}
		}
		return machineTypes;
	}

}
