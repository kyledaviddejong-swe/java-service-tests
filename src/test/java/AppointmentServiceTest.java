/*
 * Author: Kyle DeJong
 * Date: June 7, 2026
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

	// Valid test values
	private final String VALID_ID = "001";
	private final String SECOND_ID = "002";
	private final String INVALID_ID = "999";
	private final String VALID_DESC = "Default description.";
	
	private AppointmentService service;
	
	// Create a fresh AppointmentService before each test
	@BeforeEach
	public void createFreshService() {
		service = new AppointmentService();
	}
	
	// Helper to make a future Date
	private Date getFutureDate() {
		return new Date(System.currentTimeMillis() + 86400000L);
	}
	
	// Helper to build valid Appointment objects
	private Appointment createAppointment(String appointmentID) {
		return new Appointment(appointmentID, getFutureDate(), VALID_DESC);
	}
	
	// Test adding and retrieving an Appointment
	@Test
	public void testAddAppointment() {
		Appointment appointment = createAppointment(VALID_ID);
		
		service.addAppointment(appointment);
		
		assertEquals(VALID_ID, service.getAppointment(VALID_ID).getAppointmentID());
	}
	
	// Test adding multiple Appointments with unique IDs
	@Test
	public void testAddMultipleAppointments() {
		service.addAppointment(createAppointment(VALID_ID));
		service.addAppointment(createAppointment(SECOND_ID));
		
		assertEquals(VALID_ID, service.getAppointment(VALID_ID).getAppointmentID());
		assertEquals(SECOND_ID, service.getAppointment(SECOND_ID).getAppointmentID());
	}
	
	// Test adding duplicate Appointment ID
	@Test
	public void testAddDuplicateAppointmentID() {
		Appointment appointment = createAppointment(VALID_ID);
		Appointment duplicateAppointment = createAppointment(VALID_ID);
		
		service.addAppointment(appointment);
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment(duplicateAppointment);
		});
	}
	
	// Test adding null Appointment
	@Test
	public void testAddNullAppointment() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment(null);
		});
	}
	
	// Test getting an Appointment by valid ID
	@Test
	public void testGetAppointment() {
		Appointment appointment = createAppointment(VALID_ID);
		
		service.addAppointment(appointment);
		
		assertEquals(appointment, service.getAppointment(VALID_ID));
	}
	
	// Test getting invalid Appointment ID
	@Test
	public void testGetInvalidAppointmentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.getAppointment(INVALID_ID);
		});
	}
	
	// Test deleting an Appointment by ID
	@Test
	public void testDeleteAppointment() {
		service.addAppointment(createAppointment(VALID_ID));
		service.deleteAppointment(VALID_ID);
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.getAppointment(VALID_ID);
		});
	}
	
	// Test deleting invalid Appointment ID
	@Test
	public void testDeleteInvalidAppointmentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteAppointment(INVALID_ID);
		});
	}
}