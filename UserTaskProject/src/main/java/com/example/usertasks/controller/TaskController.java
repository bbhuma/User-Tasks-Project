package com.example.usertasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usertasks.payloadDTO.TaskDTO;
import com.example.usertasks.service.TaskService;

@RestController
@RequestMapping("/api/users/{userId}/tasks")
public class TaskController {
	@Autowired
    private  TaskService taskService;

    // Handler method to assign a new task to a user
    @PostMapping
    public ResponseEntity<TaskDTO> assignTask(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        TaskDTO assignedTask = taskService.assignTask(userId, taskDTO);
        return ResponseEntity.ok(assignedTask);
    }

    // Handler method to retrieve all tasks of a user
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasksByUser(@PathVariable Long userId) {
        List<TaskDTO> tasks = taskService.getAllTasksByUser(userId);
        return ResponseEntity.ok(tasks);
    }

    // Handler method to retrieve a specific task by ID
	/*
	 * @GetMapping("/{taskId}") public ResponseEntity<TaskDTO>
	 * getTaskById(@PathVariable Long userId, @PathVariable Long taskId) { TaskDTO
	 * task = taskService.getTask(userId, taskId); return ResponseEntity.ok(task); }
	 */

    // Handler method to update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.updateTask(userId, taskId, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    // Handler method to delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTask(userId, taskId);
        return ResponseEntity.noContent().build();
    }
}
