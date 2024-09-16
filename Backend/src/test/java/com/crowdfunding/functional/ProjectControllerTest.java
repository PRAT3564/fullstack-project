package com.crowdfunding.functional;

import static com.crowdfunding.utils.MasterData.asJsonString;
import static com.crowdfunding.utils.MasterData.getProjectDTO;
import static com.crowdfunding.utils.TestUtils.businessTestFile;
import static com.crowdfunding.utils.TestUtils.currentTest;
import static com.crowdfunding.utils.TestUtils.testReport;
import static com.crowdfunding.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateProject() throws Exception {
		ProjectDTO projectDTO = getProjectDTO();
		when(projectService.createProject(any(ProjectDTO.class))).thenReturn(projectDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/projects").content(asJsonString(projectDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.CREATED.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testUpdateProject() throws Exception {
		ProjectDTO projectDTO = getProjectDTO();
		when(projectService.updateProject(anyLong(), any(ProjectDTO.class))).thenReturn(projectDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/projects/" + projectDTO.getId())
				.content(asJsonString(projectDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetProjectById() throws Exception {
		ProjectDTO projectDTO = getProjectDTO();
		when(projectService.getProjectById(anyLong())).thenReturn(projectDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/projects/" + projectDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(projectDTO)) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testDeleteProjectById() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/projects/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testListAllProjects() throws Exception {
		List<ProjectDTO> projectDTOList = Arrays.asList(getProjectDTO());
		when(projectService.getAllProjects()).thenReturn(projectDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/projects")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}
}
