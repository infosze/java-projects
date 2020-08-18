package hu.ak_akademia.book4you.validation;

public class TextValidator implements Validator {
	private String value;
	private boolean canBeEmpty;
	private String message;

	public TextValidator(String value, boolean canBeEmty) {
		this.value = value;
		this.canBeEmpty = canBeEmty;
		this.message = "A mező nem lehet üres!";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean isValid() throws Exception {
		if (value == null) {
			throw new MyException(message);
		}

		if (!canBeEmpty) {
			if (value.trim().isEmpty()) {
				throw new MyException(message);
			}
		}

		return true;
	}

}
