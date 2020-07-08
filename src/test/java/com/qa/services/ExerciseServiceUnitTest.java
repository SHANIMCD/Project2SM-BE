package com.qa.services;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceUnitTest {
	

	private final Exercise exercise = new Exercise("squat", "strength", "anImage", null);
	
	private Exercise savedExercise;
	
	@Mock
	private ExerciseRepo repo;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private ExerciseService service;

	private Long e_id;
	
	@Before
	public void init() {
		this.savedExercise = new Exercise(exercise.getName(), exercise.getCategory(), exercise.getImageMain(), exercise.getWorkout());
		this.savedExercise.setE_id(1);
	}
	
	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(exercise)).thenReturn(savedExercise);
		assertEquals(this.service.mapToDTO(savedExercise), service.create(exercise));
	}
	@Test
	public void testDeleteAnExercise() {
		when(this.repo.existsById(e_id)).thenReturn(true, false);
		this.service.delete(e_id);
		
		verify(this.repo, times(1)).deleteById(e_id);
	}
}
