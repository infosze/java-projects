package hu.ak_akademia.oop.hangman_game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

class WordStore {

	private static final String WORDS_FILE_NAME = "Words.txt";
	private String[] words;
	private Random random = new Random();

	public WordStore() throws IOException {
		words = wordsFromTxt(WORDS_FILE_NAME);
	}

	private String[] wordsFromTxt(String file) throws IOException {
		String[] words;
		words = Files.readAllLines(Path.of(file), StandardCharsets.UTF_8).toArray(new String[0]);
		return words;
	}

	String selectWord() {
		return words[random.nextInt(words.length)].toUpperCase();
	}

}
