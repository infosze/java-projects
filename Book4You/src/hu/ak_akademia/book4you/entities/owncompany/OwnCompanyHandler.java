package hu.ak_akademia.book4you.entities.owncompany;

public interface OwnCompanyHandler {
	OwnCompany load(String from);
	void modify(OwnCompany newData);
	void save(String to);
}
