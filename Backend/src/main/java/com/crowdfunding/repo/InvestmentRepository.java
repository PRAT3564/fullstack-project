package com.crowdfunding.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crowdfunding.entity.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
	List<Investment> findByProjectId(Long projectId);

	List<Investment> findByInvestorName(String investorName);
}
