package com.qa.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.domains.Exercise;
import com.qa.services.ExerciseService;

@RestController
public class ExController {

	private ExerciseService service;

	public ExController(ExerciseService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public Exercise create(@RequestBody Exercise exercise) {
		return this.service.create(exercise);
	}
	
	@GetMapping("/read")
	public List<Exercise> read() {
		return this.service.read();
	}
	
	@PutMapping("/update/{e_id}")
	public Exercise update(@PathVariable Long e_id, @RequestBody Exercise exercise) {
		return this.service.update(exercise, e_id);
	}
	
	@DeleteMapping("/delete/{e_id}")
	public boolean delete(@PathVariable Long e_id) {
		return this.service.delete(e_id);
	}
}
