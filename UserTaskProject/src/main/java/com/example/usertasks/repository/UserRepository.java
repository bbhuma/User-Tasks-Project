package com.example.usertasks.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usertasks.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 // You can add custom query methods here if needed
	List<User> findAll();
	Optional<User> findById(Long id);
	List<User> findByEmail(String email);
	List<User> findByName(String name);	
}
