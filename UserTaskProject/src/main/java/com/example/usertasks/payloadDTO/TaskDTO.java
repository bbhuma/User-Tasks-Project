package com.example.usertasks.payloadDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	private Long id;
    private String taskName;
    private Long userId; // User ID to which task is assigned

}
