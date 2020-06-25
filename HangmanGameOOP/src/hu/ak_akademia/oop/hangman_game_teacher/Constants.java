package hu.ak_akademia.oop.hangman_game.teacher;

/**
 * <b>Exercise: Hangman Game OOP (Szókitalálós játék OOP)</b>
 * <p>
 * Constants.
 *
 * @author A&K Akadémia (Lajos Czuczor)
 */
class Constants {

	private static final int MAX_MISMATCHES = 12;
	private static final String WORDS_FILE_NAME = "Words.txt";

	static int getMaxMismatches() {
		return MAX_MISMATCHES;
	}

	static String getWordsFileName() {
		return WORDS_FILE_NAME;
	}

}
