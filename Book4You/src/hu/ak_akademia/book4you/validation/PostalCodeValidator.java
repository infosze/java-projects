package hu.ak_akademia.book4you.validation;

public class PostalCodeValidator implements Validator {
	private String value;

	public PostalCodeValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Irányítószám mező üres!").validate();;
		if (!value.matches("([a-zA-Z\\d]{4,})"))
			throw new MyException("Irányítószám nem megfelelő formátumú! Legalább 4 hosszúnak kell lennie és tartalmazhat számot és kis/nagy betűket.");
	}
}
