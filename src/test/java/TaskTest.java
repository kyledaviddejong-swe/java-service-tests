/*
 * Author: Kyle DeJong
 * Date: May 31, 2026
 * Assignment: SNHU CS-320, Module 4
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

	// Valid values for a Task object
	private final String VALID_ID = "001"; 
	private final String VALID_NAME = "Name"; 
	private final String VALID_DESC = "Default description.";
	
	// Valid max input size values
	private final String TEN_CHAR = "a".repeat(10);
	private final String TWENTY_CHAR = "a".repeat(20);
	private final String FIFTY_CHAR = "a".repeat(50);
	
	// Invalid values for a Task object
	private final String ELEVEN_CHAR = "a".repeat(11);
	private final String TWENTY_ONE_CHAR = "a".repeat(21);
	private final String FIFTY_ONE_CHAR = "a".repeat(51);
	
	// Helper to make fresh Tasks
	private Task createValidTask() {
		return new Task(VALID_ID, VALID_NAME, VALID_DESC);
	}
	
	// Create a fresh Task before every test
	private Task testTask; 
	
	@BeforeEach
	public void createFreshTask() {
		testTask = createValidTask();
	}
	
	// Test valid Task creation and value assignment
	@Test
	public void testCreateTask() {
		assertEquals(VALID_ID, testTask.getTaskID());
		assertEquals(VALID_NAME, testTask.getName());
		assertEquals(VALID_DESC, testTask.getDescription());
	}
	
	// Task ID tests - 10 char, 11 char, null
	@Test
	public void testTenCharTaskID() {
		testTask = new Task(TEN_CHAR, VALID_NAME, VALID_DESC); 
		assertEquals(TEN_CHAR, testTask.getTaskID()); 
	}
	
	@Test
	public void testElevenCharTaskID() {
		assertThrows(IllegalArgumentException.class, () -> { 
			new Task(ELEVEN_CHAR, VALID_NAME, VALID_DESC);
		});
	}

	@Test
	public void testNullTaskID() {
		assertThrows(IllegalArgumentException.class, () -> { 
			new Task(null, VALID_NAME, VALID_DESC);
		});
	}
	
	// Name tests - 20 char, 21 char, null
	@Test
	public void testTwentyCharName() {
		testTask.setName(TWENTY_CHAR);
		assertEquals(TWENTY_CHAR, testTask.getName());
	}
	
	@Test
	public void testTwentyOneCharName() {
		assertThrows(IllegalArgumentException.class, () -> {
			testTask.setName(TWENTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			testTask.setName(null);
		});
	}
	
	// Constructor name tests - 21 char, null
	@Test
	public void testConstructorRejectsTwentyOneCharName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(VALID_ID, TWENTY_ONE_CHAR, VALID_DESC);
		});
	}
	
	@Test
	public void testConstructorRejectsNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(VALID_ID, null, VALID_DESC);
		});
	}
	
	// Description tests - 50 char, 51 char, null
	@Test
	public void testFiftyCharDescription() {
		testTask.setDescription(FIFTY_CHAR);
		assertEquals(FIFTY_CHAR, testTask.getDescription());
	}
	
	@Test
	public void testFiftyOneCharDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			testTask.setDescription(FIFTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			testTask.setDescription(null);
		});
	}
	
	// Constructor description tests - 51 char, null
	@Test
	public void testConstructorRejectsFiftyOneCharDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(VALID_ID, VALID_NAME, FIFTY_ONE_CHAR);
		});
	}
	
	@Test
	public void testConstructorRejectsNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(VALID_ID, VALID_NAME, null);
		});
	}
}