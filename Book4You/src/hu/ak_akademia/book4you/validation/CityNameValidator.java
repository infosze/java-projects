package hu.ak_akademia.book4you.validation;

public class CityNameValidator implements Validator {
	private String value;

	public CityNameValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Város mező üres!").validate();;
		if (!value.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *"))
			throw new MyException("Város neve nem megfelelő formátumú!");
	}
}
