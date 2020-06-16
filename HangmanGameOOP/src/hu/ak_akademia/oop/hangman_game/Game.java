package hu.ak_akademia.oop.hangman_game;

import java.util.Scanner;

public class Game {

	private final Scanner scanner;

	public Game() {
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		var game = new Game();
		game.playGame();
	}

	private void playGame() {
		var msgs = new Messages();
		msgs.welcome();
		msgs.instruct();
		out: for (boolean first = true; startOrNot(first); first = false) {
			var session = new Session(scanner);
			session.startNewGame();
			do {
				if (session.input()) {
					break out;
				}
				session.checkLetter();
				session.printResults();
			} while (session.makeEnd());
		}
		msgs.sayGoodbye();
	}

	private boolean startOrNot(boolean first) {
		String inputLetter;
		boolean continueGame = true;
		System.out.printf("%nIndulhat %s menet? ", first ? "az első" : "a következő");
		inputLetter = scanner.nextLine().toUpperCase().trim();
		if (inputLetter.startsWith("N")) {
			continueGame = false;
		}
		return continueGame;
	}
}
