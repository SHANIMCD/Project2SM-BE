package com.qa.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.DTO.WorkoutDTO;
import com.qa.domains.Workout;
import com.qa.repos.ExerciseRepo;
import com.qa.repos.WorkoutRepo;

@Service
public class WorkoutService {

	private WorkoutRepo repo;
	
	private ExerciseRepo exRepo;

	private ModelMapper mapper;
	
	public WorkoutService(WorkoutRepo repo, ExerciseRepo exRepo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.exRepo = exRepo;
		this.mapper = mapper;
	}
	
	private WorkoutDTO mapToDTO(Workout workout) {
		return this.mapper.map(workout, WorkoutDTO.class);
	}
	
	public WorkoutDTO createWorkout(Workout workout) {
        Workout saved = this.repo.save(workout);
        saved.getExercise().stream().map(ex -> this.exRepo.findById(ex.getE_id()).orElseThrow(EntityNotFoundException::new))
        .forEach(ex -> {
        	ex.setWorkout(saved);
        	this.exRepo.save(ex);
        });
        return this.mapToDTO(saved);
    }
	
	
	public Workout create(@RequestBody Workout workout) {		
		return this.repo.save(workout);
	}
		
	public Workout update(Workout workout, Long id) {
		Workout toUpdate = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		toUpdate.setTitle(workout.getTitle());
		toUpdate.setExercise(workout.getExercise());	
		return this.repo.save(toUpdate);
	}
	
	public List<Workout> read() {
		return this.repo.findAll();
	}
	
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
}
