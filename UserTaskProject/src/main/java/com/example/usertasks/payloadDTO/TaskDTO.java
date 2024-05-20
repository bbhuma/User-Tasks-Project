package com.example.usertasks.payloadDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	private Long taskId;
    private String taskName;
   // private Long userId; // User ID to which task is assigned
	public Long getId() {
		return taskId;
	}
	public void setId(Long id) {
		this.taskId = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
