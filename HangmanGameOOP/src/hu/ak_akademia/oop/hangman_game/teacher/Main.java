package hu.ak_akademia.oop.hangman_game.teacher;

import java.io.IOException;
import java.util.Scanner;

/**
 * <b>Exercise: Hangman Game OOP (Szókitalálós játék OOP)</b>
 * <p>
 * The main program.
 *
 * @author A&K Akadémia (Lajos Czuczor)
 */
public class Main {

	private Scanner scanner;

	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (IOException e) {
			System.err.println(e);
			return;
		}
	}

	private void run() throws IOException {
		System.out.print(Messages.getWelcomeText());
		System.out.print(Messages.getInfoText());
		TargetSetter targetSetter = new TargetSetter();
		scanner = new Scanner(System.in);
		for (boolean first = true; isSessionToStart(first); first = false) {
			String target = targetSetter.getNext();
			var session = new Session(scanner, target);
			session.play();
			if (session.isGameToQuit()) {
				break;
			}
		}
		scanner.close();
		System.out.print(Messages.getFarewellText());
	}

	private boolean isSessionToStart(boolean first) {
		System.out.printf("%nIndulhat %smenet? ", first ? "az első " : "a következő ");
		String answer = scanner.nextLine().trim().toLowerCase();
		return answer.isEmpty() || answer.charAt(0) != 'n';
	}

}
