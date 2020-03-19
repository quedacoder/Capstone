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

import com.quedacoder.WorkTaskApplication.dto.ProjectDto;
import com.quedacoder.WorkTaskApplication.entities.Project;
import com.quedacoder.WorkTaskApplication.services.ProjectService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ModelAttribute("project")
	public ProjectDto projectDto() {
		return new ProjectDto();
	}

	@GetMapping("/projects")
	public String getProjectsPage(Model model) {
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects", projects);
		return "projects";
	}

	@GetMapping("/create-project")
	public String getCreateProjectForm(Model model) {

		// Get the authenticated user to set createdBy field in UI
		String authenticatedUser = System.getProperty("user.name");
		
		// create empty shell of project
		Project project = new Project();

		model.addAttribute("authenticatedUser", authenticatedUser);
		model.addAttribute("project", project);
		return "create-project";
	}

	@PostMapping("/create-project")
	public String saveProject(@RequestParam("action") String action,
			@ModelAttribute("project") @Valid ProjectDto projectDto, BindingResult result) {
		
		// redirect to project list if cancel action
		if (action.equalsIgnoreCase("cancel")) {
			return "redirect:/projects";
		}
		
		// Check to see if the project being created alreay exist
		Project project = projectService.findByProjectName(projectDto.getProjectName());

		if (project != null) {
			result.rejectValue("projectName", null, "There is already a project created with this project name");
			return "/create-project";
		}

		// Check for validation errors
		if (result.hasErrors()) {
			return "/create-project";
		}

		// Set createdBy with authenticated user
		String authenticatedUser = System.getProperty("user.name");
		//projectDto.setCreatedBy(authenticatedUser);

		// Save the project
		project = projectService.saveOrUpdateProject(projectDto);
		return "redirect:/projects";
	}

	@GetMapping("/project/edit/{id}")
	public String getProjectEditForm(Model model, @PathVariable("id") Long id) {

		// Get the project the user wants to edit
		Project project = projectService.findById(id);

		if (project == null) {
			return "redirect:/projects";
		}

		String authenticatedUser = System.getProperty("user.name");
		model.addAttribute("project", project);
		model.addAttribute("authenticatedUser", authenticatedUser);

		return "project-edit";
	}

	@PostMapping("/project/edit/{id}")
	public String saveEditedProject(@RequestParam("action") String action, @PathVariable("id") Long id,
			@ModelAttribute("project") @Valid ProjectDto projectDto, BindingResult result) {

		// redirect to project list if cancel action
		if (action.equalsIgnoreCase("cancel")) {
			return "redirect:/projects";
		}

		// find the project to update
		Project project = projectService.findById(id);

		if (project == null) {
			result.rejectValue("projectName", null, "Project not found.");
		}

		// return to edit form
		if (result.hasErrors()) {
			return "/project-edit";
		}

		projectDto.setId(id);

		// save the changes
		project = projectService.saveOrUpdateProject(projectDto);
		return "redirect:/projects";
	}

	@GetMapping("/project/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, ProjectDto projectDto) {

		projectService.deleteById(projectDto);

		return "redirect:/projects";
	}
}
