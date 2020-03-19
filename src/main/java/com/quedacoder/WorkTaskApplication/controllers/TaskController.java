package com.quedacoder.WorkTaskApplication.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quedacoder.WorkTaskApplication.dto.TaskDto;
import com.quedacoder.WorkTaskApplication.entities.Project;
import com.quedacoder.WorkTaskApplication.entities.Task;
import com.quedacoder.WorkTaskApplication.services.ProjectService;
import com.quedacoder.WorkTaskApplication.services.TaskService;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;
	
	@ModelAttribute("task")
	public TaskDto taskDto() {
		return new TaskDto();
	}
	
	@GetMapping("/project/{id}/tasks")
	public String getTask(Model model, @PathVariable("id") Long id) {
		
		List<Task> tasks = taskService.findByProjectId(id);
		model.addAttribute("tasks", tasks);
		model.addAttribute("project_id", id);
		return "tasks";
	}
	
	@GetMapping("/project/{id}/create-task")
	public String getTaskCreateForm(Model model, @PathVariable("id") Long id) {
		
		// Create a new shell of TaskDto
		TaskDto newTask = new TaskDto();
		
		String authenticatedUser = System.getProperty("user.name");;
		model.addAttribute("authenticatedUser", authenticatedUser);
		model.addAttribute("task", newTask);
		model.addAttribute("project_id", id);
		
		return "create-task";
		
	}
	
	@PostMapping("/project/{id}/create-task")
	public String saveTask(@RequestParam("action") String action, @PathVariable("id") Long projectId, @ModelAttribute("task") 
	        @Valid TaskDto taskDto, BindingResult result, Model model) {
		
		// redirect to task list
		if (action.equalsIgnoreCase("cancel")) {
			model.addAttribute("id", projectId);
			return "redirect:/project/" + projectId + "/tasks";
		}
		
		if (result.hasErrors()) {
			model.addAttribute("project_id", projectId);
			return "create-task";
		}
		
		// check to see if the project exist
		Project project = projectService.findById(projectId);
		
		if (project == null) {
			result.rejectValue("projectName", null, "There is already a project created with this project name");
			return "redirect:/projects";
		}
		
		// check to see if the task exist by name
		Task task = taskService.findByProjectName(taskDto.getTaskName());
		
		if (task != null) {
			// throw error return to task form
			result.rejectValue("taskName", null, "There is already a task created with the name " + taskDto.getTaskName());
			model.addAttribute("project_id", projectId);
			return "create-task";
		}
		
		// set the project in the task
		taskDto.setProject(project);
		
		// save the entity
		task = taskService.saveOrUpdateProject(taskDto);
		
		return "redirect:/project/" + task.getProject().getProjectId() + "/tasks";
		
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/project/task/edit/{task_id}")
	public String getTaskEditForm(Model model, @PathVariable("task_id") Long task_id) {
		
		// get the task user selected to edit
		Task editTask = taskService.findById(task_id);
		Project project = editTask.getProject();
		
		// check if the task is null
		if (editTask == null) {
			return "redirect:/project/task/edit/" + task_id;
		}
		
		// add objects to the model
		String authenticatedUser = System.getProperty("user.name");;
		model.addAttribute("authenticatedUser", authenticatedUser);
		model.addAttribute("task", editTask);
		model.addAttribute("project", project);
		
		return "task-edit";
		
	}
	
	@PostMapping("/project/task/edit/{task_id}")
	public String saveEditedTask(@RequestParam("action") String action, @PathVariable("task_id") Long task_id,
					@ModelAttribute("task") @Valid TaskDto taskDto, BindingResult result, Model model) {
		
		// Get the project id
		Long project_id = taskService.findProjectIdByTaskId(task_id);
		
		// redirect to task list if cancel or to edit form object has errors
		if (action.equalsIgnoreCase("cancel")) {
			return "redirect:/project/" + project_id + "/tasks";
		}
		
		if (result.hasErrors()) {
			model.addAttribute("task_id", task_id);
			return "task-edit";
		}
		
		// get the task object from the database
		Task taskToUpdate = taskService.findById(taskDto.getTaskId());
		
		if (taskToUpdate == null) {
			// throw error return to task form
			result.rejectValue("taskName", null, "Task: " + taskDto.getTaskName() + ". Could not be found!");
			return "task-edit";
		}
		
		taskDto.setProject(taskToUpdate.getProject());
		taskToUpdate = taskService.saveOrUpdateProject(taskDto);
		
		return "redirect:/project/" + project_id + "/tasks";
	}
	
	@GetMapping("/project/task/delete/{task_id}")
	public String deleteTask(@PathVariable("task_id") Long task_id) {
		
		// Get the project id
		Task task = taskService.findById(task_id);
		TaskDto taskDto = new TaskDto();
		taskDto.setTaskId(task.getTaskId());
		taskDto.setTaskName(task.getTaskName());
		taskDto.setTaskDescription(task.getTaskDescription());
		taskDto.setTaskType(task.getTaskType());
		taskDto.setStartDate(task.getStartDate());
		taskDto.setStatus(task.getStatus());
		taskDto.setComments(task.getComments());
		taskDto.setTicketNumber(task.getTicketNumber());
		
		Long project_id = task.getProject().getProjectId();
		
		// delete the task
		taskService.deleteById(taskDto);
		
		return "redirect:/project/" + project_id + "/tasks";
		
	}
}
