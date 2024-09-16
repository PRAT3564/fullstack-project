package com.crowdfunding.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdfunding.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
