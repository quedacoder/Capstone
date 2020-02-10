package com.quedacoder.WorkTaskApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void save(Project projectToSave) {
		
		projectRepository.save(projectToSave);
		
	}
	
}
