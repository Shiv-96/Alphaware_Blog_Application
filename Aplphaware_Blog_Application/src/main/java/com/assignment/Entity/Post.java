package com.assignment.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postID;
	
	@NotBlank(message = "Title can't be blank")
	@NotNull(message = "Title can't be null")
	private String title;
	
	@NotBlank(message = "Content can't be blank")
	@NotNull(message = "Content can't be null")
	private String content;
	
	@FutureOrPresent(message = "Created time should not be in past")
	private LocalDateTime createdAt;
	
	@FutureOrPresent(message = "Updated time should not be in past")
	private LocalDateTime updatedAt;
	
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	
	
}
