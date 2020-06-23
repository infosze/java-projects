package hu.ak_akademia.oop.hangman_game;

import java.io.IOException;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		try {
			new Game().play();
		} catch (IOException e) {
			System.out.println("A file nem található!");
		}
	}

	private final Scanner scanner;
	private WordStore wordStore;

	public Game() throws IOException {
		wordStore = new WordStore();
		scanner = new Scanner(System.in);
	}

	private void play() throws IOException {
		var msgs = new Messages();
		msgs.welcome();
		msgs.instruct();
		for (boolean first = true; startOrNot(first); first = false) {
			var session = new Session(scanner, wordStore.selectWord());
			session.run();
			if (session.isGameToQuit()) {
				break;
			}
		}
		msgs.sayGoodbye();
	}

	private boolean startOrNot(boolean first) {
		String inputLetter;
		System.out.printf("%nIndulhat %s menet? ", first ? "az első" : "a következő");
		inputLetter = scanner.nextLine().toUpperCase().trim();
		return !inputLetter.startsWith("N");
	}

}
