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

	public WorkoutDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WorkoutDTO [id=" + id + ", title=" + title + ", exercises=" + exercises + "]";
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exercises == null) ? 0 : exercises.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkoutDTO other = (WorkoutDTO) obj;
		if (exercises == null) {
			if (other.exercises != null)
				return false;
		} else if (!exercises.equals(other.exercises))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	

}
