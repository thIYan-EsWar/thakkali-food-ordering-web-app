package com.thakkali.model;

import java.sql.Timestamp;

public class Orders {
		
	private int order_id;
	private int customer_id;
	private int food_id;
	private int quantity;
	private Timestamp ordered_date;
	private String food_name;
	private float price;
	private String image_url;

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getOrdered_date() {
		return ordered_date;
	}

	public void setOrdered_date(Timestamp ordered_date) {
		this.ordered_date = ordered_date;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Orders() {}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", customer_id=" + customer_id + ", food_id=" + food_id + ", quantity="
				+ quantity + ", ordered_date=" + ordered_date + "]";
	}

	
	
}
