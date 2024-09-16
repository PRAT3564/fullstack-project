package com.crowdfunding.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.dto.ProjectDTO;
import com.crowdfunding.entity.Project;
import com.crowdfunding.exception.ResourceNotFoundException;
import com.crowdfunding.repo.ProjectRepository;
import com.crowdfunding.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		Project project = convertToEntity(projectDTO);
		Project savedProject = projectRepository.save(project);
		return convertToDto(savedProject);
	}

	@Override
	public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setGoalAmount(projectDTO.getGoalAmount());
		project.setAmountRaised(projectDTO.getAmountRaised());
		Project updatedProject = projectRepository.save(project);
		return convertToDto(updatedProject);
	}

	@Override
	public boolean deleteProject(Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		projectRepository.delete(project);
		return true;
	}

	@Override
	public ProjectDTO getProjectById(Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		return convertToDto(project);
	}

	@Override
	public List<ProjectDTO> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private ProjectDTO convertToDto(Project project) {
		ProjectDTO dto = new ProjectDTO();
		dto.setId(project.getId());
		dto.setName(project.getName());
		dto.setDescription(project.getDescription());
		dto.setGoalAmount(project.getGoalAmount());
		dto.setAmountRaised(project.getAmountRaised());
		return dto;
	}

	private Project convertToEntity(ProjectDTO projectDTO) {
		Project project = new Project();
		project.setId(projectDTO.getId());
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setGoalAmount(projectDTO.getGoalAmount());
		project.setAmountRaised(projectDTO.getAmountRaised());
		return project;
	}
}
