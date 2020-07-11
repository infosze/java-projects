package hu.ak_akademia.book4you.controllers;

public class Messages {

	static String getErrorMessageEmpty() {
		return String.format("Kérem töltse ki a mezőt!");
	}

	static String getErrorMessageWrongFormat() {
		return String.format("Kérem megfelelő formátumot adjon meg!");
	}

}
