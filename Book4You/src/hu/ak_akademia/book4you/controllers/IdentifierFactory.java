package hu.ak_akademia.book4you.controllers;

public class IdentifierFactory {

	String[] letters = new String[2];

	String generateIdentifier(String textField) {
		cutFirstLettersFromName(textField);
		String identifier;
		identifier = letters[0];
		identifier += letters[1];
		int identityNumber = (int) (Math.random() * 10_000) + 0;
		String formattedIdentityNumber = String.format("%04d", identityNumber);
		identifier += formattedIdentityNumber;
		return identifier;
	}

	private void cutFirstLettersFromName(String textField) {
		String name1;
		String name2;
		String[] name = textField.split(" ");
		if (name.length <= 2) {
			name1 = name[0];
			name2 = name[1];
		} else if (name[1].lastIndexOf(".") > -1) {
			name1 = name[0];
			name2 = name[2];
		} else {
			name1 = name[0];
			name2 = name[1];
		}
		letters = new String[] { name1.substring(0, 2).toUpperCase(), name2.substring(0, 1) };
	}

}
