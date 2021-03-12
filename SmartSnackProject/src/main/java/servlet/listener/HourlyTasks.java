package servlet.listener;

import java.util.List;
import dao.AutomatDAO;
import entity.Automat;

public class HourlyTasks implements Runnable {

	private static final String SOLD_OUT_PRODUCT = "SELECT * FROM ssp.machine;"; // TODO fix it SQL query
	private static final String SOLD_OUT_COIN = "SELECT * FROM ssp.machine;"; // TODO fix it SQL query
	private static final String OFFLINE = "SELECT * FROM ssp.machine;"; // TODO fix it SQL query
	
	private static List<Automat> soldOutProductMachines;
	private static List<Automat> soldOutCoinMachines;
	private static List<Automat> offlineMachines;

	@Override
	public void run() {
		soldOutProductMachines = new AutomatDAO().findMachines(SOLD_OUT_PRODUCT);
		soldOutCoinMachines = new AutomatDAO().findMachines(SOLD_OUT_COIN);
		offlineMachines = new AutomatDAO().findMachines(OFFLINE);
	}

	public static List<Automat> getSoldOutProductMachines() {
		return soldOutProductMachines;
	}

	public static List<Automat> getSoldOutCoinMachines() {
		return soldOutCoinMachines;
	}

	public static List<Automat> getOfflineMachines() {
		return offlineMachines;
	}

}
