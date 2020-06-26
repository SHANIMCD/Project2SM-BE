package com.qa.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Workout {

	@Id
	@GeneratedValue
	private long id;
	
	
	@Column(nullable = false)
	private String title;
	
	@OneToMany(mappedBy = "workout")
	private List<Exercise> exercise;

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

	public List<Exercise> getExercise() {
		return exercise;
	}

	public void setExercise(List<Exercise> exercise) {
		this.exercise = exercise;
	}

	public Workout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Workout(long id, String title, List<Exercise> exercise) {
		super();
		this.id = id;
		this.title = title;
		this.exercise = exercise;
	}


	
	
		
	
}
