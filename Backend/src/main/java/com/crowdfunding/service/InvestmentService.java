package com.crowdfunding.service;

import java.util.List;

import com.crowdfunding.dto.InvestmentDTO;

public interface InvestmentService {
	InvestmentDTO makeInvestment(InvestmentDTO investmentDTO);

	InvestmentDTO updateInvestment(Long investmentId, InvestmentDTO investmentDTO);

	boolean deleteInvestment(Long investmentId);

	InvestmentDTO getInvestmentById(Long investmentId);

	List<InvestmentDTO> getInvestmentsByProjectId(Long projectId);

	List<InvestmentDTO> getInvestmentsByInvestorName(String investorName);
}
