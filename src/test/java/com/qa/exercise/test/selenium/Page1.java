package com.qa.exercise.test.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page1 {

	@FindBy(id = "addName")
	private WebElement addName;
	
	@FindBy(id = "catSelect")
	private WebElement addCategory;
	
	@FindBy(id = "addImage")
	private WebElement addImage;
	
	@FindBy(id = "addWorkoutID")
	private WebElement addWorkoutID;
	
	@FindBy(id = "submitButton")
	private WebElement submitButton;
	
	@FindBy(id = "updateName")
	private WebElement updateName;
	
	@FindBy(id = "updateCatSelect")
	private WebElement updateCategory;
	
	@FindBy(id = "updateImage")
	private WebElement updateImage;
	
	@FindBy(id = "updateWorkoutID")
	private WebElement updateWorkoutID;
	
	@FindBy(id = "updateButton")
	private WebElement updateButton;
	
	
	public void createEx(String name, String image, String category, String workoutID) {
		this.addName.sendKeys(name);
		this.addImage.sendKeys(image);
		this.addCategory.sendKeys(category);
		this.addWorkoutID.sendKeys(workoutID);
	}


	public WebElement getAddName() {
		return addName;
	}


	public void setAddName(WebElement addName) {
		this.addName = addName;
	}


	public WebElement getAddCategory() {
		return addCategory;
	}


	public void setAddCategory(WebElement addCategory) {
		this.addCategory = addCategory;
	}


	public WebElement getAddImage() {
		return addImage;
	}


	public void setAddImage(WebElement addImage) {
		this.addImage = addImage;
	}


	public WebElement getAddWorkoutID() {
		return addWorkoutID;
	}


	public void setAddWorkoutID(WebElement addWorkoutID) {
		this.addWorkoutID = addWorkoutID;
	}


	public WebElement getSubmitButton() {
		return submitButton;
	}


	public void setSubmitButton(WebElement submitButton) {
		this.submitButton = submitButton;
	}


	public WebElement getUpdateName() {
		return updateName;
	}


	public void setUpdateName(WebElement updateName) {
		this.updateName = updateName;
	}


	public WebElement getUpdateCategory() {
		return updateCategory;
	}


	public void setUpdateCategory(WebElement updateCategory) {
		this.updateCategory = updateCategory;
	}


	public WebElement getUpdateImage() {
		return updateImage;
	}


	public void setUpdateImage(WebElement updateImage) {
		this.updateImage = updateImage;
	}


	public WebElement getUpdateWorkoutID() {
		return updateWorkoutID;
	}


	public void setUpdateWorkoutID(WebElement updateWorkoutID) {
		this.updateWorkoutID = updateWorkoutID;
	}


	public WebElement getUpdateButton() {
		return updateButton;
	}


	public void setUpdateButton(WebElement updateButton) {
		this.updateButton = updateButton;
	}
	
}
