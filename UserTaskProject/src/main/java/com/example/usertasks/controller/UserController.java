package com.example.usertasks.controller;

import java.util.List;

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

import com.example.usertasks.entity.User;
import com.example.usertasks.exception.ResourceNotFoundException;
import com.example.usertasks.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<User> getUserByuser_id(@PathVariable Long user_id) {
		if(!userRepository.existsById(user_id)) throw new ResourceNotFoundException("User not found with user_id"+ user_id);
		return ResponseEntity.ok(userRepository.findById(user_id).get());
	}

	@PutMapping("/{user_id}")
	public ResponseEntity<User> updateUser(@PathVariable Long user_id, @RequestBody User user) {
		if(!userRepository.existsById(user_id)) throw new ResourceNotFoundException("User not found with user_id: " + user_id);	
		User savedUser = modelMapper.map(user, User.class);
		return  ResponseEntity.ok(userRepository.save(savedUser));
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long user_id, @RequestBody User user) {
		if (!userRepository.existsById(user_id)) {
            throw new ResourceNotFoundException("User not found with user_id: " + user_id);
        }
        userRepository.deleteById(user_id);
        return ResponseEntity.noContent().build();
	}
}
