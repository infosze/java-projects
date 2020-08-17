package hu.ak_akademia.book4you.calculations;

import java.util.List;
import hu.ak_akademia.book4you.entities.certificate.Certificate;

public class ClosingBalanceCalculation implements Calculation {
	private List<Certificate> certificates;

	public ClosingBalanceCalculation(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@Override
	public long calculate() {
		if (certificates.size() > 0) {
			int lastIndex = certificates.size() - 1;
			return certificates.get(lastIndex).getBalance();
		}

		return 0;
	}

	@Override
	public String format(long number) {
		return String.format("%,d%s", number, " Ft");
	}
}
