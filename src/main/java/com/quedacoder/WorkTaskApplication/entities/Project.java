package com.quedacoder.WorkTaskApplication.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.quedacoder.WorkTaskApplication.enums.ProcessArea;

@Entity
@Table(name="project")
@EntityListeners(AuditingEntityListener.class)
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	
	@Column(name="project_name")
	@NotNull
	@Size(min=10, max=60)
	private String projectName;
	
	@NotNull
	@Size(min=15, max=60)
	@Column(name="description")
	private String description;
	
	@Column(name="process_area")
	@NotNull
	@Size(min=10, max=55)
	private ProcessArea processArea;
	
	@Column(name="project_owner")
	@NotNull
	@Size(min=5, max=45)
	private String projectOwner;
	
	@Column(name="created_date", nullable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;
	
	@Column(name="created_by")
	@CreatedBy
	private String createdBy;
	
	@Column(name="changed_date", nullable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date changedDate;
	
	@Column(name="changed_by")
	@LastModifiedBy
	private String changedBy;
	
	public Project() {
	}

	public Project(Long projectId, String projectName, String description, ProcessArea processArea, String projectOwner) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.processArea = processArea;
		this.projectOwner = projectOwner;
	}

	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the changedDate
	 */
	public Date getChangedDate() {
		return changedDate;
	}

	/**
	 * @param changedDate the changedDate to set
	 */
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	/**
	 * @return the changedBy
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * @param changedBy the changedBy to set
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", description=" + description
				+ ", processArea=" + processArea.getProcessArea() + ", projectOwner=" + projectOwner + "]";
	}
}
