package com.quedacoder.WorkTaskApplication.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.quedacoder.WorkTaskApplication.enums.Status;
import com.quedacoder.WorkTaskApplication.enums.TaskType;

@Entity
@Table(name="task")
@EntityListeners(AuditingEntityListener.class)
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private Long taskId;
	
	@Column(name="task_name")
	private String taskName;
	
	@Column(name="description")
	private String taskDescription;
	
	@Column(name="task_type")
	private TaskType taskType;
	
	@Column(name="start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(name="status")
	private Status status;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="ticket_number")
	private String ticketNumber;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
    private Date createdDate;
	
	@Column(name="created_by")
	@CreatedBy
    private String createdBy;
	
	@Column(name="changed_date")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedBy
    private Date changedDate;
	
	@Column(name="changed_by")
	@LastModifiedBy
    private String changedBy;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "project_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Project project;
	
	public Task() {}

	public Task(Long taskId, @NotNull String taskName, String taskDescription, @NotNull TaskType taskType,
			@NotNull Date startDate, @NotNull Status status, String comments, Date createdDate, String createdBy,
			Date changedDate, String changedBy, Project project, String ticketNumber) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskType = taskType;
		this.startDate = startDate;
		this.status = status;
		this.comments = comments;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.changedDate = changedDate;
		this.changedBy = changedBy;
		this.project = project;
		this.ticketNumber = ticketNumber;
	}

	/**
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @return the taskType
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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

	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskType=" + taskType + ", startDate=" + startDate + ", status=" + status + ", comments="
				+ comments + ", ticketNumber=" + ticketNumber + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", changedDate=" + changedDate + ", changedBy=" + changedBy + ", project=" + project
				+ "]";
	}

}
