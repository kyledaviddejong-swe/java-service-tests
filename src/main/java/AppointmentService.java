/*
 * Author: Kyle DeJong
 * Date: June 7, 2026
 */

import java.util.HashMap;

public class AppointmentService {
	private HashMap<String, Appointment> appointments; 
	
	public AppointmentService() {
		appointments = new HashMap<String, Appointment>();
	}
	
	// Add new Appointment if the ID is unique
	public void addAppointment(Appointment appointment) {
		if(appointment == null || appointments.containsKey(appointment.getAppointmentID())) {
			throw new IllegalArgumentException("Invalid duplicate or null appointment ID");
		}
		else {
			appointments.put(appointment.getAppointmentID(), appointment);
		}
	}
	
	// Delete Appointment per appointmentID
	public void deleteAppointment(String appointmentID) {
		if(appointments.remove(appointmentID) == null) {
			throw new IllegalArgumentException("Invalid appointment ID");
		}
	}
	
	// Get Appointment per appointmentID, mainly for testing
	public Appointment getAppointment(String appointmentID) {
		if(!appointments.containsKey(appointmentID)) {
			throw new IllegalArgumentException("Appointment ID not found");
		}
		else {
			return appointments.get(appointmentID);
		}
	}
}