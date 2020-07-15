package com.qa.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ExControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ExerciseRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long e_id;
	private Exercise saveEx;
	private Exercise saveEXWID;
	private ExerciseDTO exDTO;
	
	private ExerciseDTO mapToDTO(Exercise exercise) {
		return this.modelMapper.map(exercise, ExerciseDTO.class);
	}
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.saveEx = new Exercise("Squat", "strength", "image", null);
		this.saveEXWID = this.repo.save(this.saveEx);
		this.e_id = this.saveEXWID.getE_id();
		this.exDTO = this.mapToDTO(saveEXWID);
	}
	
	@Test
	public void createExTest() throws Exception {
		MockHttpServletRequestBuilder mRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/create");
		mRequest.contentType(MediaType.APPLICATION_JSON);
		mRequest.content(this.objectMapper.writeValueAsString(saveEx));
		mRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.objectMapper.writeValueAsString(exDTO));
		this.mockMvc.perform(mRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void deleteExTest() throws Exception {
		this.mockMvc.perform(request(HttpMethod.DELETE, "/delete/" + this.e_id)).andExpect(status().isNoContent());
	}
	
	@Test
	public void getAllExs() throws Exception {
		List<ExerciseDTO> exList = new ArrayList<>();
		exList.add(this.exDTO);
		String content = this.mockMvc.perform(request(HttpMethod.GET, "/read").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.objectMapper.writeValueAsString(exList), content);
	}
	
	@Test
	public void updateExTest() throws Exception {
		Exercise newEx = new Exercise("front raises", "strength", "image", null);
		Exercise updatedEx = new Exercise(newEx.getName(), newEx.getCategory(), newEx.getImageMain(), newEx.getWorkout());
		updatedEx.setE_id(e_id);
		
		String result = this.mockMvc
				.perform(request(HttpMethod.PUT, "/update/" + this.e_id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(newEx)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedEx)), result);
	}
	
	
	
	

}
