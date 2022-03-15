package com.thakkali.utils;

import java.util.ArrayList;

public class JSONResponseTemplate<E> {
	
	private int current_page;
	private int start;
	private int items_per_page;
	private int total_items;
	private ArrayList<E> data;
	
	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	
	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getItems_per_page() {
		return items_per_page;
	}

	public void setItems_per_page(int items_per_page) {
		this.items_per_page = items_per_page;
	}

	public ArrayList<E> getData() {
		return data;
	}

	public void setData(ArrayList<E> data) {
		this.data = data;
	}

	public JSONResponseTemplate() {}

}
