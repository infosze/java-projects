package hu.ak_akademia.book4you.validation;

public class HouseNumberValidator implements Validator {
	private String value;

	public HouseNumberValidator(String value) {
		this.value = value;
	}

	@Override
	public void validate() throws Exception {
		new TextValidator(value, "Házszám mező üres!").validate();;
		if (!value.matches("((\\d{1,})(( |\\/)([a-zA-Z]))?)")) throw new MyException("Házszám nem megfelelő formátumú! Helyes formátum pl.: 1 vagy 1/a vagy 1/A"); 
	}

}
