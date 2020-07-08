package com.qa.services;


import static org.junit.Assert.assertEquals;
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
	

	private final Exercise exercise = new Exercise("squat", "strength", "anImage");
	
	private Exercise savedExercise;
	
	@Mock
	private ExerciseRepo repo;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private ExerciseService service;
	
	@Before
	public void init() {
		this.savedExercise = new Exercise(exercise.getName(), exercise.getCategory(), exercise.getImageMain());
		this.savedExercise.setE_id(1);
		
	}
	
	@Test
	public void testCreate() {
		Mockito.when(this.repo.save(exercise)).thenReturn(savedExercise);
		assertEquals(this.service.mapToDTO(savedExercise), service.create(exercise));
	}
}
