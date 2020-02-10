package com.quedacoder.WorkTaskApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quedacoder.WorkTaskApplication.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
