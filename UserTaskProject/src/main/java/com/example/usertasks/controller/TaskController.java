package com.example.usertasks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usertasks.entity.Task;
import com.example.usertasks.entity.User;
import com.example.usertasks.exception.ResourceNotFoundException;
import com.example.usertasks.payloadDTO.TaskDTO;
import com.example.usertasks.repository.TaskRepository;
import com.example.usertasks.repository.UserRepository;

@RestController
@RequestMapping("/api/users/{user_id}/tasks")
public class TaskController {
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserRepository userRepository;

//	@Autowired, you can not autowire an entity, it is not managed by spring, it is managed by JPA. 
//	Task task;

	@PostMapping
	public ResponseEntity<TaskDTO> assignTask(@RequestBody TaskDTO taskDTO, @PathVariable Long user_id) {

		// Hanlde exceptions of no
		if (!userRepository.existsById(user_id)) {
			throw new ResourceNotFoundException(
					"User alaready exists with same id and can not be duplicated  with id " + user_id);
		}
//	    Task task = new Task();
		// Convert DTO to entity
		Task task = modelMapper.map(taskDTO, Task.class);
		task.setUser(userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + user_id)));

		// Save the task
		Task newTask = taskRepository.save(task);

		// Convert entity back to DTO
		TaskDTO newDTO = modelMapper.map(newTask, TaskDTO.class);

		return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<TaskDTO>> getAllTask(@PathVariable Long user_id) {

		// When you use ok(), you dont need to pass status code, ok already have 200 as
		// build in.
		List<Task> tasks = taskRepository.findByUserId(user_id);

		// Dont forget to collect the tasks into a new collection called newTasks.
		List<TaskDTO> newTasks = tasks.stream().map(task -> modelMapper.map(task, TaskDTO.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(newTasks);
	}

	// Id is the id of Task and user_id os the Id name of the User, it does not
	// matter what names is given to User Id.
	@GetMapping("/{task_id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long user_id, @PathVariable Long task_id) {

		// handle the exception for no valid task found, with id
		if (!userRepository.existsById(user_id)) {
			throw new ResourceNotFoundException("User not found with id " + user_id);
		}
		/*
		 * Task task = taskRepository.findByIdAndUserId(taskId, userId) .orElseThrow(()
		 * -> new ResourceNotFoundException("Task not found for this id and user"));
		 * taskRepository.delete(task);
		 */
		
		// else return the Res Entity with the right task found.
		return ResponseEntity.ok(modelMapper.map(taskRepository.findByIdAndUserId(task_id, user_id), TaskDTO.class));
	}

	@PutMapping("/{task_id}")
	public ResponseEntity<TaskDTO> updateTaskById(@PathVariable Long user_id, @PathVariable Long task_id,
			@RequestBody TaskDTO taskDTO) {
		// handle the exception for no valid task found, with id
		if (!userRepository.existsById(user_id)) {
			throw new ResourceNotFoundException("User not found with id " + user_id);
		}

		
		/*
		 * Task task = taskRepository.findByIdAndUserId(task_id, user_id)
		 * .orElseThrow(() -> new
		 * ResourceNotFoundException("Task not found for this id and user"));
		 * taskRepository.delete(task);
		 */
		 
		User user = userRepository.findById(user_id).get();
		Task task = new Task();
		task.setId(taskDTO.getId());
		task.setTaskName(taskDTO.getTaskName());
		task.setUser(user);
		taskRepository.save(task);

		return ResponseEntity.ok(modelMapper.map(task, TaskDTO.class));
	}

	@DeleteMapping("/{task_id}")
	public ResponseEntity<Void> deleteTaskById(@PathVariable Long user_id, @PathVariable Long task_id) {

		  // handle the exception for no valid task found, with id
		  if (!userRepository.existsById(user_id)) { throw new
		  ResourceNotFoundException("User not found with id " + user_id); }
		  taskRepository.deleteById(task_id);
		 
		/*
		 * Task task = taskRepository.findByIdAndUserId (task_id, user_id)
		 * .orElseThrow(() -> new
		 * ResourceNotFoundException("Task not found for this id and user"));
		 * taskRepository.delete(task);
		 */
		 

		return ResponseEntity.noContent().build();
	}

}
