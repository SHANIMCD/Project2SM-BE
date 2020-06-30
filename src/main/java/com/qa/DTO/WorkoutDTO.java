package com.qa.DTO;

import java.util.List;


public class WorkoutDTO {
	
	private long id;
	private String title;
	private List<ExerciseDTO> exercise;
	
	public WorkoutDTO(long id, String title, List<ExerciseDTO> exercise) {
		super();
		this.id = id;
		this.title = title;
		this.exercise = exercise;
	}
	
	public WorkoutDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ExerciseDTO> getExercise() {
		return exercise;
	}

	public void setExercise(List<ExerciseDTO> exercise) {
		this.exercise = exercise;
	}

	@Override
	public String toString() {
		return "WorkoutDTO [id=" + id + ", title=" + title + ", exercise=" + exercise + "]";
	}
	
	
	


}
