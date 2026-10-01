import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
	// Valid test values 
	private final String VALID_ID = "001";
	private final String SECOND_ID = "002";
	private final String VALID_NAME = "Name";
	private final String VALID_DESC = "Default description.";
	private final String UPDATED_NAME = "Updated Name";
	private final String UPDATED_DESC = "Updated description.";
	
	// Invalid test values
	private final String INVALID_ID = "999";
	private final String INVALID_NAME = "a".repeat(21);
	private final String INVALID_DESC = "a".repeat(51);
	
	private TaskService service;

	@BeforeEach
	public void createFreshService() {
		service = new TaskService();
	}

	private Task createTask(String taskID) {
		return new Task(taskID, VALID_NAME, VALID_DESC);
	}
	
	@Test
	public void testAddTask() {
		Task newTask = new Task(VALID_ID, VALID_NAME, VALID_DESC);
		
		service.addTask(newTask);
		assertEquals(VALID_ID, service.getTask(VALID_ID).getTaskID());
	}
	
	@Test
	public void testAddMultipleTasks() {		
		service.addTask(createTask(VALID_ID));
		service.addTask(createTask(SECOND_ID));

		assertEquals(VALID_ID, service.getTask(VALID_ID).getTaskID());
		assertEquals(SECOND_ID, service.getTask(SECOND_ID).getTaskID());
	}
	
	@Test
	public void testAddDuplicateID() {
		Task testTask = createTask(VALID_ID);
		Task duplicateTask = createTask(VALID_ID);
		
		service.addTask(testTask);
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask(duplicateTask);
		});	
	}
	
	@Test
	public void testAddNullTask() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.addTask(null);
		});
	}
	
	@Test
	public void testGetTask() {
		Task testTask = createTask(VALID_ID);
		service.addTask(testTask);
		assertEquals(testTask, service.getTask(VALID_ID));
	}
	
	@Test
	public void testGetInvalidTaskID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.getTask(INVALID_ID);
		});
	}
	
	@Test
	public void testDeleteTask() {
		Task testTask = createTask(VALID_ID);
		service.addTask(testTask);
		service.deleteTask(VALID_ID);
		
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask(VALID_ID);
		}); 	
	}
	
	@Test
	public void testDeleteInvalidTaskID() { 
		assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTask(INVALID_ID);
		});
	}
	
	@Test
	public void testUpdateName() {
		Task testTask = createTask(VALID_ID);
		service.addTask(testTask);
		
		service.updateName(VALID_ID,  UPDATED_NAME);
		assertEquals(UPDATED_NAME, service.getTask(VALID_ID).getName());
	}
	
	@Test
	public void testUpdateNameInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateName(INVALID_ID, UPDATED_NAME);
		});
	}
	
	@Test
	public void testUpdateNameInvalidValue() {
		service.addTask(createTask(VALID_ID));

		assertThrows(IllegalArgumentException.class, () -> {
			service.updateName(VALID_ID, INVALID_NAME);
		});
	}
	
	@Test
	public void testUpdateDescription() {
		Task testTask = createTask(VALID_ID);
		service.addTask(testTask);
		
		service.updateDescription(VALID_ID, UPDATED_DESC);
		assertEquals(UPDATED_DESC, service.getTask(VALID_ID).getDescription());
	}
	
	@Test
	public void testUpdateDescriptionInvalidID() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.updateDescription(INVALID_ID, VALID_DESC);
		});
	}
	
	@Test
	public void testUpdateDescriptionInvalidValue() {
		service.addTask(createTask(VALID_ID));

		assertThrows(IllegalArgumentException.class, () -> {
			service.updateDescription(VALID_ID, INVALID_DESC);
		});
	}
	
}
