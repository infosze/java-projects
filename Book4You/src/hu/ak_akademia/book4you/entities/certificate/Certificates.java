package hu.ak_akademia.book4you.entities.certificate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;
import hu.ak_akademia.book4you.entities.client.Client;

public class Certificates implements CertificatesHandler {
	private List<Certificate> certificatesList;
	private final String url;

	public Certificates(String url) {
		this.url = url;
		this.certificatesList = load();
	}

	public List<Certificate> load() {
		DataLoader<Certificate> certificatesFileLoader = new FileHandler<Certificate>(url);

		return certificatesFileLoader.load();
	}

	@Override
	public void add(Certificate certificate) {
		certificatesList.add(certificate);
	}

	@Override
	public void save() {
		DataSaver<Certificate> certificatesFileSaver = new FileHandler<Certificate>(url);
		certificatesFileSaver.save(certificatesList);
	}

	@Override
	public List<Certificate> getByClient(Client client) {
		List<Certificate> result = new ArrayList<>();

		for (Certificate obj : certificatesList) {
			if (obj.getClient().equals(client)) {
				result.add(obj);
			}
		}

		return result;
	}

	@Override
	public List<Certificate> getByDate(LocalDate fromInclusively, LocalDate toInclusively) {
		List<Certificate> result = new ArrayList<>();

		for (Certificate obj : certificatesList) {
			if (obj.getDate().isAfter(fromInclusively.minusDays(1))
					&& obj.getDate().isBefore(toInclusively.plusDays(1))) {
				result.add(obj);
			}
		}

		return result;
	}

	@Override
	public List<Certificate> get() {
		return certificatesList;
	}
}
