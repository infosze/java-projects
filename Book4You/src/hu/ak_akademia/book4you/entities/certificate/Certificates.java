package hu.ak_akademia.book4you.entities.certificate;

import java.util.List;

import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;
import hu.ak_akademia.book4you.entities.user.User;

public class Certificates implements CertificatesHandler{
	private List<Certificate> certificatesList;
	private final String url;
	
	public Certificates(String url) {
		this.url = url;
		this.certificatesList = load();
	}

	public List<Certificate> getCertificatesList() {
		return certificatesList;
	}

	@Override
	public List<Certificate> load() {
		DataLoader certificatesFileLoader = new FileHandler(url);

		return (List<Certificate>) certificatesFileLoader.load();
	}
	
	@Override
	public void add(Certificate certificate) {
		certificatesList.add(certificate);
	}

	@Override
	public void save() {
		DataSaver certificatesFileSaver = new FileHandler(url);
		certificatesFileSaver.save(certificatesList);
	}
}
