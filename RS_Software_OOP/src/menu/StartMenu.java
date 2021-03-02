package menu;

import java.util.Scanner;

import entryRoomData.RoomDataEntry;
import print.EmployeePrint;
import print.MaterialPrint;
import session.Forming;
import tables.Concrete;
import tables.Customers;
import tables.Employees;
import tables.FloorMats;
import tables.Parquet;
import tables.Wallpaper;
import tables.WallpaperGlue;

public class StartMenu {
	public Scanner scan = new Scanner(System.in);
	private String[] chosenCustomer;

	public void menu() {
		MaterialPrint materialPrinter = new MaterialPrint();
		EmployeePrint employeePrinter = new EmployeePrint();
		WallpaperGlue wallpaperGlue = new WallpaperGlue(scan);
		Forming forming = new Forming(scan);
		Employees employees = new Employees(scan);
		FloorMats floorMats = new FloorMats(scan);
		Concrete concrete = new Concrete(scan);
		Parquet parquet = new Parquet(scan);
		Wallpaper wallpaper = new Wallpaper(scan);
		MenuDisplays menudisplays = new MenuDisplays(scan);
		Customers customers = new Customers(scan);
		RoomDataEntry roomDataEntry = new RoomDataEntry(scan);

		outerloop: while (true) {
			int choice = 0;
			forming.options();
			String option = scan.next().toUpperCase();
			while (!(option.equals("A") || option.equals("B") || option.equals("C") || option.equals("D")
					|| option.equals("E"))) {
				System.out.print("Kérem, az opciók közül válasszon! A/B/C/D/E: ");
				option = scan.next().toUpperCase();
			}
			String yesOrNo = "";
			switch (option) {
			case "A":
				/*
				 * TODO
				 */
				//
				continue;
			case "B":
				menudisplays.bMenuDisplay(forming);
				choice = MenuDisplays.treePointsMenuChecker();
				if (choice == -1) {
					bye();
					break outerloop;
				}
				switch (choice) {
				case 1:
//					if (admin) {
//						System.out.println("Hozzáadás");
					employees.add();
//					} else {
//						System.out.println("Nincs jogosultsága a felhasználók szerkesztéséhez!");
//					}
					yesOrNo = quitOrNo();
					if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
						bye();
						break outerloop;
					}
					continue;
				case 2:
					employeePrinter.print(employees.getEmployees());
					yesOrNo = quitOrNo();
					if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
						bye();
						break outerloop;
					}
					continue;
				case 3:/*
						 * TODO
						 */
//				if (admin) {
//					System.out.println("Törlés");
					employees.delete();
//					} else {
//							System.out.println("Nincs jogosultsága a felhasználók szerkesztéséhez!");
//					}
					continue;
				}
			case "C":
				int choice2 = menudisplays.cMenuDisplay(forming);
				while (!(choice2 == -1 || (choice2 <= 5 && choice2 >= 1))) {
					System.out.print("Kérem, a sorszámok közül válasszon! ");
					choice2 = scan.nextInt();
				}
				if (choice2 == -1) {
					bye();
					break outerloop;
				}
				switch (choice2) {
				case 1:
					int choice3 = input();
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					choice3 = menuPointCheker(choice3);
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					switch (choice3) {
					case 1:
						materialPrinter.print(wallpaper.getWallpaper());
						yesOrNo = quit();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 2:
						wallpaper.delete();
						yesOrNo = quit();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 3:
						wallpaper.add();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					}
					continue;
				case 2:
					choice3 = input();
					choice3 = menuPointCheker(choice3);
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					switch (choice3) {
					case 1:
						materialPrinter.print(concrete.getConcrete());
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 2:
						concrete.delete();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 3:
						concrete.add();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					}
					continue;
				case 3:
					choice3 = input();
					choice3 = menuPointCheker(choice3);
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					switch (choice3) {
					case 1:
						materialPrinter.print(parquet.getParquet());
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 2:
						parquet.delete();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 3:
						parquet.add();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					}
					continue;
				case 4:
					choice3 = input();
					choice3 = menuPointCheker(choice3);
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					switch (choice3) {
					case 1:
						materialPrinter.print(floorMats.getFloorMats());
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 2:
						floorMats.delete();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 3:
						floorMats.add();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					}
					continue;
				case 5:
					choice3 = input();
					choice3 = menuPointCheker(choice3);
					if (choice3 == -1) {
						bye();
						break outerloop;
					}
					switch (choice3) {
					case 1:
						materialPrinter.print(wallpaperGlue.getWallpaperGlue());
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 2:
						wallpaperGlue.delete();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					case 3:
						wallpaperGlue.add();
						yesOrNo = quitOrNo();
						if (yesOrNo.equals("n") || yesOrNo.equals("-1")) {
							bye();
							break outerloop;
						}
						continue;
					}
					continue;
				}
			case "D":
				bid();
				do {
				chosenCustomer = customers.getCustomer();
				}while(roomDataEntry.goodOrNoDataEntry().equals("u"));
				roomDataEntry.addRoomData(chosenCustomer);
				continue;
			case "E":
				bye();
				break outerloop;
			}
			scan.close();
		}

	}

	private String quitOrNo() {
		String yesOrNo = quit();
		while (!yesOrNo.equals("i") && !yesOrNo.equals("n") && !yesOrNo.equals("-1")) {
			System.out.print("Kérem, 'i'-vel vagy 'n'-nel válaszoljon  (i = igen / n = nem)");
			yesOrNo = scan.next();
		}
		return yesOrNo;
	}

	private void bye() {
		System.out.println();
		System.out.println("Viszontlátásra!");
	}

	String quit() {
		System.out.println("Szeretne visszatérni a menübe? i / n ");
		System.out.print("Válasz: ");
		String yesOrNo = scan.next();
		return yesOrNo;
	}

	private int menuPointCheker(int choice) {
		while (!(choice == -1 || (choice <= 3 && choice >= 1))) {
			System.out.print("Kérem, a sorszámok közül válasszon! ");
			choice = scan.nextInt();
		}
		return choice;
	}

	private int input() {
		System.out.println("1.Lekérdezés");
		System.out.println("2.Törlés");
		System.out.println("3.Hozzádadás");
		System.out.println();
		System.out.print("Válasszon menüpontot : ");
		int choice3 = scan.nextInt();
		System.out.println();
		return choice3;
	}

	private void bid() {
		System.out.println();
		System.out.println("ÁRAJÁNLAT");
		System.out.println();
	}

}
