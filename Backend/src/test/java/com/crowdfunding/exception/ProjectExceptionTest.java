package com.crowdfunding.exception;

import static com.crowdfunding.utils.TestUtils.currentTest;
import static com.crowdfunding.utils.TestUtils.exceptionTestFile;
import static com.crowdfunding.utils.TestUtils.testReport;
import static com.crowdfunding.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crowdfunding.controller.ProjectController;
import com.crowdfunding.dto.ProjectDTO;
import com.crowdfunding.service.ProjectService;
import com.crowdfunding.utils.MasterData;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
public class ProjectExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateProjectInvalidDataException() throws Exception {
		ProjectDTO projectDTO = MasterData.getProjectDTO();
		projectDTO.setName("");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/projects")
				.content(MasterData.asJsonString(projectDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateProjectInvalidDataException() throws Exception {
		ProjectDTO projectDTO = MasterData.getProjectDTO();
		projectDTO.setName("");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/projects/" + projectDTO.getId())
				.content(MasterData.asJsonString(projectDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetProjectByIdResourceNotFoundException() throws Exception {
		Long projectId = 100L; // Assume this ID does not exist
		when(this.projectService.getProjectById(projectId))
				.thenThrow(new ResourceNotFoundException("Project not found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/projects/" + projectId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteProjectByIdResourceNotFoundException() throws Exception {
		Long projectId = 100L; // Assume this ID does not exist
		when(this.projectService.deleteProject(projectId))
				.thenThrow(new ResourceNotFoundException("Project not found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/projects/" + projectId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"),
				exceptionTestFile);
	}
}
