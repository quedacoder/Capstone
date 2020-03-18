package com.quedacoder.WorkTaskApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quedacoder.WorkTaskApplication.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	Task findByTaskName(String taskName);
	
	@Query(value = "SELECT * FROM TASK WHERE PROJECT_ID = ?1", nativeQuery = true)
	List<Task> findByProjectId(Long id);
	
	@Query(value = "SELECT project_id FROM TASK WHERE TASK_ID = ?1", nativeQuery = true)
	Long findProjectIdByTaskId(Long taskId);

}
