package hu.ak_akademia.book4you.validation;

public class CashierNameValidator implements Validator{

	private String value;

	public CashierNameValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "A név mező üres!").validate();;
		if (!value.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(?: |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ0-9]*\\b\\.?)(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ0-9]*))\\ *"))
			throw new MyException("A név nem megfelelő formátumú! Legalább 2 szóból kell állnia!");
	}
}
