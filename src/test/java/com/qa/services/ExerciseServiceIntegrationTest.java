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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.qa.DTO.ExerciseDTO;
import com.qa.domains.Exercise;
import com.qa.repos.ExerciseRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ExerciseServiceIntegrationTest {
	
	@Autowired
	private ExerciseService service;
	
	@Autowired
	private ExerciseRepo repo;
	
	private Exercise saveEx;
	
	private Exercise saveExWID;
	
	@Autowired
	private ModelMapper mapper;
	
	private ExerciseDTO mapToDTO(Exercise exercise) {
		return this.mapper.map(exercise, ExerciseDTO.class);
	}
	
	@Before
	public void init() {
		this.saveEx = new Exercise("new Exercise", "heartrate", "image", null);
		
		this.repo.deleteAll();
		
		this.saveExWID = this.repo.save(this.saveEx);
	}
	
	@Test
	public void createExTest() {
		assertEquals(this.mapToDTO(this.saveExWID), this.service.create(saveEx));
	}
	
	@Test
	public void deleteExTest() {
		assertThat(this.service.delete(this.saveExWID.getE_id())).isFalse();
	}
	
	@Test
	public void exByIDTest() {
		assertThat(this.service.read(this.saveExWID.getE_id())).isEqualTo(this.mapToDTO(this.saveExWID));
	}
	
	@Test
	public void readExsTest() {
		assertThat(this.service.read()).isEqualTo(Stream.of(this.mapToDTO(saveExWID)).collect(Collectors.toList()));
	}
	
	@Test
	public void updateExTest() {
		Exercise newEx = new Exercise("front raises", "strength", "image", null);
		Exercise updatedEx = new Exercise(newEx.getName(), newEx.getCategory(), newEx.getImageMain(), newEx.getWorkout());
		updatedEx.setE_id(this.saveExWID.getE_id());
		
		assertThat(this.service.update(newEx, this.saveExWID.getE_id())).isEqualTo(this.mapToDTO(updatedEx));
	}
}
