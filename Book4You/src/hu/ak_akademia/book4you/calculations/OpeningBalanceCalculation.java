package hu.ak_akademia.book4you.calculations;

import java.util.List;
import hu.ak_akademia.book4you.entities.certificate.Certificate;

public class OpeningBalanceCalculation implements Calculation {
	private List<Certificate> certificates;

	public OpeningBalanceCalculation(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@Override
	public long calculate() {
		if (certificates.size() > 0) {
			return certificates.get(0).getBalance() - certificates.get(0).getAmount();
		}

		return 0;
	}

	@Override
	public String format(long number) {
		return String.format("%,d%s", number, " Ft");
	}
}
