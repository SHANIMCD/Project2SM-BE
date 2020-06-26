package com.qa.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@Service
public class ExerciseService {

	private ExerciseRepo repo;

	public ExerciseService(ExerciseRepo repo) {
		super();
		this.repo = repo;
	}

	public Exercise create(Exercise exercise) {
		return this.repo.save(exercise);
	}

	public Exercise update(Exercise exercise, Long e_id) {
		Exercise toUpdate = this.repo.findById(e_id).orElseThrow(() -> new EntityNotFoundException());
		
		toUpdate.setName(exercise.getName());
		toUpdate.setCategory(exercise.getCategory());
		toUpdate.setImageMain(exercise.getImageMain());
		
		return this.repo.save(toUpdate);
	}

	public List<Exercise> read() {
		return this.repo.findAll();
	}

	public boolean delete(Long e_id) {
		this.repo.deleteById(e_id);
		return this.repo.existsById(e_id);
	}
}
