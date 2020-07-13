package com.qa.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.qa.DTO.WorkoutDTO;
import com.qa.domains.Workout;
import com.qa.repos.WorkoutRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutServiceIntegrationTest {
	

	@Autowired
	WorkoutService service;
	
	@Autowired
	private WorkoutRepo repo;
	
	private Workout saveWo;
	
	private Workout saveWoWID;
	
	@Autowired
	private ModelMapper mapper;
	
	private WorkoutDTO mapToDTO(Workout workout) {
		return this.mapper.map(workout, WorkoutDTO.class);
	}
	
	@Before
	public void init() {
		this.saveWo = new Workout("morning workout", null);
		this.repo.deleteAll();
		this.saveWoWID = this.repo.save(this.saveWo);
	}
	
	@Test
	public void createWoTest() {
		assertEquals(this.mapToDTO(this.saveWoWID), this.service.create(saveWo));
	}
	
	@Test
	public void deleteWoTest() {
		assertThat(this.service.delete(this.saveWoWID.getId())).isFalse();
	}
	
	@Test
	public void woByIDTest() {
		assertThat(this.service.read(this.saveWoWID.getId())).isEqualTo(this.mapToDTO(this.saveWoWID));
	}
	
	@Test
	public void ReadWoListTest() {
		assertThat(this.service.read()).isEqualTo(Stream.of(this.mapToDTO(saveWoWID)).collect(Collectors.toList()));
	}
	
	@Test
	public void updateWoTest() {
		Workout newWo = new Workout("front raises", null);
		Workout updatedWo = new Workout(newWo.getTitle(), newWo.getExercises());
		updatedWo.setId(this.saveWoWID.getId());
		
		assertThat(this.service.update(newWo, this.saveWoWID.getId())).isEqualTo(this.mapToDTO(updatedWo));
	}

}
