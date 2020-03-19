package com.quedacoder.WorkTaskApplication.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quedacoder.WorkTaskApplication.dto.TaskDto;
import com.quedacoder.WorkTaskApplication.entities.Task;
import com.quedacoder.WorkTaskApplication.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task findByProjectName(String taskName) {
		
		return taskRepository.findByTaskName(taskName);
	}

	@Override
	public Task findById(Long id) {
		
		// create empty task object
		Task task = null;
		
		// find the task in the database
		Optional<Task> optionalTask = taskRepository.findById(id);
		
		// check if the task exist
		if (optionalTask.isPresent()) {
			task = optionalTask.get();
		}
		
		// return task
		return task;
		
	}

	@Override
	public void deleteById(TaskDto taskToDelete) {
		taskRepository.deleteById(taskToDelete.getTaskId());
	}

	@Override
	public Task saveOrUpdateProject(TaskDto taskDto) {
		
		// Create new instance of Task
		Task task = new Task();
		Date date = new Date(System.currentTimeMillis());
		
		// check if update or create
		if (taskDto.getTaskId() != null) {	
			// this is an update
			task.setTaskId(taskDto.getTaskId());
		}
		
		task.setTaskName(taskDto.getTaskName());
		task.setTaskDescription(taskDto.getTaskDescription());
		task.setTaskType(taskDto.getTaskType());
		task.setStartDate(taskDto.getStartDate());
		task.setStatus(taskDto.getStatus());
		task.setComments(taskDto.getComments());
		task.setTicketNumber(taskDto.getTicketNumber());
		task.setProject(taskDto.getProject());
		
		// save entity
		return taskRepository.save(task);
	}

	@Override
	public List<Task> findByProjectId(Long id) {
		
		return taskRepository.findByProjectId(id);
	}

	@Override
	public Long findProjectIdByTaskId(Long taskId) {
		return taskRepository.findProjectIdByTaskId(taskId);
	}
}
