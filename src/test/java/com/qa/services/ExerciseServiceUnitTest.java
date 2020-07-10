package com.qa.services;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceUnitTest {
	

	private final Exercise exercise = new Exercise("squat", "strength", "anImage", null);
	
	private Exercise savedExercise;
	private Exercise savedExerciseWID;
	
	private ExerciseDTO exDTO;
	
	
	private List<Exercise> exList;
	
	@Mock
	private ExerciseRepo repo;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private ExerciseService service;

	private Long e_id = 1L;
	
	@Before
	public void init() {
		this.exList = new ArrayList<>();
		this.savedExercise = new Exercise("squat", "strength", "anImage", null);
		this.exList.add(savedExercise);
		this.savedExerciseWID = new Exercise(exercise.getName(), exercise.getCategory(), exercise.getImageMain(), exercise.getWorkout());
		this.savedExerciseWID.setE_id(e_id);
		this.exDTO = new ModelMapper().map(savedExerciseWID, ExerciseDTO.class);
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
	
	@Test
	public void findExByIDTest() {
		when(this.repo.findById(e_id)).thenReturn(Optional.of(savedExercise));
		assertEquals(this.service.mapToDTO(savedExercise), service.read(e_id));
		verify(this.repo, times(1)).findById(e_id);
	}
	
	@Test
	public void readExercisesTest() {
		when(this.repo.findAll()).thenReturn(this.exList);
//		when(this.mapper.map(savedExerciseWID, ExerciseDTO.class)).thenReturn(exDTO);
		
		assertFalse("No exercises found", this.service.read().isEmpty());
		verify(this.repo, times(1)).findAll();
	}
	
	@Test
	public void UpdateExTest() {
		Exercise newEx = new Exercise("front raises", "strength", "image", null);
		Exercise updatedEx = new Exercise(newEx.getName(), newEx.getCategory(), newEx.getImageMain(), newEx.getWorkout());
		updatedEx.setE_id(1L);
		
		ExerciseDTO newDTO = new ModelMapper().map(updatedEx, ExerciseDTO.class);
		
		when(this.repo.findById(this.e_id)).thenReturn(Optional.of(savedExerciseWID));
		when(this.mapper.map(updatedEx, ExerciseDTO.class)).thenReturn(newDTO);
		
		when(this.repo.save(updatedEx)).thenReturn(updatedEx);
		
		assertEquals(newDTO, this.service.update(newEx, this.e_id));
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedEx);
	}
	
}
