package com.qa.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import com.qa.domains.Workout;
import com.qa.repos.WorkoutRepo;

@Service
public class WorkoutService {

	private WorkoutRepo repo;

	public WorkoutService(WorkoutRepo repo) {
		super();
		this.repo = repo;
	}
	
//	public WorkoutDTO createWorkout(Workout workout) {
//        Workout saved = this.repo.save(workout);
//        Workout.getExercise().stream().map(ex -> this.ExerciseRepo.findById(Exercise.getE_id()).orElseThrow(() -> new EntityNotFoundException()))
//        .forEach(ex -> {
//            ex.setExercise(saved);
//            this.ExerciseRepo.save(workout);
//        }));
//        return this.mapper.mapToDTO(saved);
//    }
	
	
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
