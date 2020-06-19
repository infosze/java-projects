package hu.ak_akademia.oop.hangman_game;

import java.text.Collator;
import java.util.Arrays;
import java.util.Scanner;

class Session {

	private static final int MAX_MISMATCHES = 12;
	private static final String LEGAL_CHARS;
	private final Scanner scanner;
	private String[] mismatches = new String[MAX_MISMATCHES];
	private String[] wordFound;
	private Collator collator = Collator.getInstance();
	private String word;
	private String inputLetter;
	private int numMismatches;
	private String goodWord;
	static {
		String l = "";
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			l += ch;
		}
		l += "ÁÉÍÓÖŐÚÜŰ";
		LEGAL_CHARS = l;
	}

	Session(Scanner scanner, String word) {
		this.scanner = scanner;
		this.word = word;
	}

	boolean run() {
		makeStart();
		do {
			if (input()) {
				return true;
			}
			checkLetter();
			printResults();
		} while (makeEnd());
		return false;
	}

	private void makeStart() {
		numMismatches = 0;
		wordFound = new String[word.length()];
		for (int i = 0; i < wordFound.length; i++) {
			wordFound[i] = "_";
			System.out.printf("%s ", wordFound[i]);
		}
		Arrays.fill(mismatches, "");
		System.out.println();
	}

	private boolean input() {
		boolean wrongLetter;
		do {
			wrongLetter = true;
			System.out.print("Kérek egy betűt: ");
			inputLetter = scanner.nextLine().toUpperCase().trim();
			if (inputLetter.isEmpty() || inputLetter.length() > 1) {
				continue;
			} else if (LEGAL_CHARS.contains(inputLetter)) {
				wrongLetter = false;
			} else if (inputLetter.equals("*")) {
				return true;
			}
		} while (wrongLetter);
		return false;
	}

	private void checkLetter() {
		if (word.contains(inputLetter)) {
			int index = word.indexOf(inputLetter);
			while (index >= 0) {
				wordFound[index] = inputLetter;
				index = word.indexOf(inputLetter, index + 1);
			}
		} else if (0 > Arrays.binarySearch(mismatches, 0, numMismatches + 1, inputLetter, collator)) {
			mismatches[numMismatches] = inputLetter;
			Arrays.sort(mismatches, 0, numMismatches + 1, collator);
			numMismatches++;
		}
	}

	private void printResults() {
		System.out.println();
		for (int i = 0; i < wordFound.length; i++) {
			System.out.printf("%s ", wordFound[i]);
		}
		System.out.print("      Rossz tippek: ");
		System.out.printf("%s", mismatches[0]);
		for (int i = 1; i < numMismatches; i++) {
			System.out.printf(", %s", mismatches[i]);
		}
		System.out.println();
	}

	private boolean makeEnd() {
		if (numMismatches == MAX_MISMATCHES) {
			System.out.println("Ez most nem sikerült.");
			return false;
		}
		goodWord = "";
		for (int i = 0; i < wordFound.length; i++) {
			goodWord += wordFound[i];
		}
		if (goodWord.equals(word)) {
			System.out.println("Kitaláltad, gratulálok!");
			return false;
		}
		return true;
	}

}
