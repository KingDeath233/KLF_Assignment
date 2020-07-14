package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	@Id
	private int id;
	
	@Column(name="name")
	private String name;
}
