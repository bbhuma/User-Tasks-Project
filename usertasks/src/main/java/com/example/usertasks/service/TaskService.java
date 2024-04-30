package com.example.usertasks.service;

import java.util.List;

import com.example.usertasks.payloadDTO.TaskDTO;

public interface TaskService {
	 	List<TaskDTO> getAllTasks();
	    List<TaskDTO> getTasksByUserId(Long userId);
	    TaskDTO getTaskByIdAndUserId(Long taskId, Long userId);
	    TaskDTO createTask(Long userId, TaskDTO taskDTO);
	    TaskDTO updateTask(Long userId, Long taskId, TaskDTO taskDTO);
	    void deleteTask(Long userId, Long taskId);
		TaskDTO getTask(Long userId, Long taskId);
		List<TaskDTO> getAllTasksByUser(Long userId);
		TaskDTO assignTask(Long userId, TaskDTO taskDTO);
		void deleteAllTasksByUser(Long userId);

	/*
	 * public TaskDTO getTask(Long taskId) { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public List<TaskDTO> getAllTasksByUser(Long userId) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public TaskDTO assignTask(Long userId, TaskDTO taskDTO) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public void deleteAllTasksByUser(Long userId) { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * public void deleteTask(Long userId, Long taskId) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * public TaskDTO updateTask(Long userId, Long taskId, TaskDTO taskDTO) { //
	 * TODO Auto-generated method stub return null; }
	 * 
	 * public TaskDTO getTask(Long userId, Long taskId) { // TODO Auto-generated
	 * method stub return null; }
	 */

}
