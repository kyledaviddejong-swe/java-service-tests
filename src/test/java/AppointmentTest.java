/*
 * Author: Kyle DeJong
 * Date: June 7, 2026
 * Assignment: SNHU CS-320, Module 5
 */

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentTest {

	// Valid values for an Appointment object
	private final String VALID_ID = "001";
	private final String VALID_DESC = "Default description.";
	
	// Valid max input size values
	private final String TEN_CHAR = "a".repeat(10);
	private final String FIFTY_CHAR = "a".repeat(50);
	
	// Invalid values for an Appointment object
	private final String ELEVEN_CHAR = "a".repeat(11);
	private final String FIFTY_ONE_CHAR = "a".repeat(51);
	
	// Helper to make a future Date
	private Date getFutureDate() {
		return new Date(System.currentTimeMillis() + 86400000L);
	}
	
	// Helper to make a past Date
	private Date getPastDate() {
		return new Date(System.currentTimeMillis() - 86400000L);
	}
	
	// Helper to make fresh Appointments
	private Appointment createValidAppointment() {
		return new Appointment(VALID_ID, getFutureDate(), VALID_DESC);
	}
	
	// Create a fresh Appointment before every test
	private Appointment appointment;
	
	@BeforeEach
	public void createFreshAppointment() {
		appointment = createValidAppointment();
	}
	
	// Test valid Appointment creation and value assignment
	@Test
	public void testCreateAppointment() {
		Date futureDate = getFutureDate();
		Appointment testAppointment = new Appointment(VALID_ID, futureDate, VALID_DESC);
		
		assertEquals(VALID_ID, testAppointment.getAppointmentID());
		assertEquals(futureDate, testAppointment.getAppointmentDate());
		assertEquals(VALID_DESC, testAppointment.getDescription());
	}
	
	// Appointment ID tests - 10 char, 11 char, null, no public setter
	@Test
	public void testTenCharAppointmentID() {
		Appointment testAppointment = new Appointment(TEN_CHAR, getFutureDate(), VALID_DESC);
		
		assertEquals(TEN_CHAR, testAppointment.getAppointmentID());
	}
	
	@Test
	public void testElevenCharAppointmentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(ELEVEN_CHAR, getFutureDate(), VALID_DESC);
		});
	}
	
	@Test
	public void testNullAppointmentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null, getFutureDate(), VALID_DESC);
		});
	}
	
	@Test
	public void testAppointmentIDHasNoPublicSetter() {
		assertThrows(NoSuchMethodException.class, () -> {
			Appointment.class.getMethod("setAppointmentID", String.class);
		});
	}
	
	// Date tests - future date, past date, null
	@Test
	public void testFutureAppointmentDate() {
		Date futureDate = getFutureDate();
		appointment.setAppointmentDate(futureDate);
		
		assertEquals(futureDate, appointment.getAppointmentDate());
	}
	
	@Test
	public void testPastAppointmentDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			appointment.setAppointmentDate(getPastDate());
		});
	}
	
	@Test
	public void testNullAppointmentDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			appointment.setAppointmentDate(null);
		});
	}
	
	// Constructor date tests - past date, null
	@Test
	public void testConstructorRejectsPastDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(VALID_ID, getPastDate(), VALID_DESC);
		});
	}
	
	@Test
	public void testConstructorRejectsNullDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(VALID_ID, null, VALID_DESC);
		});
	}
	
	// Description tests - 50 char, 51 char, null
	@Test
	public void testFiftyCharDescription() {
		appointment.setDescription(FIFTY_CHAR);
		
		assertEquals(FIFTY_CHAR, appointment.getDescription());
	}
	
	@Test
	public void testFiftyOneCharDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			appointment.setDescription(FIFTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			appointment.setDescription(null);
		});
	}
	
	// Constructor description tests - 51 char, null
	@Test
	public void testConstructorRejectsFiftyOneCharDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(VALID_ID, getFutureDate(), FIFTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testConstructorRejectsNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(VALID_ID, getFutureDate(), null);
		});
	}

	// Mutating a caller-owned Date must not change validated appointment state.
	@Test
	public void testConstructorCopiesDate() {
		Date suppliedDate = getFutureDate();
		long expectedTime = suppliedDate.getTime();
		Appointment created = new Appointment(VALID_ID, suppliedDate, VALID_DESC);
		suppliedDate.setTime(0L);
		assertEquals(expectedTime, created.getAppointmentDate().getTime());
	}

	@Test
	public void testSetterCopiesDate() {
		Date suppliedDate = getFutureDate();
		long expectedTime = suppliedDate.getTime();
		appointment.setAppointmentDate(suppliedDate);
		suppliedDate.setTime(0L);
		assertEquals(expectedTime, appointment.getAppointmentDate().getTime());
	}

	@Test
	public void testGetterReturnsDateCopy() {
		long expectedTime = appointment.getAppointmentDate().getTime();
		appointment.getAppointmentDate().setTime(0L);
		assertEquals(expectedTime, appointment.getAppointmentDate().getTime());
	}

	@Test
	public void testRejectedDateUpdatePreservesState() {
		long expectedTime = appointment.getAppointmentDate().getTime();
		assertThrows(IllegalArgumentException.class,
			() -> appointment.setAppointmentDate(getPastDate()));
		assertEquals(expectedTime, appointment.getAppointmentDate().getTime());
	}
}