package hu.ak_akademia.book4you.validation;

public class ExistenceValidator implements Validator{
	private Object obj;
	private String alertMessage;

	public ExistenceValidator(Object obj, String alertMessage) {
		this.obj = obj;
		this.alertMessage = alertMessage;
	}

	@Override
	public void validate() throws Exception {
		if (obj == null) throw new MyException(alertMessage);
	}
}
