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

import com.qa.DTO.WorkoutDTO;
import com.qa.domains.Workout;
import com.qa.repos.WorkoutRepo;

@RunWith(MockitoJUnitRunner.class)
public class WorkoutServiceUnitTest {
	
	private final Workout workout = new Workout("morning blast", null);
	
	private Workout savedWorkout;
	private Workout savedWorkoutWID;
	private WorkoutDTO wkDTO;
	private List<Workout> woList;
	
	@Mock
	private WorkoutRepo repo;
	
	@Mock
	private ModelMapper mapper;

	@InjectMocks
	private WorkoutService service;
	
	private Long id = 1L;
	
	@Before
	public void init() {
		this.woList = new ArrayList<>();
		this.savedWorkout = new Workout("morning blast", null);
		this.woList.add(savedWorkout);
		this.savedWorkoutWID = new Workout(workout.getTitle(), workout.getExercises());
		this.savedWorkoutWID.setId(id);
		this.wkDTO = new ModelMapper().map(savedWorkoutWID, WorkoutDTO.class);
	}
	
	@Test
	public void testWOCreate() {	
		Mockito.when(this.repo.save(workout)).thenReturn(savedWorkout);
		assertEquals(this.service.mapToDTO(savedWorkout), service.create(workout));
	}
	
	@Test
	public void deleteWoTest() {
		when(this.repo.existsById(id)).thenReturn(true, false);
		this.service.delete(id);
		verify(this.repo, times(1)).deleteById(id);
	}
	
	@Test
	public void WOByIdTest() {
		when(this.repo.findById(id)).thenReturn(Optional.of(savedWorkout));
		assertEquals(this.service.mapToDTO(savedWorkout), service.read(id));
		verify(this.repo, times(1)).findById(id);
	}
	
	@Test
	public void ListWorkoutsTest() {
		Mockito.when(this.repo.findAll()).thenReturn(this.woList);
		
		assertFalse("No Workouts found", this.service.read().isEmpty());
		verify(this.repo, times(1)).findAll();
	}
	
	@Test
	public void updateWoTest() {
		Workout newWorkout = new Workout("front raises", null);
		Workout updatedWorkout = new Workout(newWorkout.getTitle(), newWorkout.getExercises());
		updatedWorkout.setId(this.id);
		
		WorkoutDTO updatedDTO = new ModelMapper().map(updatedWorkout, WorkoutDTO.class);
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.savedWorkoutWID));
		when(this.mapper.map(updatedWorkout, WorkoutDTO.class)).thenReturn(updatedDTO);
		
		when(this.repo.save(updatedWorkout)).thenReturn(updatedWorkout);
		
		assertEquals(updatedDTO, this.service.update(newWorkout, this.id));
		
		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedWorkout);
	}
	
}
