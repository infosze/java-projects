package hu.ak_akademia.book4you.validation;

public class PasswordValidator implements Validator{
	private String value;

	public PasswordValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Jelszó mező üres!").validate();;
		if (!value.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"))
			throw new MyException("Jelszó nem megfelelő formátumú! Min. 8 karakter: kisbetű, nagybetű, szám.");
	}
}
