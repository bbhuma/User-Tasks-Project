package com.example.usertasks.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usertasks.payloadDTO.TaskDTO;
import com.example.usertasks.payloadDTO.UserDTO;
import com.example.usertasks.service.TaskService;
import com.example.usertasks.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private UserService userService;
	
	@Autowired 
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    private TaskService taskService;
    
    

    // Implement UserController endpoints
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
         //return new ResponseEntity<UserDTO>(userService.createUser(createdUser),HttpStatus.CREATED)
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestParam String email, @RequestParam String password) {
        UserDTO loggedInUser = userService.loginUser(email, password);
        return ResponseEntity.ok(loggedInUser);
    }

	/*
	 * @PostMapping("/{userId}/tasks") public ResponseEntity<TaskDTO>
	 * assignTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) { TaskDTO
	 * assignedTask = taskService.assignTask(userId, taskDTO); return
	 * ResponseEntity.ok(assignedTask); }
	 */

	/*
	 * @GetMapping("/{userId}/tasks") public ResponseEntity<List<TaskDTO>>
	 * getAllTasksByUser(@PathVariable Long userId) { List<TaskDTO> tasks =
	 * taskService.getAllTasksByUser(userId); return ResponseEntity.ok(tasks); }
	 */

    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long userId, @PathVariable Long taskId) {
        TaskDTO task = taskService.getTask(userId, taskId);
        return ResponseEntity.ok(task);
    }

	/*
	 * @PutMapping("/{userId}/tasks/{taskId}") public ResponseEntity<TaskDTO>
	 * updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody
	 * TaskDTO taskDTO) { TaskDTO updatedTask = taskService.updateTask(userId,
	 * taskId, taskDTO); return ResponseEntity.ok(updatedTask); }
	 */

	/*
	 * @DeleteMapping("/{userId}/tasks/{taskId}") public ResponseEntity<Void>
	 * deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
	 * taskService.deleteTask(userId, taskId); return
	 * ResponseEntity.noContent().build(); }
	 */

    @DeleteMapping("/{userId}/tasks")
    public ResponseEntity<Void> deleteAllTasksByUser(@PathVariable Long userId) {
        taskService.deleteAllTasksByUser(userId);
        return ResponseEntity.noContent().build();
    }
}