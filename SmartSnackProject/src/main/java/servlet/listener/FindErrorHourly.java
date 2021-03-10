package servlet.listener;

import java.util.List;
import dao.AutomatErrorDAO;
import entity.Automat;

public class FindErrorHourly implements Runnable {

	private static List<Automat> wrongMachines;
	
	@Override
	public void run() {
		wrongMachines = new AutomatErrorDAO().findSoldOutProductMachines();
		for (Automat e : wrongMachines) {
			System.out.println(e.toString() + " ----> Min. 3 fajta t kifogyott...");  // TODO test. fix it later.
		}
		System.out.println(); // TODO test. fix it later.
	}

	
	public static List<Automat> getWrongMachines() {
		return wrongMachines;
	}
	

}
