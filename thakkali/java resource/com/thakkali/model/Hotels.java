package com.thakkali.model;

public class Hotels {
	private int manager_id;
	private String hotel_name;
	private String hotel_address;
	private String hotel_image;
	

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
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

	public String getHotel_image() {
		return hotel_image;
	}

	public void setHotel_image(String hotel_image) {
		this.hotel_image = hotel_image;
	}

	public Hotels() {}

	@Override
	public String toString() {
		return "HotelDao [manager_id=" + manager_id + ", hotel_name=" + hotel_name + ", hotel_address=" + hotel_address
				+ ", hotel_image=" + hotel_image + "]";
	}
}

