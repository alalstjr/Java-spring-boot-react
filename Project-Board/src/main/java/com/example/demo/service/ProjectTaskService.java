package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ProjectTask;
import com.example.demo.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
		
		if(projectTask.getStatus() == null || projectTask.getStatus() == "") 
		{
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepository.save(projectTask);
	}
	
	// 모든 데이터를 찾는다.
	public Iterable<ProjectTask> findAll() {
		return projectTaskRepository.findAll();
	}
	
	// 특정 ID 값을 찾는다.
	public ProjectTask findById(Long id) {
		return projectTaskRepository.getById(id);
	}
	
	// 특정 ID 값을 지운다.
	public void delete(Long id) {
		ProjectTask projectTask = findById(id);
		projectTaskRepository.delete(projectTask);
	}
}
