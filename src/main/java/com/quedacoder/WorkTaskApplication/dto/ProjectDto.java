package com.quedacoder.WorkTaskApplication.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.quedacoder.WorkTaskApplication.enums.ProcessArea;

public class ProjectDto {
	
	private Long id;
	
	@NotEmpty(message="Project Name can not be empty!!!")
	private String projectName;
	
	@NotEmpty(message="Please enter a description for the Project!!!")
	private String description;
	
	@NotNull(message="Project must be assigned to a Process Area!!!")
	private ProcessArea processArea;
	
	@NotEmpty(message="Please enter the name of the Project Owner!!!")
	private String projectOwner;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the processArea
	 */
	public ProcessArea getProcessArea() {
		return processArea;
	}

	/**
	 * @param processArea the processArea to set
	 */
	public void setProcessArea(ProcessArea processArea) {
		this.processArea = processArea;
	}

	/**
	 * @return the projectOwner
	 */
	public String getProjectOwner() {
		return projectOwner;
	}

	/**
	 * @param projectOwner the projectOwner to set
	 */
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
}
