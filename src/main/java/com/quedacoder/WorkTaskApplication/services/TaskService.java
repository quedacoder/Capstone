package com.quedacoder.WorkTaskApplication.services;

import java.util.List;

import com.quedacoder.WorkTaskApplication.dto.TaskDto;
import com.quedacoder.WorkTaskApplication.entities.Task;

public interface TaskService {
	
	public List<Task> findByProjectId(Long id);
	public Task findByProjectName(String taskName);
	public Task findById(Long id);
	public void deleteById(TaskDto taskToDelete);
	public Task saveOrUpdateProject(TaskDto TaskDto);
	public Long findProjectIdByTaskId(Long taskId);
}
