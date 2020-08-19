package hu.ak_akademia.book4you.validation;

public class CountryNameValidator implements Validator {
	private String value;

	public CountryNameValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Ország mező üres!").validate();;
		if (!value.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *"))
			throw new MyException("Ország neve nem megfelelő formátumú!");
	}
}
