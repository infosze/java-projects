package hu.ak_akademia.book4you.validation;

public class TextValidator implements Validator {
	private String value;
	private String alertMessage;

	public TextValidator(String value, String alertMessage) {
		this.value = value;
		this.alertMessage = alertMessage;
	}

	public String getAlertMessage() {
		return alertMessage;
	}

	@Override
	public void validate() throws Exception {
		new ExistenceValidator(value, alertMessage);

		if (value.trim().isEmpty()) {
			throw new MyException(alertMessage);
		}
	}
}
