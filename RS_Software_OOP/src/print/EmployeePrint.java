package print;

public class EmployeePrint {
	
	public void print(String[] employees) {
		System.out.println("Index|  Azonsító|                 Név|       Beosztás|");
		System.out.println("─────┼──────────┼────────────────────┼───────────────┼");
		for (int i = 0; i < employees.length; i++) {
			String[] temp = employees[i].split(";");

			System.out.printf("%5d|%10s|%20s|%15s|%n", i, temp[0], temp[1], temp[2]);
			System.out.println("─────┼──────────┼────────────────────┼───────────────┼");
		}
	}
}
