package com.project.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.services.MapValidationErrorService;
import com.project.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	/**
	 * Spring official docs:
	 * 
	 * You can use @RequestBody in combination with @Valid from javax.validation api, which cause Standard 
	 * Bean Validation to be applied. 
	 * By default, validation errors cause a MethodArgumentNotValidException, which is turned 
	 * into a 400 (BAD_REQUEST) response. 
	 * Alternatively, you can handle validation errors locally within the controller through an Errors 
	 * or BindingResult argument.
	 */
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> entity = mapValidationErrorService.mapValidationError(result);
		if(entity!=null) return entity;
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<>(project1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
		Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProject() {
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
		projectService.deleteProjectByIdentifier(projectId.toUpperCase());
		return new ResponseEntity<>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> entity = mapValidationErrorService.mapValidationError(result);
		if(entity!=null) return entity;
		
	    Project updatedProject = projectService.updateProject(project);
	    return new ResponseEntity<>(updatedProject, HttpStatus.ACCEPTED);
	}
	
}
