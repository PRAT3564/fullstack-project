package com.crowdfunding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Investment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Investment amount is required")
	@Min(value = 1, message = "Investment amount must be at least 1")
	private Double amount;

	@NotNull(message = "Investor name is required")
	private String investorName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	public Investment() {
		super();
	}

	public Investment(Long id,
			@NotNull(message = "Investment amount is required") @Min(value = 1, message = "Investment amount must be at least 1") Double amount,
			@NotNull(message = "Investor name is required") String investorName, Project project) {
		super();
		this.id = id;
		this.amount = amount;
		this.project = project;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	@Override
	public String toString() {
		return "Investment [id=" + id + ", amount=" + amount + ", investorName=" + investorName + ", project=" + project
				+ "]";
	}
}
