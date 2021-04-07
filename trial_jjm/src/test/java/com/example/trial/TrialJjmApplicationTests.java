package com.example.trial;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TrialJjmApplicationTests {
	
	/*
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void contextLoads() throws Exception{
		String displayName = "밥을 먹었어";
		String projectId = "trial-eeoueo";
		
		mockMvc.perform((RequestBuilder) com.example.dialogflow.IntentManagement.getIntentIds(displayName, projectId))
				.andExpect(status().isOk())
				.andExpect(content().string("Is it work?"))
				.andDo(print());
	}
*/
}
