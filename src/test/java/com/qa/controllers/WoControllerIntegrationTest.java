package com.qa.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domains.Workout;
import com.qa.repos.WorkoutRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private WorkoutRepo repo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private long id;
	
	private Workout saveWo;
	private Workout saveWoWID;
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.saveWo = new Workout("new workout", null);
		this.saveWoWID = this.repo.save(this.saveWo);
		this.id = this.saveWoWID.getId();
	}
	
	@Test
	public void createWoTest() throws Exception {
		String result = this.mock.perform(request(HttpMethod.POST, "/wo/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(saveWo)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(saveWoWID), result);
	}
	
	@Test
	public void deleteWoTest() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/wo/delete/" + this.id)).andExpect(status().isNoContent());
	}
	
	@Test
	public void showAllWos() throws Exception {
		List <Workout> woList = new ArrayList<>();
		woList.add(this.saveWoWID);
		String content = this.mock.perform(request(HttpMethod.GET, "/wo/read").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		
	}
	
	@Test
	public void getOneWo() throws Exception {
		String content = this.mock.perform(request(HttpMethod.GET, "/wo/read/" + this.id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
//		assertEquals(this.mapper.writeValueAsString(this.saveWo), content);
	}
	
	@Test
	public void updateWoTest() throws Exception {
		Workout newWo = new Workout("front raises", null);
		Workout updatedWo = new Workout(newWo.getTitle(), newWo.getExercises());
		updatedWo.setId(this.saveWoWID.getId());
		
		String result = this.mock.perform(request(HttpMethod.PUT, "/wo/update/" + this.id).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newWo)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedWo), result);

	}

}
