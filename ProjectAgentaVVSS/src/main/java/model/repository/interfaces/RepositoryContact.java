package model.repository.interfaces;

import java.util.List;

import model.base.Contact;

public interface RepositoryContact {

	List<Contact> getContacts();
	void addContact(Contact contact);
	boolean removeContact(Contact contact);
	boolean saveContracts();
	int count();
	Contact getByName(String string);
}
