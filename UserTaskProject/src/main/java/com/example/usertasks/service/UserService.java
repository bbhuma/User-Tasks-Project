package com.example.usertasks.service;

import java.util.List;

import com.example.usertasks.payloadDTO.UserDTO;

public interface UserService {

	 	List<UserDTO> getAllUsers();
	    UserDTO getUserById(Long userId);
	    UserDTO createUser(UserDTO userDTO);
	    UserDTO updateUser(Long userId, UserDTO userDTO);
	    void deleteUser(Long userId);
		UserDTO loginUser(String email, String password);
    
	/* 
	 * These  are the methods if you defined servces as a calss, instead of interface.
	 * For interface no need of defining the method body. 
	 * public List<UserDTO> getAllUsers() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public UserDTO createUser(UserDTO userDTO) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * public UserDTO loginUser(String email, String password) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public UserDTO updateUser(Long userId, UserDTO userDTO) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public void deleteUser(Long userId) { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
