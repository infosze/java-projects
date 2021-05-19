package generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Machine;
import util.DatabaseConnect;

public class MachineGenerator {

	private static final String MACHINE_TYPES_ST = "SELECT machine_type_id FROM machine_type;";
	private static final String MACHINE_INSERTER_ST = "INSERT INTO machine (machine_type_id, country, zipcode, city, address) VALUES (?, ?, ?, ?, ?)";

	public void run(int number) {
		try {
			List<Machine> machines = generateMachines(number);
			insertMachines(machines);
		} catch (SQLException e) {
			System.out.println("Hiba az adatbázisműveletben.");
			e.printStackTrace();
		}
	}

	private void insertMachines(List<Machine> machines) throws SQLException {
		try (Connection con = DatabaseConnect.getConnection();
				PreparedStatement pstmt = con.prepareStatement(MACHINE_INSERTER_ST)) {
			con.setAutoCommit(false);
			for (Machine machine : machines) {
				pstmt.setInt(1, machine.getMachineTypeId());
				pstmt.setString(2, machine.getCoutry());
				pstmt.setInt(3, machine.getZipCode());
				pstmt.setString(4, machine.getCity());
				pstmt.setString(5, machine.getAddress());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		}
	}

	private List<Machine> generateMachines(int number) throws SQLException {
		List<Machine> machines = new ArrayList<>(number);
		while (machines.size() < number) {
			Machine machine = new Machine();
			machine.setMachineTypeId(selectRandomMachineType());
			machine.setCoutry("Magyarország");
			machine.setZipCode(1111);
			machine.setCity("Tesztváros");
			machine.setAddress("Teszt u. 11");
			machines.add(machine);
		}
		return machines;
	}

	private int selectRandomMachineType() throws SQLException {
		List<Integer> machineTypes = getMachineTypes();
		int randomNum = (int) Math.round(Math.random() * (machineTypes.size() - 1));
		return machineTypes.get(randomNum);
	}

	private List<Integer> getMachineTypes() throws SQLException {
		List<Integer> machineTypes = new ArrayList<>();
		try (Connection con = DatabaseConnect.getConnection();
				PreparedStatement pstmt = con.prepareStatement(MACHINE_TYPES_ST)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				machineTypes.add(rs.getInt(1));
			}
		}
		return machineTypes;
	}

}
