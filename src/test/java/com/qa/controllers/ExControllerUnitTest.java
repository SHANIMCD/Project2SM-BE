package com.qa.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.services.ExerciseService;

@RunWith(MockitoJUnitRunner.class)
public class ExControllerUnitTest {

	@InjectMocks
	private ExController controller;
	
	@Mock
	ExerciseService service;
	
	private List<Exercise> exList;
	private Exercise saveExerciseWID;
	private Exercise saveExercise;
	private ExerciseDTO dto;
	final long e_id = 1L;
	
	
	private ModelMapper mapper = new ModelMapper();
	
	private ExerciseDTO mapToDTO(Exercise exercise) {
		return this.mapper.map(exercise, ExerciseDTO.class);
	}
	
	@Before
	public void init() {
		this.exList = new ArrayList<>();
		this.saveExercise = new Exercise("squat", "strength", "anImage", null);
		this.exList.add(saveExercise);
		this.saveExerciseWID = new Exercise(saveExercise.getName(), saveExercise.getCategory(), saveExercise.getImageMain(), saveExercise.getWorkout());
		this.saveExerciseWID.setE_id(1);
		this.dto = this.mapToDTO(saveExercise);
	}
	
	@Test
	public void createExTest() {
		when(this.service.create(saveExercise)).thenReturn(this.dto);
		assertEquals(new ResponseEntity<ExerciseDTO>(this.dto, HttpStatus.CREATED), this.controller.create(saveExercise));
		verify(this.service, times(1)).create(this.saveExercise);
	}
	
	@Test
	public void deleteDuckTest() {
		this.controller.delete(e_id);
		verify(this.service, times(1)).delete(e_id);
	}
	
	@Test
	public void exByIdTest() {
		when(this.service.read(this.e_id)).thenReturn(this.dto);
		assertEquals(new ResponseEntity<ExerciseDTO>(this.dto, HttpStatus.OK), this.controller.readOne(this.e_id));
		verify(this.service, times(1)).read(this.e_id);
	}
	
	@Test
	public void allExsTest() {
		when(service.read()).thenReturn(this.exList.stream().map(this::mapToDTO).collect(Collectors.toList()));
		assertFalse("Exercises not found", this.controller.read().getBody().isEmpty());
		verify(service, times(1)).read();
	}
	
	@Test
	public void updateExTest() {
		Exercise newEx = new Exercise("front raises", "strength", "image2", null);
		Exercise updatedEx = new Exercise(newEx.getName(), newEx.getCategory(), newEx.getImageMain(), newEx.getWorkout());
		updatedEx.setE_id(this.e_id);
		
		when(this.service.update(newEx, this.e_id)).thenReturn(this.mapToDTO(updatedEx));
		
		assertEquals(new ResponseEntity<ExerciseDTO>(this.mapToDTO(updatedEx), HttpStatus.ACCEPTED), this.controller.update(this.e_id, newEx));
		verify(this.service, times(1)).update(newEx, this.e_id);
	}
}
