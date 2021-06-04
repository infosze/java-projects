package util;

public class VerifyProvidedPassword {

	public static void main(String[] args) {
		// User provided password to validate
		String providedPassword = "2211";

		// Encrypted and Base64 encoded password read from database
		String securePassword = "9EspnNNYu5MEWYvuG+Ym4Xg9odx0E3uvcdfv6o8a8Bs=";

		// Salt value stored in database
		String salt = "NzC3ÖHémbaűís7jée0á3ű5wWZ2s1AY";

		boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);

		if (passwordMatch) {
			System.out.println("Provided user password " + providedPassword + " is correct.");
		} else {
			System.out.println("Provided password is incorrect");
		}
	}

}