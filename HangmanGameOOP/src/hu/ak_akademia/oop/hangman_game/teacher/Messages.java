package hu.ak_akademia.oop.hangman_game.teacher;

/**
 * <b>Exercise: Hangman Game OOP (Szókitalálós játék OOP)</b>
 * <p>
 * Textual messages in the main program.
 *
 * @author A&K Akadémia (Lajos Czuczor)
 */
class Messages {

	static String getWelcomeText() {
		return String.format("Üdvözöllek a Szókitaláló játékban!%n%n");
	}

	static String getInfoText() {
		return String.format("A játék egy-egy menetében egy szót kell kitalálnod.%n"
				+ "A szó legfeljebb 11 betűs magyar szó, szóközök, kötőjelek és más írásjelek nélkül, szófajára%n"
				+ "nézve főnév, melléknév, vagy ige, ragozatlan alakban, ill. egyes szám 3. személyben.%n"
				+ "Egyszerre egy betűt adhatsz meg; a szó kitalálásához az összes betűt azonosítanod kell. Ehhez%n"
				+ "legfeljebb %d lehetőséged van. A program segítségképpen felfedi a szóban az eltalált betűket,%n"
				+ "és számon tartja azokat a betűket is, amelyek hibás tippnek bizonyultak. Tippeléskor a kis- és%n"
				+ "nagybetűk közti különbség nem számít.%n", Constants.getMaxMismatches());
	}

	static String getFarewellText() {
		return String.format("%nViszlát!%n");
	}

}
