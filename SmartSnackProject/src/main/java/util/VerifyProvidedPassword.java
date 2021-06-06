package util;

public class VerifyProvidedPassword {

	public static void main(String[] args) {
		// User provided password to validate
		String providedPassword = "3333";

		// Encrypted and Base64 encoded password read from database
		String securePassword = "VgeJjqKH5aZDaEjOon47fZ1Z2oZI00KaUKrM8ohHWgw=";

		// Salt value stored in database
		String salt = "x5JjzGlNHL6jHEh66kYzoB4zpuFyty";

		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);

		if (passwordMatch) {
			System.out.println("Provided user password " + providedPassword + " is correct.");
		} else {
			System.out.println("Provided password is incorrect");
		}
	}

}