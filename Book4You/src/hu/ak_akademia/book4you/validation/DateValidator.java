package hu.ak_akademia.book4you.validation;

import java.time.LocalDate;

public class DateValidator implements Validator {
	private LocalDate from;
	private LocalDate to;

	public DateValidator(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void validate() throws Exception {
		new ExistenceValidator(from, "Kérem adjon meg kezdő dátumot!").validate();
		new ExistenceValidator(to, "Kérem adjon meg végső dátumot!").validate();

		if (from.isAfter(to))
			throw new MyException("A kezdő dátum nem lehet nagyobb, mint a végső dátum!");
	}
}
