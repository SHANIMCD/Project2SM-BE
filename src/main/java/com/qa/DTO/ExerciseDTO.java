package com.qa.DTO;

public class ExerciseDTO {

	private long e_id;
	private String name;
	private String category;
	private String imageMain;
	
	public ExerciseDTO(long e_id, String name, String category, String imageMain) {
		super();
		this.e_id = e_id;
		this.name = name;
		this.category = category;
		this.imageMain = imageMain;
	}

	public ExerciseDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "ExerciseDTO [e_id=" + e_id + ", name=" + name + ", category=" + category + ", imageMain=" + imageMain
				+ "]";
	}
	
	
	
	}
