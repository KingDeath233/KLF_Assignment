package com.example.demo.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Email(message="Not a valid email format!")
	@NotEmpty(message="Email can not be empty!")
	private String fromAddress;
	
	@NotEmpty(message="Subject can not be empty!")
	private String subject;
	
	@NotEmpty(message="Content can not be empty!")
	private String content;
	
}
