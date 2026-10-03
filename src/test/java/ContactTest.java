/*
 * Author: Kyle DeJong
 * Date: May 24th, 2026
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactTest {
	
	// Valid parameter values for Contact object
	private final String VALID_ID = "001"; 
	private final String VALID_FIRST_NAME = "John"; 
	private final String VALID_LAST_NAME = "Doe"; 
	private final String VALID_PHONE_NUMBER = "5551234567"; 
	private final String VALID_ADDRESS = "123 Fake Street"; 
	private final String TEN_CHAR = "1234567890";
	private final String THIRTY_CHAR = "123456789012345678901234567890";
	// Invalid parameter values for Contact object
	private final String ELEVEN_CHAR = "12345678901";	
	private final String THIRTY_ONE_CHAR = "1234567890123456789012345678901";
	
	// Helper to build a valid Contact object
	private Contact createValidContact() {
		return new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
	}

	// Create a fresh Contact object before each test 
	private Contact contact; 
	
	@BeforeEach
	public void createFreshContact() {
		contact = createValidContact(); 
	}

	// Test valid Contact was created and assigned properly 
	@Test
	public void testCreateContact() { 					
		assertEquals(VALID_ID, contact.getContactID());
		assertEquals(VALID_FIRST_NAME, contact.getFirstName());
		assertEquals(VALID_LAST_NAME, contact.getLastName());
		assertEquals(VALID_PHONE_NUMBER, contact.getPhoneNumber());
		assertEquals(VALID_ADDRESS, contact.getAddress());
	}
	
	// ID tests - 10 char, 11 char, null
	@Test
	public void testTenCharID() {
		contact = new Contact(TEN_CHAR,VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
		assertEquals(TEN_CHAR, contact.getContactID());
	}
	
	@Test
	public void testElevenCharID() { 
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(ELEVEN_CHAR,VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS); 
		});
	}
	
	@Test
	public void testNullID() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null,VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
		});
	}
	
	// First name tests - 10 char, 11 char, null
	@Test
	public void testTenCharFirstName() {
		contact.setFirstName(TEN_CHAR);
		assertEquals(TEN_CHAR, contact.getFirstName());
	}

	@Test
	public void testElevenCharFirstName() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setFirstName(ELEVEN_CHAR);
		});
	}
	
	@Test
	public void testNullFirstName() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setFirstName(null);
		});
	}
	
	// Last name tests - 10 char, 11 char, null
	@Test
	public void testTenCharLastName() {
		contact.setLastName(TEN_CHAR);
		assertEquals(TEN_CHAR, contact.getLastName());
	}

	@Test
	public void testElevenCharLastName() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setLastName(ELEVEN_CHAR);
		});
	}
	
	@Test
	public void testNullLastName() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setLastName(null);
		});
	}
	
	// Phone number tests - 10 digit, 9 digit, 11 digit, non-numerical digit, null
	@Test
	public void testTenDigitPhoneNumber() {
		contact.setPhoneNumber(TEN_CHAR);
		assertEquals(TEN_CHAR, contact.getPhoneNumber());
	}
	
	@Test
	public void testNineDigitPhoneNumber() { 
		assertThrows(IllegalArgumentException.class, () -> { 
			contact.setPhoneNumber(TEN_CHAR.substring(0, 9));
		});
	}
	
	@Test
	public void testElevenDigitPhoneNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setPhoneNumber(ELEVEN_CHAR);
		});
	}
	
	@Test
	public void testNonNumericPhoneNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setPhoneNumber("123456789A");
		});
	}
	
	@Test
	public void testNullPhoneNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setPhoneNumber(null);
		});
	}
	
	// Address tests - 30 char, 31 char, null
	@Test
	public void testThirtyCharAddress() {
		contact.setAddress(THIRTY_CHAR);
		assertEquals(THIRTY_CHAR, contact.getAddress());
	}
	
	@Test
	public void testThirtyOneCharAddress() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress(THIRTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testNullAddress() {
		assertThrows(IllegalArgumentException.class, () -> {
			contact.setAddress(null); 
		});
	}
}

