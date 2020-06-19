package hu.ak_akademia.oop.hangman_game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private static final String WORDS_FILE_NAME = "Words.txt";
	private String word;
	String[] words;
	private final Scanner scanner;
	Random random = new Random();
	private WordStore wordStore = new WordStore();

	public Game() {
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		var game = new Game();
		try {
			game.playGame();
		} catch (IOException e) {
			System.out.println("A file nem található!");
		}
	}

	private void playGame() throws IOException {
		words = wordStore.wordsFromTxt(WORDS_FILE_NAME);
		var msgs = new Messages();
		msgs.welcome();
		msgs.instruct();
		for (boolean first = true; startOrNot(first); first = false) {
			var session = new Session(scanner, selectWord());
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

	private String selectWord() {
		word = words[random.nextInt(words.length)].toUpperCase();
		return word;
	}

}
