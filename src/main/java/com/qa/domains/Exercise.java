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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (e_id ^ (e_id >>> 32));
		result = prime * result + ((imageMain == null) ? 0 : imageMain.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((workout == null) ? 0 : workout.hashCode());
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
		Exercise other = (Exercise) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (e_id != other.e_id)
			return false;
		if (imageMain == null) {
			if (other.imageMain != null)
				return false;
		} else if (!imageMain.equals(other.imageMain))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (workout == null) {
			if (other.workout != null)
				return false;
		} else if (!workout.equals(other.workout))
			return false;
		return true;
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

	public Exercise(String name, String category, String imageMain, Workout workout) {
		this.name = name;
		this.category = category;
		this.imageMain = imageMain;
		this.workout = workout;
	}

	@Override
	public String toString() {
		return "Exercise [e_id=" + e_id + ", name=" + name + ", category=" + category + ", imageMain=" + imageMain
				+ ", workout=" + workout + "]";
	}

}