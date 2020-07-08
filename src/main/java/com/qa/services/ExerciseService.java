package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@Service
public class ExerciseService {

	private ExerciseRepo repo;
	private ModelMapper mapper;

	public ExerciseService(ExerciseRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public ExerciseDTO mapToDTO(Exercise exercise) {
		return this.mapper.map(exercise, ExerciseDTO.class);
	}

	public ExerciseDTO create(Exercise exercise) {
		Exercise saved = this.repo.save(exercise);
		return this.mapToDTO(saved);
	}

	public ExerciseDTO update(Exercise exercise, Long e_id) {
		Exercise toUpdate = this.repo.findById(e_id).orElseThrow(() -> new EntityNotFoundException());

		toUpdate.setName(exercise.getName());
		toUpdate.setCategory(exercise.getCategory());
		toUpdate.setImageMain(exercise.getImageMain());
		toUpdate.setWorkout(exercise.getWorkout());
		return this.mapToDTO(repo.save(toUpdate));
	}

	public List<ExerciseDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ExerciseDTO read(Long e_id) {
		return mapToDTO(this.repo.findById(e_id).orElseThrow(() -> new EntityNotFoundException()));
	}

	public boolean delete(Long e_id) {
		this.repo.deleteById(e_id);
		return this.repo.existsById(e_id);
	}
}
