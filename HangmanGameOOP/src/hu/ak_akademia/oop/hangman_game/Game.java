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
		for (boolean first = true; startOrNot(first); first = false) {
			var session = new Session(scanner);
			if (session.run()) {
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
