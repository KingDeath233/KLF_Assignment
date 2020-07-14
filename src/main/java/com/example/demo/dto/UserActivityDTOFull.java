package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityDTOFull {
	
	private String user_name;
	private String activity_name;
	private long amount;
	private java.util.Date first_occurrence;
	private java.util.Date last_occurrence;

}
