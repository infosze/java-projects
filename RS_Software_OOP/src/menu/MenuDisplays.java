package menu;

import java.util.Scanner;

import session.Forming;

public class MenuDisplays {
	
	static Scanner scan;

	public MenuDisplays(Scanner scan) {
		MenuDisplays.scan = scan;
	}

	public int cMenuDisplay(Forming forming) {

		System.out.println(
				"Választható anyagnevek az alábbiak : \n\r 1.Tapéta \n\r 2.Beton \n\r 3.Parketta\n\r 4.Padlószőnyeg \n\r 5.Tapétargasztó");
		System.out.println();
		System.out.print("Válasszon menüpontot: ");
		int choice2 = scan.nextInt();
		System.out.println("");
		return choice2;
	}

	public void bMenuDisplay(Forming forming) {
		formatIn2("1. Hozzáadás");
		formatIn2("2. Lekérdezés");
		formatIn2("3. Törlés");
	}
	
	public static int treePointsMenuChecker() {
		System.out.print("Sorszám: ");
		int choice = scan.nextInt();
		while (!(choice == -1 || (choice <= 3 && choice >= 1))) {
			System.out.print("Kérem, a sorszámok közül válasszon! ");
			choice = scan.nextInt();
			System.out.println("");
		}
		return choice;
	}

	public void options() {
		System.out.println("Menüpontok az alábbiak: ");
		System.out.println("");
		formatIn1("A, Útmutató");
		formatIn1("B, Dolgozók hozzáadása / törlése");
		formatIn1("C, Anyagnevek / lekérdezés / törlése");
		formatIn1("D, Árajánlat");
		formatIn1("E, Kilépés");
		System.out.print("Kérem, adja meg a kiválasztott menüpontot: ");
	}

	public void saving() {
		System.out.println("Szeretné elmenteni? (i / n)");
		System.out.print("Válasz: ");
		String save = scan.next().toUpperCase();
		if (save.equals("I")) {
			System.out.print("Kérem, adja meg a fájl nevét: ");
//			String fileName = scan.next();
			// writeTenderToFile(fileName + ".txt", sb.toString());
		}
	}

	public void formatIn1(String text) {
		System.out.println(text);
		for (int i = 0; i < text.length() + 1; i++) {
			System.out.print(".");
		}
		System.out.println("");

	}

	public void formatIn2(String text) {
		System.out.println("");
		text = "× " + text;
		System.out.println(text);
		System.out.print("*");
		for (int i = 0; i < text.length() - 1; i++) {
			System.out.print("*");
		}
		System.out.println("");

	}
}
