/*
 * Author: Kyle DeJong
 * Date: May 24th, 2026
 */

import java.util.HashMap;
import java.util.Map;

public class ContactService {
	private Map<String, Contact> contacts; 
	
	public ContactService() {
		contacts = new HashMap<String, Contact>(); 
	}
	
	public void addContact(Contact contact) {
		if(contact == null || contacts.containsKey(contact.getContactID())) {
			throw new IllegalArgumentException("Invalid or duplicate contact");
		} 
		else {
			contacts.put(contact.getContactID(), contact); 
		}
	}
	
	public void deleteContact(String contactID) {
		if(contacts.remove(contactID) == null) {
			throw new IllegalArgumentException("Contact ID not found");
		}
	}
	
	public Contact getContact(String contactID) {
		if(!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID not found");
		}
		else {
			return contacts.get(contactID);
		}
	}
	
	public void updateFirstName(String contactID, String firstName) {
		getContact(contactID).setFirstName(firstName);
	}
	
	public void updateLastName(String contactID, String lastName) {
		getContact(contactID).setLastName(lastName);
	}
	
	public void updatePhoneNumber(String contactID, String phoneNumber) {
		getContact(contactID).setPhoneNumber(phoneNumber);
	}
	
	public void updateAddress(String contactID, String address) { 
		getContact(contactID).setAddress(address);
	}
}