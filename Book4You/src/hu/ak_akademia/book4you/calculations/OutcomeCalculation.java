package hu.ak_akademia.book4you.calculations;

import java.util.List;

import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Direction;

public class OutcomeCalculation implements Calculation {
	private List<Certificate> certificates;

	public OutcomeCalculation(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@Override
	public long calculate() {
		long result = 0;

		for (Certificate e : certificates) {
			if (e.getDirection() == Direction.OUTCOME) {
				result += e.getAmount();
			}
		}

		return result;
	}

	@Override
	public String format(long number) {
		return String.format("%,d%s", number, " Ft");
	}
}
