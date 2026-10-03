/*
 * Author: Kyle DeJong
 * Date: May 24th, 2026
 */

public class Contact {
	// ID can't be updated after creation
	private final String contactID;
	private String firstName, lastName, phoneNumber, address;
	
	// No empty default, requires all fields on creation
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		if(contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Invalid contact ID");
		}
		
		this.contactID = contactID;
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setAddress(address);
	}
	
	// Getters
	public String getContactID() { return this.contactID; } 		
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }
	public String getPhoneNumber() { return this.phoneNumber; }
	public String getAddress() { return this.address; }
	
	// Setters
	// firstName must be 10 characters or less and not null
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first name");
		}
		else { 
			this.firstName = firstName; 
		}
	}
	
	// lastName must be 10 characters or less and not null
	public void setLastName(String lastName) { 
		if(lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last name");
		}
		else { 
			this.lastName = lastName; 
		}
	}
	
	// phoneNumber must be exactly 10 digits and not null
	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber == null || !phoneNumber.matches("\\d{10}")) { // Regex 
			throw new IllegalArgumentException("Invalid phone number");
		}
		else {
			this.phoneNumber = phoneNumber; 
		}
	}
	
	// address must be 30 characters or less and not null
	public void setAddress(String address) { 
		if(address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid address");
		}
		else { 
			this.address = address; 
		}
	}
}