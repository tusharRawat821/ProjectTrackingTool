package com.project.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	/**
	 * Jpa writes the SQL query based on the method name at runtime. 
	 * @param projectIdentifier
	 * @return
	 */
	public Project findByProjectIdentifier(String projectId);
	
	@Override
	Iterable<Project> findAll();
}

