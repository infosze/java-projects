package hu.ak_akademia.oop.hangman_game;

import java.text.Collator;
import java.util.Arrays;
import java.util.Scanner;

class Session {

	private static final int MAX_MISMATCHES = 12;
	private static final Collator COLLATOR = Collator.getInstance();
	private static final String LEGAL_CHARS;

	static {
		var l = new StringBuilder();
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			l.append(ch);
		}
		l.append("ÁÉÍÓÖŐÚÜŰ");
		l.append("*");
		LEGAL_CHARS = l.toString();
	}

	private final String word;
	private final String[] wordFound;
	private final String[] mismatches = new String[MAX_MISMATCHES];
	private int numMismatches;
	private boolean gameToQuit = false;
	private final Scanner scanner;

	Session(Scanner scanner, String word) {
		this.scanner = scanner;
		this.word = word;

		wordFound = new String[word.length()];
		for (int i = 0; i < wordFound.length; i++) {
			wordFound[i] = "_";
			System.out.printf("%s ", wordFound[i]);
		}
		Arrays.fill(mismatches, "");
		numMismatches = 0;
	}

	void run() {
		System.out.println();
		do {
			String inputLetter = input();
			if (inputLetter.contentEquals("*")) {
				gameToQuit = true;
				return;
			}
			checkLetter(inputLetter);
			printResults();
		} while (makeEnd());
	}

	private String input() {
		String inputLetter;
		while (true) {
			System.out.print("Kérek egy betűt: ");
			inputLetter = scanner.nextLine().toUpperCase().trim();
			if (inputLetter.isEmpty() || inputLetter.length() > 1) {
				continue;
			} else if (LEGAL_CHARS.contains(inputLetter)) {
				return inputLetter;
			}
		}
	}

	private void checkLetter(String inputLetter) {
		if (word.contains(inputLetter)) {
			int index = word.indexOf(inputLetter);
			while (index >= 0) {
				wordFound[index] = inputLetter;
				index = word.indexOf(inputLetter, index + 1);
			}
		} else if (0 > Arrays.binarySearch(mismatches, 0, numMismatches + 1, inputLetter, COLLATOR)) {
			mismatches[numMismatches] = inputLetter;
			Arrays.sort(mismatches, 0, numMismatches + 1, COLLATOR);
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
		String goodWord;
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

	boolean isGameToQuit() {
		return gameToQuit;
	}

}
