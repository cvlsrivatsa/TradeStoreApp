package org.db.tradestore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradestoreApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	/*
	@Test
	public void getsAllTrades() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/trade")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getsSingleTrade() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/trade/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}
	*/
	@Test
	public void returnsNotFoundForInvalidSingleTrade() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/latest/4")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}
	/*

	@Test
	public void addsNewRide() throws Exception {
		String newTrade = "{\"tradeId\":\"T1\",\"version\":\"1\",\"counterPartyId\":\"CP-1\",\"bookId\":\"B1\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/trade")
						.contentType(MediaType.APPLICATION_JSON)
						.content(newTrade)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}
	 */
}
