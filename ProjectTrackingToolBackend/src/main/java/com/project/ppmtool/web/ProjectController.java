package com.project.ppmtool.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
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
		
		if(result.hasErrors()) {
			/**
			 * {"field" : "errorMsg",..} : send error in this format to our React front-end.
			 * 
			 */
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<>(project1, HttpStatus.CREATED);
	}
	
}
