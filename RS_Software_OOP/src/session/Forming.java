package session;

import java.util.Scanner;

public class Forming {

	Scanner scan;

	public Forming(Scanner scan) {
		this.scan = scan;
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
