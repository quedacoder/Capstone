package com.quedacoder.WorkTaskApplication.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quedacoder.WorkTaskApplication.dto.ProjectDto;
import com.quedacoder.WorkTaskApplication.entities.Project;
import com.quedacoder.WorkTaskApplication.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project findByProjectName(String projectName) {
		return projectRepository.findByProjectName(projectName);
	}

	@Override
	public Project findById(Long id) {
		
		Optional<Project> optionalProject = projectRepository.findById(id);
		Project project = null;
		
		if (optionalProject.isPresent()) {
			project = optionalProject.get();
		}
		
		return project;
	}

	@Override
	public void deleteById(ProjectDto projectToDelete) {		
		projectRepository.deleteById(projectToDelete.getId());		
	}

	@Override
	public Project saveOrUpdateProject(ProjectDto projectDto) {
		
		// Create a new instanace of the project 
		Project project = new Project();	
		Date date = new Date(System.currentTimeMillis());
		
		// null id means this is an update
		if (projectDto.getId() != null ) {
			
			// this is an update
			project.setProjectId(projectDto.getId());
			project.setChangedBy(projectDto.getCreatedBy());
			project.setChangedDate(date);
			project.setCreatedBy(projectDto.getCreatedBy());
			project.setCreatedDate(date);
			
		} else {
			
			// only want to set on create
			project.setCreatedBy(projectDto.getCreatedBy());
			project.setCreatedDate(date);
			project.setChangedBy(projectDto.getChangedBy());
			project.setChangedDate(date);
			
		}
		
		project.setProjectName(projectDto.getProjectName());
		project.setProjectOwner(projectDto.getProjectOwner());
		project.setDescription(projectDto.getDescription());
		project.setProcessArea(projectDto.getProcessArea());
		
		// Save the Project
		return projectRepository.save(project);
	}

	@Override
	public Project findByProjectNameAndId(String projectName, Long projectId) {
		
		Optional<Project> optionalProject = projectRepository.findByProjectNameAndProjectId(projectName, projectId);
		Project project = null;
		
		if (optionalProject.isPresent()) {
			project = optionalProject.get();
		}
		
		return project;
	}
}
