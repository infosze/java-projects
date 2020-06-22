package hu.ak_akademia.book4you.entities.certificate;

import java.util.List;

public class Certificates implements CertificatesHandler{
	private List<Certificate> certificatesList;
	
	public Certificates(List<Certificate> certificatesList) {
		this.certificatesList = loadToList();
	}

	public List<Certificate> getCertificatesList() {
		return certificatesList;
	}

	@Override
	public List<Certificate> loadToList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void add(Certificate newCertificate) {
		// TODO Auto-generated method stub
	}
}
