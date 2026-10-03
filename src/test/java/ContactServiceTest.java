/*
 * Author: Kyle DeJong
 * Date: May 24th, 2026
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

	// Valid parameter values for ContactService tests
	private final String VALID_ID = "001";
	private final String SECOND_ID = "002";
	private final String INVALID_ID = "999";
	
	private final String UPDATED_FIRST_NAME = "Jane";
	private final String UPDATED_LAST_NAME = "Smith";
	private final String UPDATED_PHONE_NUMBER = "5557654321";
	private final String UPDATED_ADDRESS = "456 Real Avenue";

	// Create a fresh ContactService before each test
	private ContactService service;
		
	@BeforeEach
	public void createFreshService() {
		service = new ContactService();
	}
	
	// Helper to build valid Contact objects
	private Contact createContact(String contactID) {
		return new Contact(contactID, "John", "Doe", "5551234567", "123 Fake Street");
	}
	
	// Test adding and retrieving a Contact
	@Test
	public void testAddContact() {
		Contact contact = createContact(VALID_ID);
		
		service.addContact(contact);
		
		assertEquals(VALID_ID, service.getContact(VALID_ID).getContactID());
	}
	
	// Test adding multiple Contacts with unique IDs
	@Test
	public void testAddMultipleContacts() {
		service.addContact(createContact(VALID_ID));
		service.addContact(createContact(SECOND_ID));
		
		assertEquals(VALID_ID, service.getContact(VALID_ID).getContactID());
		assertEquals(SECOND_ID, service.getContact(SECOND_ID).getContactID());
	}
	
	// Test adding duplicate Contact ID 
	@Test
	public void testAddDuplicateContactID() {
		Contact contact = createContact(VALID_ID);
		Contact duplicateContact = createContact(VALID_ID);
		
		service.addContact(contact);
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addContact(duplicateContact);
		});
	}
	
	// Test adding null Contact 
	@Test
	public void testAddNullContact() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.addContact(null);
		});
	}
	
	// Test getting a Contact by valid ID
	@Test
	public void testGetContact() {
		Contact contact = createContact(VALID_ID);
		
		service.addContact(contact);
		
		assertEquals(contact, service.getContact(VALID_ID));
	}
	
	// Test getting invalid Contact ID throws exception
	@Test
	public void testGetInvalidContactID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.getContact(INVALID_ID);
		});
	}
	
	// Test deleting a Contact by ID
	@Test
	public void testDeleteContact() {
		service.addContact(createContact(VALID_ID));
		service.deleteContact(VALID_ID);
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.getContact(VALID_ID);
		});
	}
	
	// Test deleting invalid Contact ID throws exception
	@Test
	public void testDeleteInvalidContactID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteContact(INVALID_ID);
		});
	}
	
	// Test updating firstName by Contact ID
	@Test
	public void testUpdateFirstName() {
		service.addContact(createContact(VALID_ID));
		service.updateFirstName(VALID_ID, UPDATED_FIRST_NAME);
		
		assertEquals(UPDATED_FIRST_NAME, service.getContact(VALID_ID).getFirstName());
	}
	
	// Test updating firstName with invalid Contact ID 
	@Test
	public void testUpdateFirstNameInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateFirstName(INVALID_ID, UPDATED_FIRST_NAME);
		});
	}
	
	// Test updating lastName by Contact ID
	@Test
	public void testUpdateLastName() {
		service.addContact(createContact(VALID_ID));
		service.updateLastName(VALID_ID, UPDATED_LAST_NAME);
		
		assertEquals(UPDATED_LAST_NAME, service.getContact(VALID_ID).getLastName());
	}
	
	// Test updating lastName with invalid Contact ID 
	@Test
	public void testUpdateLastNameInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateLastName(INVALID_ID, UPDATED_LAST_NAME);
		});
	}
	
	// Test updating phoneNumber by Contact ID
	@Test
	public void testUpdatePhoneNumber() {
		service.addContact(createContact(VALID_ID));
		service.updatePhoneNumber(VALID_ID, UPDATED_PHONE_NUMBER);
		
		assertEquals(UPDATED_PHONE_NUMBER, service.getContact(VALID_ID).getPhoneNumber());
	}
	
	// Test updating phoneNumber with invalid Contact ID throws exception
	@Test
	public void testUpdatePhoneNumberInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updatePhoneNumber(INVALID_ID, UPDATED_PHONE_NUMBER);
		});
	}
	
	// Test updating address by Contact ID
	@Test
	public void testUpdateAddress() {
		service.addContact(createContact(VALID_ID));
		service.updateAddress(VALID_ID, UPDATED_ADDRESS);
		
		assertEquals(UPDATED_ADDRESS, service.getContact(VALID_ID).getAddress());
	}
	
	// Test updating address with invalid Contact ID throws exception
	@Test
	public void testUpdateAddressInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateAddress(INVALID_ID, UPDATED_ADDRESS);
		});
	}
}