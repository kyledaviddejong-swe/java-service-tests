/*
 * Author: Kyle DeJong
 * Date: June 7, 2026
 * Assignment: SNHU CS-320, Module 5
 */

import java.util.Date; 

public class Appointment {
	private final String appointmentID; 
	private Date appointmentDate;
	private String description; 	

	public Appointment(String appointmentID, Date appointmentDate, String description) {
		if(appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Invalid appointment ID");
		}
		else {
			this.appointmentID = appointmentID; 
		}
		
		setAppointmentDate(appointmentDate);
		setDescription(description);		
	}
	
	// Accessors
	public String getAppointmentID() {
		return this.appointmentID; 
	}
	
	public Date getAppointmentDate() {
		return new Date(this.appointmentDate.getTime());
	}
	
	public String getDescription() {
		return this.description;
	}
	
	// Mutators
	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null) {
			throw new IllegalArgumentException("Invalid appointment date");
		}
		// Validate and store our own copy so callers cannot bypass the setter.
		Date dateCopy = new Date(appointmentDate.getTime());
		if (dateCopy.before(new Date())) {
			throw new IllegalArgumentException("Invalid appointment date");
		}
		this.appointmentDate = dateCopy;
	}
	
	public void setDescription(String description) {
		if(description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description value");
		}
		else {
			this.description = description;
		}
	}
}