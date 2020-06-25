package hu.ak_akademia.oop.hangman_game.teacher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 * <b>Exercise: Hangman Game OOP (Szókitalálós játék OOP)</b>
 * <p>
 * To store the possible words and select one from them just before starting a new session.
 *
 * @author A&K Akadémia (Lajos Czuczor)
 */
class TargetSetter {

	private final String[] targets;
	private final Random random;

	TargetSetter() throws IOException {
		var wordsFile = Path.of(Constants.getWordsFileName());
		targets = Files.readAllLines(wordsFile, StandardCharsets.UTF_8).toArray(new String[0]);
		random = new Random();
	}

	String getNext() {
		return targets[random.nextInt(targets.length)].toUpperCase();
	}

}
