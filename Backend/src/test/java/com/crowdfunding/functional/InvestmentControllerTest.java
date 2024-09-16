package com.crowdfunding.functional;

import static com.crowdfunding.utils.MasterData.asJsonString;
import static com.crowdfunding.utils.MasterData.getInvestmentDTO;
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

import com.crowdfunding.controller.InvestmentController;
import com.crowdfunding.dto.InvestmentDTO;
import com.crowdfunding.service.InvestmentService;

@WebMvcTest(InvestmentController.class)
@AutoConfigureMockMvc
public class InvestmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvestmentService investmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testMakeInvestment() throws Exception {
		InvestmentDTO investmentDTO = getInvestmentDTO();
		when(investmentService.makeInvestment(any(InvestmentDTO.class))).thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/investments")
				.content(asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.CREATED.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testUpdateInvestment() throws Exception {
		InvestmentDTO investmentDTO = getInvestmentDTO();
		when(investmentService.updateInvestment(anyLong(), any(InvestmentDTO.class))).thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/investments/" + investmentDTO.getId())
				.content(asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetInvestmentById() throws Exception {
		InvestmentDTO investmentDTO = getInvestmentDTO();
		when(investmentService.getInvestmentById(anyLong())).thenReturn(investmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(investmentDTO)) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testDeleteInvestmentById() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/investments/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetInvestmentsByProjectId() throws Exception {
		List<InvestmentDTO> investmentDTOList = Arrays.asList(getInvestmentDTO());
		when(investmentService.getInvestmentsByProjectId(anyLong())).thenReturn(investmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/project/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetInvestmentsByInvestorName() throws Exception {
		List<InvestmentDTO> investmentDTOList = Arrays.asList(getInvestmentDTO());
		when(investmentService.getInvestmentsByInvestorName("Alice")).thenReturn(investmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/investor/Alice")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), !result.getResponse().getContentAsString().isEmpty() ? "true" : "false",
				businessTestFile);
	}
}
