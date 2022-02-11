package com.thakkali.model;

public class Manager extends User {
	private int manager_id;
	private String password;
	private String hotel_name;
	private String hotel_address;

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_address() {
		return hotel_address;
	}

	public void setHotel_address(String hotel_address) {
		this.hotel_address = hotel_address;
	}

	public Manager() {}
	
	public void extendFromUser(User user) {
		this.user_id = user.user_id;
		this.user_name = user.user_name;
		this.address = user.address;
		this.email_id = user.email_id;
		this.contact_number = user.contact_number;
		this.user_type = user.user_type;
	}

	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + ", password=" + password + ", hotel_name=" + hotel_name
				+ ", hotel_address=" + hotel_address + "]";
	}

}
