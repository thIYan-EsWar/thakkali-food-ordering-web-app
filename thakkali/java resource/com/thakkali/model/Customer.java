package com.thakkali.model;

public class Customer extends User {
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Customer() {
		super();
	}
	
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
		return "Customer [password=" + password + ", user_id=" + user_id + ", user_name=" + user_name + ", email_id="
				+ email_id + ", address=" + address + ", contact_number=" + contact_number + ", user_type=" + user_type
				+ "]";
	}

}
