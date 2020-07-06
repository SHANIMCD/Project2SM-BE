package com.qa.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.services.ExerciseService;

@RestController
@CrossOrigin
public class ExController { 

	private ExerciseService service;

	public ExController(ExerciseService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ExerciseDTO> create(@RequestBody Exercise exercise) {
		return new ResponseEntity<ExerciseDTO>(this.service.create(exercise), HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ExerciseDTO>> read() {
		return new ResponseEntity<List<ExerciseDTO>>(this.service.read(), HttpStatus.OK);
	}
	
	
	@GetMapping("/read/{e_id}")
	public ResponseEntity<Exercise> readOne(@PathVariable Long e_id) {
		return ResponseEntity.ok(this.service.read(e_id));
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
