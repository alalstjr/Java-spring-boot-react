package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ProjectTask;
import com.example.demo.service.ProjectTaskService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addPTToBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result) {
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();
			
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask newPT = projectTaskService.saveOrUpdateProjectTask(projectTask);
		
		return new ResponseEntity<ProjectTask>(newPT, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<ProjectTask> getAllPTs() {
		return projectTaskService.findAll();
	}
	
	@GetMapping("/{pt_id}")
	public ResponseEntity<?> getPTById(@PathVariable Long pt_id) {
		ProjectTask projectTask = projectTaskService.findById(pt_id);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id) {
		projectTaskService.delete(pt_id);
		
		return new ResponseEntity<String>("Project Task delete", HttpStatus.OK);
	}
}
