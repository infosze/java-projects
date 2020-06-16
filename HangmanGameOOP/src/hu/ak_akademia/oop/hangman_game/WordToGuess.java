package hu.ak_akademia.oop.hangman_game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WordToGuess {

	public String[] wordsFromTxt(String file) {
		String[] words;
		try {
			words = Files.readAllLines(Path.of(file), StandardCharsets.UTF_8).toArray(new String[0]);
		} catch (IOException e) {
			return null;
		}
		return words;
	}
}
