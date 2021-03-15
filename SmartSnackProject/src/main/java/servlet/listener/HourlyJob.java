package servlet.listener;

import java.util.List;

import dao.AutomatDAO;
import dao.impl.AutomatDAOimpl;
import entity.Automat;

public class HourlyJob implements Runnable {

	private static List<Automat> soldOutProductMachines;
	private static List<Automat> soldOutCoinMachines;
	private static List<Automat> offlineMachines;

	@Override
	public void run() {
		AutomatDAO automatDao = new AutomatDAOimpl();
		soldOutProductMachines = automatDao.getSoldOutProductMachines();
		soldOutCoinMachines = automatDao.getSoldOutCoinMachines();
		offlineMachines = automatDao.getOfflineMachines();
	}

	public List<Automat> getSoldOutProductMachines() {
		return soldOutProductMachines;
	}

	public List<Automat> getSoldOutCoinMachines() {
		return soldOutCoinMachines;
	}

	public List<Automat> getOfflineMachines() {
		return offlineMachines;
	}

}
