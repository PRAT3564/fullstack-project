package com.crowdfunding.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectDTO {
	private Long id;

	@NotBlank(message = "Project name is required")
	@Size(max = 255, message = "Project name must not exceed 255 characters")
	private String name;

	@NotBlank(message = "Project description is required")
	@Size(max = 2000, message = "Project description must not exceed 2000 characters")
	private String description;

	@NotNull(message = "Goal amount is required")
	@Min(value = 1, message = "Goal amount must be at least 1")
	private Double goalAmount;

	@NotNull(message = "Amount raised should not be null")
	private Double amountRaised;

	private List<InvestmentDTO> investments;

	public ProjectDTO() {
		super();
	}

	public ProjectDTO(Long id,
			@NotBlank(message = "Project name is required") @Size(max = 255, message = "Project name must not exceed 255 characters") String name,
			@NotBlank(message = "Project description is required") @Size(max = 2000, message = "Project description must not exceed 2000 characters") String description,
			@NotNull(message = "Goal amount is required") @Min(value = 1, message = "Goal amount must be at least 1") Double goalAmount,
			@NotNull(message = "Amount raised should not be null") Double amountRaised,
			List<InvestmentDTO> investments) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.goalAmount = goalAmount;
		this.amountRaised = amountRaised;
		this.investments = investments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(Double goalAmount) {
		this.goalAmount = goalAmount;
	}

	public Double getAmountRaised() {
		return amountRaised;
	}

	public void setAmountRaised(Double amountRaised) {
		this.amountRaised = amountRaised;
	}

	public List<InvestmentDTO> getInvestments() {
		return investments;
	}

	public void setInvestments(List<InvestmentDTO> investments) {
		this.investments = investments;
	}

	@Override
	public String toString() {
		return "ProjectDTO [id=" + id + ", name=" + name + ", description=" + description + ", goalAmount=" + goalAmount
				+ ", amountRaised=" + amountRaised + ", investments=" + investments + "]";
	}
}
