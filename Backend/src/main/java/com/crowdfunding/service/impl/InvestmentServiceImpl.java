package com.crowdfunding.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.dto.InvestmentDTO;
import com.crowdfunding.entity.Investment;
import com.crowdfunding.entity.Project;
import com.crowdfunding.exception.ResourceNotFoundException;
import com.crowdfunding.repo.InvestmentRepository;
import com.crowdfunding.service.InvestmentService;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	private final InvestmentRepository investmentRepository;

	@Autowired
	public InvestmentServiceImpl(InvestmentRepository investmentRepository) {
		this.investmentRepository = investmentRepository;
	}

	@Override
	public InvestmentDTO makeInvestment(InvestmentDTO investmentDTO) {
		Investment investment = convertToEntity(investmentDTO);
		investment = investmentRepository.save(investment);
		return convertToDto(investment);
	}

	@Override
	public InvestmentDTO updateInvestment(Long investmentId, InvestmentDTO investmentDTO) {
		Investment investment = investmentRepository.findById(investmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Investment not found"));
		investment.setAmount(investmentDTO.getAmount());
		// Assume the investorName and projectId are not updatable or handled separately
		investment = investmentRepository.save(investment);
		return convertToDto(investment);
	}

	@Override
	public boolean deleteInvestment(Long investmentId) {
		Investment investment = investmentRepository.findById(investmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Investment not found"));
		investmentRepository.delete(investment);
		return true;
	}

	@Override
	public InvestmentDTO getInvestmentById(Long investmentId) {
		Investment investment = investmentRepository.findById(investmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Investment not found"));
		return convertToDto(investment);
	}

	@Override
	public List<InvestmentDTO> getInvestmentsByProjectId(Long projectId) {
		List<Investment> investments = investmentRepository.findByProjectId(projectId);
		return investments.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<InvestmentDTO> getInvestmentsByInvestorName(String investorName) {
		List<Investment> investments = investmentRepository.findByInvestorName(investorName);
		return investments.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private InvestmentDTO convertToDto(Investment investment) {
		InvestmentDTO dto = new InvestmentDTO();
		dto.setId(investment.getId());
		dto.setAmount(investment.getAmount());
		dto.setInvestorName(investment.getInvestorName());
		dto.setProjectId(investment.getProject().getId());
		return dto;
	}

	private Investment convertToEntity(InvestmentDTO dto) {
		Investment investment = new Investment();
		investment.setAmount(dto.getAmount());
		investment.setInvestorName(dto.getInvestorName());
		investment.setProject(new Project(dto.getProjectId()));
		return investment;
	}
}
