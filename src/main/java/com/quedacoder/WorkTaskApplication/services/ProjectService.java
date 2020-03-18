package com.quedacoder.WorkTaskApplication.services;

import java.util.List;

import com.quedacoder.WorkTaskApplication.dto.ProjectDto;
import com.quedacoder.WorkTaskApplication.entities.Project;

public interface ProjectService {
	
	public List<Project> findAll();
	public Project findByProjectName(String projectName);
	public Project findById(Long id);
	public void deleteById(ProjectDto projectToDelete);
	public Project saveOrUpdateProject(ProjectDto projectDto);
	public Project findByProjectNameAndId(String projectName, Long projectId);

}
