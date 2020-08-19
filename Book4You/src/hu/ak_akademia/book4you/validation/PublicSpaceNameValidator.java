package hu.ak_akademia.book4you.validation;

public class PublicSpaceNameValidator implements Validator {
	private String value;

	public PublicSpaceNameValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Közterület neve mező üres!").validate();;
		if (!value.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ\\d]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]\\d*))\\ *"))
			throw new MyException("Közterület neve nem megfelelő formátumú!");
	}
}
