package com.crowdfunding.boundary;

import static com.crowdfunding.utils.TestUtils.boundaryTestFile;
import static com.crowdfunding.utils.TestUtils.currentTest;
import static com.crowdfunding.utils.TestUtils.testReport;
import static com.crowdfunding.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.crowdfunding.dto.InvestmentDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class InvestmentBoundaryTest {

	private static Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testAmountNotNull() throws IOException {
		InvestmentDTO investmentDTO = new InvestmentDTO();
		investmentDTO.setAmount(null);
		Set<ConstraintViolation<InvestmentDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountMin() throws IOException {
		InvestmentDTO investmentDTO = new InvestmentDTO();
		investmentDTO.setAmount(0.0);
		Set<ConstraintViolation<InvestmentDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testInvestorNameNotBlank() throws IOException {
		InvestmentDTO investmentDTO = new InvestmentDTO();
		investmentDTO.setInvestorName("");
		Set<ConstraintViolation<InvestmentDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testInvestorNameSize() throws IOException {
		InvestmentDTO investmentDTO = new InvestmentDTO();
		investmentDTO.setInvestorName("a".repeat(256)); // Generating a string longer than 255 characters
		Set<ConstraintViolation<InvestmentDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testProjectIdNotNull() throws IOException {
		InvestmentDTO investmentDTO = new InvestmentDTO();
		investmentDTO.setProjectId(null);
		Set<ConstraintViolation<InvestmentDTO>> violations = validator.validate(investmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
