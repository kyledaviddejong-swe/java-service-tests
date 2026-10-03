/*
 * Author: Kyle DeJong
 * Date: May 31, 2026
 */

import java.util.HashMap;

public class TaskService {
	private HashMap<String, Task> tasks; 
	
	public TaskService() {
		tasks = new HashMap<String, Task>(); 
	}
	
	public void addTask(Task newTask) {
		if(newTask == null || tasks.containsKey(newTask.getTaskID())) { 
			throw new IllegalArgumentException("Invalid or duplicate task ID"); 
		}
		else {
			tasks.put(newTask.getTaskID(), newTask); 
		}
	}
	
	public void deleteTask(String taskID) {
		if(tasks.remove(taskID) == null) {
			throw new IllegalArgumentException("Invalid task ID");
		}
	}
	
	public Task getTask(String taskID) {
		if(!tasks.containsKey(taskID)) {
			throw new IllegalArgumentException("Task ID not found");
		}
		else {
			return tasks.get(taskID);
		}
	}
	
	public void updateName(String taskID, String name) {
		getTask(taskID).setName(name);
	}
	
	public void updateDescription(String taskID, String description) {
		getTask(taskID).setDescription(description);
	}
	
}
