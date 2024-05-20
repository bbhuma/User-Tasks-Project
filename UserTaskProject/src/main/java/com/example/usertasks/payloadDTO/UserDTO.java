package com.example.usertasks.payloadDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long userId;
    private String name;
    private String email;
    private String password;

    // Constructors, getters, and setters

}
