package hu.ak_akademia.oop.hangman_game.teacher;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * <b>Exercise: Hangman Game OOP (Szókitalálós játék OOP)</b>
 * <p>
 * Manage one session (to have the user identify one word).
 *
 * @author A&K Akadémia (Lajos Czuczor)
 */
class Session {

	private static final int MAX_MISMATCHES = Constants.getMaxMismatches();
	private static final Collator COLLATOR = Collator.getInstance(new Locale("hu", "HU"));
	private static final String LETTERS;

	static {
		var letters = new StringBuilder();
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			letters.append(ch);
		}
		letters.append("ÁÉÍÓÖŐÚÜŰ");
		letters.append('*');
		LETTERS = letters.toString();
	}

	private final Scanner scanner;
	private final char[] target; // the word the user is expected to find out (letter by letter)
	private final boolean[] matches; // letters hit, uses the same index as target
	private final String[] mismatches = new String[MAX_MISMATCHES]; // mismatched characters in alphabetic order
	private int numMismatches = 0; // number of mismatches, number of actual letters in mismatches[]
	private boolean gameToQuit = false;

	Session(Scanner scanner, String target) {
		this.scanner = scanner;
		this.target = target.toCharArray();
		matches = new boolean[target.length()];
		Arrays.fill(mismatches, "\uFFFF");
	}

	void play() {
		print();
		do {
			char letter = input();
			if (letter == '*') {
				gameToQuit = true;
				return;
			}
			process(letter);
			print();
		} while (!isSuceeded() && !isFailed());
		System.out.println(isFailed() ? "Ez most nem sikerült." : "Kitaláltad, gratulálok!");
	}

	boolean isGameToQuit() {
		return gameToQuit;
	}

	private char input() {
		String input;
		do {
			System.out.print("Kérek egy betűt: ");
			input = scanner.nextLine().strip().trim().toUpperCase();
		} while (input.isEmpty() || input.length() > 1 || !LETTERS.contains(input));
		return input.charAt(0);
	}

	// process the character the user has specified
	private void process(char letter) {
		if (Arrays.binarySearch(mismatches, "" + letter) < 0) {
			boolean match = updateMatches(letter);
			if (!match) {
				updateMismatches(letter);
			}
		}
	}

	// update matches[]: which letters has already been identified in the word
	private boolean updateMatches(char letter) {
		boolean match = false;
		for (int i = 0; i < target.length; i++) {
			if (target[i] == letter) {
				matches[i] = match = true;
			}
		}
		return match;
	}

	// update mismatches[] and numMismatches: the set of specified letters that didn't match
	private void updateMismatches(char letter) {
		int pos = Arrays.binarySearch(mismatches, "" + letter, COLLATOR);
		pos = -pos - 1;
		System.arraycopy(mismatches, pos, mismatches, pos + 1, numMismatches - pos);
		mismatches[pos] = "" + letter;
		numMismatches++;
	}

	// print the word (with identified and still unknown letters) and mismatched letters
	private void print() {
		System.out.println();
		printTarget();
		printMismatches();
	}

	private void printTarget() {
		for (int i = 0; i < target.length; i++) {
			System.out.print(matches[i] ? target[i] : "_");
			System.out.print(" ");
		}
	}

	private void printMismatches() {
		if (numMismatches > 0) {
			System.out.print("     Rossz tippek: ");
			System.out.print(mismatches[0]);
			for (int i = 1; i < numMismatches; i++) {
				System.out.printf(", %s", mismatches[i]);
			}
		}
		System.out.println();
	}

	// user has managed to identify the target word
	private boolean isSuceeded() {
		for (int i = 0; i < matches.length; i++) {
			if (!matches[i]) {
				return false;
			}
		}
		return true;
	}

	// user failed to to identify the target word
	private boolean isFailed() {
		return numMismatches == MAX_MISMATCHES;
	}

}
