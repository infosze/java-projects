package hu.ak_akademia.book4you.entities.certificate;

import java.util.List;

public interface CertificatesHandler {
	List<Certificate> generate();
	void add();
}
