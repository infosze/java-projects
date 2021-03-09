package servlet.listener;

import java.util.List;

import dao.AutomatErrorDAO;
import entity.Automat;

public class FindErrorHourly implements Runnable {

	@Override
	public void run() {
		List<Automat> wrongMachines = new AutomatErrorDAO().findSoldOutCoinMachines();
		for (Automat e : wrongMachines) {
			System.out.println(e.toString() + " ----> Min. 2 fajta érme kifogyott...");  // TODO test. fix it later.
		}
		System.out.println(); // TODO test. fix it later.
	}

}
