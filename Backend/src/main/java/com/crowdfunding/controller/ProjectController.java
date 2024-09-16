package com.crowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunding.dto.ProjectDTO;
import com.crowdfunding.service.ProjectService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
		ProjectDTO createdProject = projectService.createProject(projectDTO);
		return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long projectId,
			@Valid @RequestBody ProjectDTO projectDTO) {
		ProjectDTO updatedProject = projectService.updateProject(projectId, projectDTO);
		return ResponseEntity.ok(updatedProject);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
		projectService.deleteProject(projectId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId) {
		ProjectDTO projectDTO = projectService.getProjectById(projectId);
		return ResponseEntity.ok(projectDTO);
	}

	@GetMapping
	public ResponseEntity<List<ProjectDTO>> getAllProjects() {
		List<ProjectDTO> projects = projectService.getAllProjects();
		return ResponseEntity.ok(projects);
	}
}
