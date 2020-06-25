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
	long id;
	
	
	@Column(nullable = false)
	String title;
	
	@OneToMany(mappedBy = "workout")
	private List<Exercise> exercise;


	
	
	public Workout(long w_id, String title, List<Exercise> exercise) {
		super();
		this.id = w_id;
		this.title = title;
		this.exercise = exercise;
	}




	public Workout() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return "Workout [id=" + id + ", title=" + title + ", exercise=" + exercise + "]";
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




	public List<Exercise> getExercise() {
		return exercise;
	}




	public void setExercise(List<Exercise> exercise) {
		this.exercise = exercise;
	}


	
	
}
