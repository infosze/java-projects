package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AutomatDAO;
import entity.Automat;
import util.DatabaseConnect;

public class AutomatDAOimpl implements AutomatDAO {

	private static final String SOLD_OUT_PRODUCT = "SELECT * FROM freedbtech_ssp.machine;"; // TODO fix it SQL query
	private static final String SOLD_OUT_COIN = "SELECT * FROM freedbtech_ssp.machine;"; // TODO fix it SQL query
	private static final String OFFLINE = "SELECT * FROM freedbtech_ssp.machine;"; // TODO fix it SQL query

	public List<Automat> getSoldOutProductMachines() {
//		return findMachines(SOLD_OUT_PRODUCT);
		return new ArrayList<>();
	}

	public List<Automat> getSoldOutCoinMachines() {
		return findMachines(SOLD_OUT_COIN);
	}

	public List<Automat> getOfflineMachines() {
		return findMachines(OFFLINE);
//		return new ArrayList<>();
	}

	private List<Automat> findMachines(String sqlStatement) {
		List<Automat> result = new ArrayList<>();
		try (Connection con = DatabaseConnect.getConnection(); //
				PreparedStatement stm = con.prepareStatement(sqlStatement); //
				ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				result.add(new Automat(//
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
