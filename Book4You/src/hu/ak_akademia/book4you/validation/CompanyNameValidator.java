package hu.ak_akademia.book4you.validation;

public class CompanyNameValidator implements Validator{

	private String value;

	public CompanyNameValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Cég neve mező üres!").validate();;
		if (!value.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ0-9]{2,})(?: |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ0-9]*\\b\\.?)(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ0-9]*))\\ *"))
			throw new MyException("Cég neve nem megfelelő formátumú!");
	}
}
