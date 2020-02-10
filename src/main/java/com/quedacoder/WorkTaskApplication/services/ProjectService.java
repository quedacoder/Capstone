package com.quedacoder.WorkTaskApplication.services;

import java.util.List;

import com.quedacoder.WorkTaskApplication.entities.Project;

public interface ProjectService {
	
	public List<Project> findAll();
	public void save(Project projectToSave);

}
