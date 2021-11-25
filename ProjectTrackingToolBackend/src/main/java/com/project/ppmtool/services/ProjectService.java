package com.project.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ppmtool.domain.Project;
import com.project.ppmtool.exceptions.ProjectIdException;
import com.project.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			// to remove the ambiguity of different casing here.
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch (Exception ex) {
			throw new ProjectIdException("Project ID '"+project.getProjectIdentifier()+"' already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project ID '"+projectId+"' doesn't exists");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("Cannot delete Project with ID: '"+projectId+"'. This project doesn't exists");
		}
		projectRepository.delete(project);
	}
	
	/**
	 *  JPA figures out that if we are passing the Project with primary-key 'id' as an attribute which exists in DB
	 *  and Project object is valid. It by-default updates the entry in the DB, instead of inserting a new entry in it.
	 *  calling save() on an object with predefined id will update the corresponding database record rather than insert a new one.
	 *  It will persist or update based on the presence of the id (primary key).
	 */
	/**
	 * Caveat:
	 * what if we pass an entity with partial values ? Will the other values gets null in DB ? : YES
	 * So from react frontend we are never going to pass the null values for created_At and other arguments
	 */
	public Project updateProject(Project project) {
		return saveOrUpdateProject(project);
	}
}
