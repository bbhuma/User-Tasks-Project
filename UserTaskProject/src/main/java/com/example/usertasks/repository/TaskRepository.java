package com.example.usertasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.usertasks.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	// Object findByIdAndUserId(Long taskId, Long userId);
	// You can add custom query methods here if needed

	List<Task> findByUserId(Long userId);

	Task findByIdAndUserId(Long taskId, Long userId);

	void deleteByUserId(Long userId);

}