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

	public ExerciseDTO(String string, String name2, String category2, Object imageMain2) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (e_id ^ (e_id >>> 32));
		result = prime * result + ((imageMain == null) ? 0 : imageMain.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ExerciseDTO other = (ExerciseDTO) obj;
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
		return true;
	}
	
	

}
