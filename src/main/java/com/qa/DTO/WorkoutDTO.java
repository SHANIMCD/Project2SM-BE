package com.qa.DTO;

import java.util.List;

public class WorkoutDTO {

	private long id;
	private String title;
	private List<ExerciseDTO> exercises;

	public WorkoutDTO(long id, String title, List<ExerciseDTO> exercises) {
		super();
		this.id = id;
		this.title = title;
		this.exercises = exercises;
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

	public List<ExerciseDTO> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseDTO> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "WorkoutDTO [id=" + id + ", title=" + title + ", exercises=" + exercises + "]";
	}

	public WorkoutDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
