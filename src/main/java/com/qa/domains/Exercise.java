package com.qa.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exercise {

	@Id
	@GeneratedValue
	long e_id;
	
	@Column(name = "name", unique = true, nullable = false)
	String name;
	
	@Column(nullable = false)
	String category;
	
	@Column(nullable = false)
	String imageMain;
	
	@ManyToOne(targetEntity = Workout.class)
	private Workout workout;

	public Exercise(long e_id, String name, String category, String imageMain, Workout workout) {
		super();
		this.e_id = e_id;
		this.name = name;
		this.category = category;
		this.imageMain = imageMain;
		this.workout = workout;
	}

	public long getE_id() {
		return e_id;
	}

	public void setE_id(long e_id) {
		this.e_id = e_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageMain() {
		return imageMain;
	}

	public void setImageMain(String imageMain) {
		this.imageMain = imageMain;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Exercise [e_id=" + e_id + ", name=" + name + ", category=" + category + ", imageMain=" + imageMain
				+ ", workout=" + workout + "]";
	}

	
}