package hu.ak_akademia.book4you.entities.certificate;

import java.time.LocalDate;
import java.util.List;

import hu.ak_akademia.book4you.entities.client.Client;

public interface CertificatesHandler {
	List<Certificate> get();
	List<Certificate> getByDate(LocalDate fromInclusively, LocalDate toInclusively);
	List<Certificate> getByClient(Client client);
	void add(Certificate certificate);
	void save();
}
