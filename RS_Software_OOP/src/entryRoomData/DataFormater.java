package entryRoomData;

import java.util.Scanner;

import fileOperation.FileOperation;

class DataFormater {

	StringBuilder sb = new StringBuilder(); // Text format to print.
	Room[] rooms;
	Scanner scan = new Scanner(System.in);
	TotalConcreteCalculator concreteCalculator;
	TotalFloorCalculator floorCalculator;
	TotalWallCalculator wallCalculator;
	TotalGlueCalculator glueCalculator;
	CalculationManager calculationManager;
//	Customers customers;
//	String[] customer;
//	Employees employees;
//	String[] employee;
	double totalPrice;
	double discount;
	double discountedPrice;
	private String[] chosenCustomer;

	DataFormater(Room[] rooms, Scanner scan, double totalPrice, double discount, double discountedPrice, String[] chosenCustomer) {
		this.rooms = rooms;
		this.scan = scan;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.discountedPrice = discountedPrice;
		this.chosenCustomer = chosenCustomer;
		concreteCalculator = new TotalConcreteCalculator(rooms);
		floorCalculator = new TotalFloorCalculator(rooms);
		wallCalculator = new TotalWallCalculator(rooms);
		glueCalculator = new TotalGlueCalculator(rooms);
		calculationManager = new CalculationManager(rooms);
//		customer = customers.getCustomer();
//		employee = employees.getEmployees();
	}

	void printOffer() {
		sb.delete(0, sb.length());
		System.out.printf("%n%n");
		sb.append(String.format("%n%n"));
		System.out.printf("%30s%n%n", "Árajánlat");
		sb.append(String.format("%30s%n%n", "Árajánlat"));
		System.out.printf("A megrendelő adatai:%n%nNév:         %s%nCím:         %s%nTelefonszám: %s%nE-mail:      %s%n%n", chosenCustomer[0],
				chosenCustomer[1], chosenCustomer[2], chosenCustomer[3]);
		sb.append(String.format("A megrendelő adatai:%n%nNév:         %s%nCím:         %s%nTelefonszám: %s%nE-mail:      %s%n%n", chosenCustomer[0],
				chosenCustomer[1], chosenCustomer[2], chosenCustomer[3]));

		for (int i = 1; i < rooms.length; i++) {
			System.out.printf("%n%d. helyiség:%n%n", i);
			sb.append(String.format("%n%d. helyiség:%n%n", i));
			try {
				rooms[i].getFloorConcreteName().isEmpty();
				if (!rooms[i].getFloorConcreteName().isEmpty()) {
					System.out.printf("%-30s%5.0f csomag%8.0f Ft%n", "Aljzatkiegyenlítő:", rooms[i].getConcretePackRounded(),
							rooms[i].getConcretePriceRounded());
					sb.append(String.format("%-30s%,5.0f csomag%8.0f Ft%n", "Aljzatkiegyenlítő:", rooms[i].getConcretePackRounded(),
							rooms[i].getConcretePriceRounded()));
				}
			} catch (NullPointerException ex) {
			}
			try {
				rooms[i].getFloorMaterialName().isEmpty();
				if (!rooms[i].getFloorMaterialName().isEmpty()) {
					System.out.printf("%-30s%5.0f csomag%8.0f Ft%n", "Padlóburkoló:", rooms[i].getFloorPackRounded(),
							rooms[i].getFloorPriceRounded());
					sb.append(String.format("%-30s%5.0f csomag%8.0f Ft%n", "Padlóburkoló:", rooms[i].getFloorPackRounded(),
							rooms[i].getFloorPriceRounded()));
				}
			} catch (NullPointerException ex) {
			}
			try {
				rooms[i].getWallMaterialName().isEmpty();
				if (!rooms[i].getWallMaterialName().isEmpty()) {
					System.out.printf("%-30s%5.0f csomag%8.0f Ft%n", "Tapéta:", rooms[i].getWallPackRounded(), rooms[i].getWallPriceRounded());
					sb.append(String.format("%-30s%5.0f csomag%8.0f Ft%n", "Tapéta:", rooms[i].getWallPackRounded(), rooms[i].getWallPriceRounded()));
				}
			} catch (NullPointerException ex) {
			}
			try {
				rooms[i].getWallGlueName().isEmpty();
				if (!rooms[i].getWallGlueName().isEmpty())
					System.out.printf("%-30s%5.0f csomag%8.0f Ft%n", "Segédanyag:", rooms[i].getGluePackRounded(), rooms[i].getGluePriceRounded());
				sb.append(String.format("%-30s%5.0f csomag%8.0f Ft%n", "Segédanyag:", rooms[i].getGluePackRounded(), rooms[i].getGluePriceRounded()));
			} catch (NullPointerException ex) {
			}

		}

		System.out.printf("%n");
		sb.append(String.format("%n"));
		System.out.printf("%-42s%8.0f Ft%n", "Végösszeg:", totalPrice);
		sb.append(String.format("%-42s%8.0f Ft%n", "Végösszeg:", totalPrice));
		System.out.printf("%-42s%8.0f %%%n", "Kedvezmény mértéke (%): ", discount);
		sb.append(String.format("%-42s%8.0f %%%n", "Kedvezmény mértéke (%): ", discount));
		System.out.printf("%-42s%8.0f Ft%n", "Kedvezménnyel csökkentett végösszeg: ", discountedPrice);
		sb.append(String.format("%-42s%8.0f Ft%n", "Kedvezménnyel csökkentett végösszeg: ", discountedPrice));
//		System.out.printf("A felhasznált anyagok összesítése%n%n");
//		sb.append(String.format("A felhasznált anyagok összesítése%n%n"));
//		System.out.printf("A kiválasztott aljzatkiegyenlítő(k)%n");
//		sb.append(String.format("A kiválasztott aljzatkiegyenlítő(k)%n"));
//		System.out.printf(concreteCalculator.printTotalMaterialsAndPrices());
//		sb.append(String.format(concreteCalculator.printTotalMaterialsAndPrices()));
//		System.out.printf("%n");
//		sb.append(String.format("%n"));
//		System.out.printf("A kiválasztott paldóburkolat(ok)%n");
//		sb.append(String.format("A kiválasztott paldóburkolat(ok)%n"));
//		System.out.printf(floorCalculator.printTotalMaterialsAndPrices());
//		sb.append(String.format(floorCalculator.printTotalMaterialsAndPrices()));
//		System.out.printf("%n");
//		sb.append(String.format("%n"));
//		System.out.printf("A kiválasztott falburkolat(ok)%n");
//		sb.append(String.format("A kiválasztott falburkolat(ok)%n"));
//		System.out.printf(wallCalculator.printTotalMaterialsAndPrices());
//		sb.append(String.format(wallCalculator.printTotalMaterialsAndPrices()));
//		System.out.printf("%n");
//		sb.append(String.format("%n"));
//		System.out.printf("A kiválasztott segédanyag(ok)%n");
//		sb.append(String.format("A kiválasztott segédanyag(ok)%n"));
//		System.out.printf(glueCalculator.printTotalMaterialsAndPrices());
//		sb.append(String.format(glueCalculator.printTotalMaterialsAndPrices()));
//		System.out.printf("%n");
//		sb.append(String.format("%n"));
//		System.out.printf("Végösszeg: %,.0f forint%n", totalPrice);
//		sb.append(String.format("Végösszeg: %,.0f forint%n", totalPrice));
//		System.out.printf("Kedvezmény mértéke: %,.0f%%%n", discount);
//		sb.append(String.format("Kedvezmény mértéke: %,.0f%%%n", discount));
//		System.out.printf("Kedvezménnyel csökkentett végösszeg: %,.0f forint%n", discountedPrice);
//		sb.append(String.format("Kedvezménnyel csökkentett végösszeg: %,.0f forint%n", discountedPrice));
//		System.out.printf("%n");
//		sb.append(String.format("%n"));
		System.out.println();
		saving();
	}

	void saving() {
		FileOperation fileOperation = new FileOperation();

		System.out.println("Szeretné elmenteni? (I / N)");
		System.out.print("Válasz: ");
		String save = scan.next().toUpperCase();
		if (save.equals("I")) {
			System.out.print("Kérem, adja meg a fájl nevét: ");
			String fileName = scan.next();
			fileOperation.writeTextToFile(sb.toString(), fileName + ".txt");
		} else if (save.equals("N")) {
			System.exit(0);
		} else {
			System.out.println("");
			System.err.println("Nem volt értelmezhető a válasz! Kérem, próbáljon a felsorolt lehetőségek közül választani!\n");
			saving();
		}
	}

}
