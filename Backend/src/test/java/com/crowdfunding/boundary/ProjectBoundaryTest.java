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

import com.crowdfunding.dto.ProjectDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ProjectBoundaryTest {

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
	public void testNameNotBlank() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setName("");
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameSize() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setName("a".repeat(256)); // Generating a string longer than 255 characters
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDescriptionNotBlank() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setDescription("");
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDescriptionSize() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setDescription("a".repeat(2001)); // Generating a string longer than 2000 characters
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testGoalAmountNotNull() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setGoalAmount(null);
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testGoalAmountMin() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setGoalAmount(0.0);
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAmountRaisedNotNull() throws IOException {
		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setAmountRaised(null);
		Set<ConstraintViolation<ProjectDTO>> violations = validator.validate(projectDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
