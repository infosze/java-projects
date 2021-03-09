package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Automat;

public class AutomatErrorDAO extends AbstractDao {

	private static final String SOLD_OUT_COIN = "SELECT * FROM ssp.machine;";

	public List<Automat> findSoldOutCoinMachines() {
		List<Automat> result = new ArrayList<>();
		try (Connection con = getConnection(); //
				PreparedStatement stm = con.prepareStatement(SOLD_OUT_COIN); //
				ResultSet rs = stm.executeQuery()) {
			while (rs.next()) {
				result.add(new Automat(//
						rs.getString("machine_id"), //
						rs.getInt("machine_type_id"), //
						rs.getString("country"), //
						rs.getInt("zipcode"), //
						rs.getString("county"), //
						rs.getString("address")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

//	List<Automat> findSoldOutProductMachines();

//	List<Automat> findWrongMachines();

}
