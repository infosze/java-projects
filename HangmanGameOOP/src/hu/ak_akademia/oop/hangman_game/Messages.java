package hu.ak_akademia.oop.hangman_game;

class Messages {

	void welcome() {
		System.out.println("Üdvözöllek a Szókitaláló játékban!");
	}

	void instruct() {
		System.out.printf("%nA játék egy-egy menetében egy szót kell kitalálnod.\r\n"
				+ "A szó legfeljebb 11 betűs magyar szó, szóközök, kötőjelek és más írásjelek nélkül, szófajára\r\n"
				+ "nézve főnév, melléknév, vagy ige, ragozatlan alakban, ill. egyes szám 3. személyben.\r\n"
				+ "Egyszerre egy betűt adhatsz meg; a szó kitalálásához az összes betűt azonosítanod kell. Ehhez\r\n"
				+ "legfeljebb 12 lehetőséged van. A program segítségképpen felfedi a szóban az eltalált betűket,\r\n"
				+ "és számon tartja azokat a betűket is, amelyek hibás tippnek bizonyultak. Tippeléskor a kis- és\r\n"
				+ "nagybetűk közti különbség nem számít.%n");
	}

	void sayGoodbye() {
		System.out.printf("%nViszlát!%n");
	}
}
