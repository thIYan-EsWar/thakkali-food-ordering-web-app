package com.thakkali.model;

public class Foods extends User{
	protected int food_id;
	protected int manager_id;
	protected String food_name;
	protected String food_category;
	protected String description;
	protected float price;
	protected String image_url;

	protected String image_alt;
	protected int availability;
	
	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_category() {
		return food_category;
	}

	public void setFood_category(String food_category) {
		this.food_category = food_category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getImage_alt() {
		return image_alt;
	}

	public void setImage_alt(String image_alt) {
		this.image_alt = image_alt;
	}
	
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
	public int getAvailability() {
		return this.availability;
	}

	public Foods() {
		super();
	}
	
	public void extendFromUser(User user) {
		this.user_id = user.user_id;
		this.manager_id = user.user_id;
		this.user_name = user.user_name;
		this.email_id = user.email_id;
		this.address = user.address;
		this.user_type = user.user_type;
		this.contact_number = user.contact_number;
	}

	@Override
	public String toString() {
		return "Foods [food_id=" + food_id + ", manager_id=" + manager_id + ", food_name=" + food_name
				+ ", food_category=" + food_category + ", description=" + description + ", price=" + price
				+ ", image_url=" + image_url + ", image_alt=" + image_alt + "]";
	}
	
}
