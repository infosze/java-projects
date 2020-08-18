package hu.ak_akademia.book4you.calculations;

import java.util.List;
import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Direction;

public class OpeningBalanceCalculation implements Calculation {
	private List<Certificate> certificates;

	public OpeningBalanceCalculation(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@Override
	public long calculate() {
		if (certificates.size() > 0) {
			if (certificates.get(0).getDirection() == Direction.INCOME) {
				return certificates.get(0).getBalance() - certificates.get(0).getAmount();
			} else if (certificates.get(0).getDirection() == Direction.OUTCOME) {
				return certificates.get(0).getBalance() + certificates.get(0).getAmount();
			}
		}

		return 0;
	}

	@Override
	public String format(long number) {
		return String.format("%,d%s", number, " Ft");
	}
}
