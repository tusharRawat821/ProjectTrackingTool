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

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Project name is required")
	private String projectName;
	
	// like a JIRA feature which gives us an identifier name based on projectName.
	@NotBlank(message = "Project Identifier is required")
	@Size(min = 4, max = 5) 
	@Column(unique = true, updatable = false)
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
	
	public Project() {
	}
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalDateTime getCreated_At() {
		return created_At;
	}

	public void setCreated_At(LocalDateTime created_At) {
		this.created_At = created_At;
	}

	public LocalDateTime getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(LocalDateTime updated_At) {
		this.updated_At = updated_At;
	}

}
