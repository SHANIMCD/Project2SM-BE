package com.qa.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.DTO.WorkoutDTO;
import com.qa.domains.Workout;
import com.qa.services.WorkoutService;

@RunWith(MockitoJUnitRunner.class)
public class WoControllerUnitTest {

	@InjectMocks
	private WoController controller;
	
	@Mock
	WorkoutService service;
	
	private List<Workout> woList;
	private Workout saveWoWID;
	private Workout saveWo;
	private WorkoutDTO dto;
	final long id = 1L;
	
	private ModelMapper mapper = new ModelMapper();
	
	private WorkoutDTO mapToDTO(Workout workout) {
		return this.mapper.map(workout, WorkoutDTO.class);
	}
	
	@Before
	public void init() {
		this.woList = new ArrayList<>();
		this.saveWo = new Workout("morning blast", null);
		this.woList.add(saveWo);
		this.saveWoWID = new Workout(saveWo.getTitle(), saveWo.getExercises());
		this.saveWoWID.setId(1);
		this.dto = this.mapToDTO(saveWo);
	}
	
	@Test
	public void createWoTest() {
		when(this.service.create(saveWo)).thenReturn(this.dto);
		assertEquals(new ResponseEntity<WorkoutDTO>(this.dto, HttpStatus.CREATED) , this.controller.create(saveWo));
		verify(this.service, times(1)).create(this.saveWo);
	}
	
	@Test
	public void deleteWoTest() {
		this.controller.delete(id);
		verify(this.service, times(1)).delete(id);
	}
	
	@Test
	public void woByIdTest() {
		when(this.service.read(id)).thenReturn(this.dto);
		assertEquals(new ResponseEntity<WorkoutDTO>(this.dto, HttpStatus.OK), this.controller.readOne(this.id));
		verify(this.service, times(1)).read(this.id);
	}
	
	@Test
	public void allWoTest() {
		when(service.read()).thenReturn(this.woList.stream().map(this::mapToDTO).collect(Collectors.toList()));
		assertFalse("No workouts found", this.controller.read().getBody().isEmpty());
		verify(service, times(1)).read();
	}
	
	@Test
	public void updateWoTest() {
		Workout newWo = new Workout("Morning workout", null);
		Workout updatedWo = new Workout(newWo.getTitle(), newWo.getExercises());
		newWo.setId(this.id);
		
		when(this.service.update(newWo, this.id)).thenReturn(this.mapToDTO(updatedWo));
		
		assertEquals(new ResponseEntity<WorkoutDTO>(this.mapToDTO(updatedWo), HttpStatus.ACCEPTED), this.controller.update(this.id, newWo));
		verify(this.service, times(1)).update(newWo, this.id);
	}
	
}
