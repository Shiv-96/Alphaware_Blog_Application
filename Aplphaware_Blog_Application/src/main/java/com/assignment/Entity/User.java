package com.assignment.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotBlank(message = "Name can't be blank")
	@NotNull(message = "Name can't be null")
	private String name;
	
	@Email(message = "email is not in format")
	@Column(unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Password can't be blank")
	@NotNull(message = "Password can't be null")
	private String password;
	
	@NotBlank(message = "Address can't be blank")
	@NotNull(message = "Address can't be null")
	private String address;
	
}
