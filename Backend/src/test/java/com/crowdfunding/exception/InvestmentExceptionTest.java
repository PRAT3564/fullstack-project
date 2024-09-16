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

import com.crowdfunding.controller.InvestmentController;
import com.crowdfunding.dto.InvestmentDTO;
import com.crowdfunding.service.InvestmentService;
import com.crowdfunding.utils.MasterData;

@WebMvcTest(InvestmentController.class)
@AutoConfigureMockMvc
public class InvestmentExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InvestmentService investmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateInvestmentInvalidDataException() throws Exception {
		InvestmentDTO investmentDTO = MasterData.getInvestmentDTO();
		investmentDTO.setAmount(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/investments")
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateInvestmentInvalidDataException() throws Exception {
		InvestmentDTO investmentDTO = MasterData.getInvestmentDTO();
		investmentDTO.setAmount(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/investments/" + investmentDTO.getId())
				.content(MasterData.asJsonString(investmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetInvestmentByIdResourceNotFoundException() throws Exception {
		InvestmentDTO investmentDTO = MasterData.getInvestmentDTO();
		investmentDTO.setId(100L);
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Investment not found");

		when(this.investmentService.getInvestmentById(investmentDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Investment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteInvestmentByIdResourceNotFoundException() throws Exception {
		InvestmentDTO investmentDTO = MasterData.getInvestmentDTO();
		investmentDTO.setId(100L);
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Investment not found");

		when(this.investmentService.deleteInvestment(investmentDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Investment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/investments/" + investmentDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

}
