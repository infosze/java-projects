package tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import entryRoomData.RoomDataEntry;
import fileOperation.Coding;
import fileOperation.FileOperation;
import print.CustomerPrint;

public class Customers {

	private Scanner scan;

	public Customers(Scanner scan) {
		this.scan = scan;
	}

	RoomDataEntry rde = new RoomDataEntry(scan);
	Coding coding = new Coding();
	FileOperation fileOperation = new FileOperation();
	CustomerPrint customerPrint = new CustomerPrint();
	public String[] customers = fileOperation.readMaterial("customers");

	/**
	 * In this method, you can select customer data or enter a new customer data.
	 * 
	 * @return A string array with the data of the selected customer. Index 0: name,
	 *         Index 1: address, Index 2: phone number, Index 3: e-mail address.
	 */
	public String[] getCustomer() {
		scan.nextLine();
		System.out.println("Válaszd ki a megrendelőt.");
		customerPrint.print(customers);
		String inputLetter;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.print("BENNE VAN A LISTÁBAN A MEGRENDELŐ? (\"i\"-igen / \"n\"-nem) ");
			inputLetter = scan.nextLine()
					.trim();
			rde.checkExit(inputLetter);
			if (!inputLetter.equals("i") && !inputLetter.equals("n")) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				notGoodData = true;
			}
		} while (notGoodData);
		if (inputLetter.equals("i")) {
			String[] customer = chooseCustomer(customers);
			return customer;
		}
		String[] customer = addNewCustomerData();
		return customer;
	}

	public String[] chooseCustomer(String[] datas) {
		int customerIndex = 0;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.print("ADJA MEG A KIVÁLASZTOTT MEGRENDELŐ INDEXÉT: ");
			try {
				customerIndex = scan.nextInt();
				scan.nextLine();
				rde.checkExit(customerIndex);
			} catch (InputMismatchException e) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				scan.next();
				notGoodData = true;
			}
			if (customerIndex < 0 || customerIndex > datas.length - 1 && notGoodData == false) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				notGoodData = true;
			}
		} while (notGoodData);
		String[] customerDatas = datas[customerIndex].split(";");
		System.out.printf("%s, %s , %s, %s%n", customerDatas[0], customerDatas[1], customerDatas[2], customerDatas[3]);
		System.out.println();
		return customerDatas;

	}

	/**
	 * This method can be used to enter data for new customers. You can enter four
	 * details: Name, Address, Phone number, and e-mail address.
	 * 
	 * @return A string array in which the data is separated by a semicolon without
	 *         space.
	 */
	public String[] addNewCustomerData() {

		String customerData = new String();
		char inputLetter;
		String input;

		System.out.println();
		System.out.println("Új megrendelő felvitele.");
		scan.nextLine();
		do {
			System.out.print("NÉV: ");
			input = scan.nextLine();
			rde.checkExit(input);
			customerData = input + ";";

			System.out.print("CÍM: ");
			input = scan.nextLine();
			rde.checkExit(input);
			customerData += input + ";";

			System.out.print("TELEFONSZÁM: ");
			input = scan.nextLine();
			rde.checkExit(input);
			customerData += input + ";";

			System.out.print("e-mail CÍM: ");
			input = scan.nextLine();
			rde.checkExit(input);
			customerData += input;

			do {
				System.out.printf("%nADATBEVITEL JÓ? (\"i\"-igen / \"u\"-újra) ");
				inputLetter = scan.next()
						.charAt(0);
				rde.checkExit(inputLetter);
				System.out.println();
			} while (inputLetter != 'i' && inputLetter != 'n');
		} while (inputLetter == 'n');
		if (inputLetter == 'i') {
			fileOperation.fileAppend(customerData, "customers");
		}
		String[] customer = customerData.split(";");
		System.out.println("Az adatbevitel kész.");
		System.out.printf("%s, %s , %s, %s%n", customer[0], customer[1], customer[2], customer[3]);
		System.out.println();
		readCustomers();
		return customer;
	}

	public void readCustomers() {
		int lines = fileOperation.getLines("customers");
		try {
			customers = new String[lines];
			BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));
			for (int i = 0; i < customers.length; i++) {
				customers[i] = reader.readLine(); // Read lines
				customers[i] = coding.decoder(customers[i], 5);
			}
			reader.close();

		} catch (Exception e) {

		}

	}

}
