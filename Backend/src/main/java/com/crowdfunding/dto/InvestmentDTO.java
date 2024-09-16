package com.crowdfunding.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InvestmentDTO {
	private Long id;

	@NotNull(message = "Investment amount is required")
	@Min(value = 1, message = "Investment amount must be at least 1")
	private Double amount;

	@NotBlank(message = "Investor name is required")
	@Size(max = 255, message = "Investor name must not exceed 255 characters")
	private String investorName;

	@NotNull(message = "Project ID is required")
	private Long projectId;

	public InvestmentDTO() {
		super();
	}

	public InvestmentDTO(Long id,
			@NotNull(message = "Investment amount is required") @Min(value = 1, message = "Investment amount must be at least 1") Double amount,
			@NotBlank(message = "Investor name is required") @Size(max = 255, message = "Investor name must not exceed 255 characters") String investorName,
			@NotNull(message = "Project ID is required") Long projectId) {
		super();
		this.id = id;
		this.amount = amount;
		this.projectId = projectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	@Override
	public String toString() {
		return "InvestmentDTO [id=" + id + ", amount=" + amount + ", investorName=" + investorName + ", projectId="
				+ projectId + "]";
	}
}
