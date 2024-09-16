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

import com.crowdfunding.dto.InvestmentDTO;
import com.crowdfunding.service.InvestmentService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

	private final InvestmentService investmentService;

	@Autowired
	public InvestmentController(InvestmentService investmentService) {
		this.investmentService = investmentService;
	}

	@PostMapping
	public ResponseEntity<InvestmentDTO> makeInvestment(@Valid @RequestBody InvestmentDTO investmentDTO) {
		InvestmentDTO createdInvestment = investmentService.makeInvestment(investmentDTO);
		return new ResponseEntity<>(createdInvestment, HttpStatus.CREATED);
	}

	@PutMapping("/{investmentId}")
	public ResponseEntity<InvestmentDTO> updateInvestment(@PathVariable Long investmentId,
			@Valid @RequestBody InvestmentDTO investmentDTO) {
		InvestmentDTO updatedInvestment = investmentService.updateInvestment(investmentId, investmentDTO);
		return ResponseEntity.ok(updatedInvestment);
	}

	@DeleteMapping("/{investmentId}")
	public ResponseEntity<Void> deleteInvestment(@PathVariable Long investmentId) {
		investmentService.deleteInvestment(investmentId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{investmentId}")
	public ResponseEntity<InvestmentDTO> getInvestmentById(@PathVariable Long investmentId) {
		InvestmentDTO investmentDTO = investmentService.getInvestmentById(investmentId);
		return ResponseEntity.ok(investmentDTO);
	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<InvestmentDTO>> getInvestmentsByProjectId(@PathVariable Long projectId) {
		List<InvestmentDTO> investments = investmentService.getInvestmentsByProjectId(projectId);
		return ResponseEntity.ok(investments);
	}

	@GetMapping("/investor/{investorName}")
	public ResponseEntity<List<InvestmentDTO>> getInvestmentsByInvestorName(@PathVariable String investorName) {
		List<InvestmentDTO> investments = investmentService.getInvestmentsByInvestorName(investorName);
		return ResponseEntity.ok(investments);
	}
}
