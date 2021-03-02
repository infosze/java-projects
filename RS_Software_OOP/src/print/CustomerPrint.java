package print;

public class CustomerPrint {

	public void print(String[] customers) {
		System.out.println(
				"Index|                    Név│                                Cím│                  Tel│                    Email|");
		System.out.println(
				"—————┼———————————————————————┼———————————————————————————————————┼—————————————————————┼—————————————————————————┼");
		for (int i = 0; i < customers.length; i++) {
			String[] temp = customers[i].split(";");
			System.out.printf("%5d|%23s|%35s|%21s|%25s|%n", i, temp[0], temp[1], temp[2], temp[3]);
			System.out.println(
					"—————┼———————————————————————┼———————————————————————————————————┼—————————————————————┼—————————————————————————┼");
		}
	}

}
