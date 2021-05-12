package util;

import java.util.List;

import dao.AutomatDAO;
import dao.impl.AutomatDAOimpl;
import entity.Machine;

public class HourlyJob implements Runnable {

	private static List<Machine> soldOutProductMachines;
	private static List<Machine> soldOutCoinMachines;
	private static List<Machine> offlineMachines;

	@Override
	public void run() {
		AutomatDAO automatDao = new AutomatDAOimpl();
		soldOutProductMachines = automatDao.getSoldOutProductMachines();
		soldOutCoinMachines = automatDao.getSoldOutCoinMachines();
		offlineMachines = automatDao.getOfflineMachines();
		if (!offlineMachines.isEmpty()) {
//			new EmailSender().sendEmail(offlineMachines);
		}
	}

	public List<Machine> getSoldOutProductMachines() {
		return soldOutProductMachines;
	}

	public List<Machine> getSoldOutCoinMachines() {
		return soldOutCoinMachines;
	}

	public List<Machine> getOfflineMachines() {
		return offlineMachines;
	}

}
