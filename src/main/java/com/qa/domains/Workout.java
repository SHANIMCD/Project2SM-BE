package com.qa.domains;

import java.util.List;

import javax.persistence.CascadeType;
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

	@Column
	private String title;

	@OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
	private List<Exercise> exercises;

	public Workout(long id, String title, List<Exercise> exercises) {
		super();
		this.id = id;
		this.title = title;
		this.exercises = exercises;
	}

	public Workout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Workout(String title, Object object) {
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

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "Workout [id=" + id + ", title=" + title + ", exercises=" + exercises + "]";
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
		Workout other = (Workout) obj;
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
