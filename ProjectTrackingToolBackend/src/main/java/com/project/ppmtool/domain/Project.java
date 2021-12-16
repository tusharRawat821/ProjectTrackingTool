package com.project.ppmtool.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Project name is required")
	private String projectName;
	
	// like a JIRA feature which gives us an identifier name based on projectName.
	@NotBlank(message = "Project Identifier is required")
	@Size(min = 4, max = 5)
	@Column(unique = true, updatable = false) // if we try to update the projectIdentifier in : update query it won't update.
	private String projectIdentifier; // Why is this attribute required?
	
	@NotBlank(message = "Project description is required")
	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd") // test this 
	private LocalDate start_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate end_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime created_At;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updated_At;
	
	// JPA annotations : @PrePersist and @PreUpdate
	// callback method when the new object(entity) is inserted (persisted) into the
	// DB.
	@PrePersist
	protected void onCreate() {
		this.created_At = LocalDateTime.now();
	}

	// callback method when the old object(entity) is updated into the DB.
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = LocalDateTime.now();
	}

}
