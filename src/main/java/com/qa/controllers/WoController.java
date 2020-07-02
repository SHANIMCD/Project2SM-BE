package com.qa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.DTO.WorkoutDTO;
import com.qa.domains.Workout;
import com.qa.services.WorkoutService;

@RestController
public class WoController {
	
	private WorkoutService service;

	public WoController(WorkoutService service) {
		super();
		this.service = service;
	}

	@PostMapping("/wo/create") 
	public ResponseEntity<WorkoutDTO> create(@RequestBody Workout workout) {
		return new ResponseEntity<WorkoutDTO>(this.service.create(workout), HttpStatus.CREATED);
	}
	
	@GetMapping("/wo/read")
	public ResponseEntity<List<WorkoutDTO>> read() {
		return new ResponseEntity<List<WorkoutDTO>>(this.service.read(), HttpStatus.OK);
	}
	
	@PutMapping("/wo/update/{id}") 
		public Workout update(@PathVariable Long id, @RequestBody Workout workout) {
			return this.service.update(workout, id);
		}

	
	@DeleteMapping("/wo/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}
}
