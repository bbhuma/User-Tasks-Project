package com.example.usertasks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.usertasks.entity.Task;
import com.example.usertasks.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	// Object findByIdAndUserId(Long taskId, Long userId);
	Optional<Task> findById(Long id);
	List<Task> findByTaskName(String taskName);
	List<Task> findByUser(User user);
	List<Task> findByUserId(Long user_id);
	Task findByIdAndUserId(Long id, Long user_id);
	
	

	

}