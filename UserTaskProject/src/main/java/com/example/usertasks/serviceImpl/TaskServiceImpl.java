package com.example.usertasks.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usertasks.entity.Task;
import com.example.usertasks.entity.User;
import com.example.usertasks.exception.ResourceNotFoundException;
import com.example.usertasks.payloadDTO.TaskDTO;
import com.example.usertasks.repository.TaskRepository;
import com.example.usertasks.repository.UserRepository;
import com.example.usertasks.service.TaskService;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskByIdAndUserId(Long taskId, Long userId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId);
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO createTask(Long userId, TaskDTO taskDTO) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setUser(userRepository.getOne(userId));
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(Long userId, Long taskId, TaskDTO taskDTO) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        Task task = taskRepository.findByIdAndUserId(taskId, userId);
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        modelMapper.map(taskDTO, task);
        task.setUser(userRepository.getOne(userId));
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        Task task = taskRepository.findByIdAndUserId(taskId, userId);
//        	    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        taskRepository.delete(task);
    }

    @Override
    public TaskDTO getTask(Long userId, Long taskId) {
        Task task = taskRepository.findByIdAndUserId(taskId,userId);
        return (task != null) ? modelMapper.map(task, TaskDTO.class) : null;
    }

    @Override
    public List<TaskDTO> getAllTasksByUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO assignTask(Long userId, TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        User user = new User(); // Assuming you have a way to fetch the user object by userId
        user.setId(userId); // Set the ID of the user
        task.setUser(user); // Set the user in the task
        task = taskRepository.save(task);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public void deleteAllTasksByUser(Long userId) {
        taskRepository.deleteByUserId(userId);
    }
}
