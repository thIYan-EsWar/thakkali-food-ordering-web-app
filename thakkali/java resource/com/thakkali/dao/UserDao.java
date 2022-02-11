package com.thakkali.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thakkali.model.Customer;
import com.thakkali.model.Manager;
import com.thakkali.model.User;

public class UserDao {
	//Create User
	private final static String create_user_query = 
			"insert into "
			+ "user_table (user_name, email_id, address,"
			+ "contact_number, user_type)"
			+ "values (?, ?, ?, ?, ?);";
	//Create Customer
	private final static String create_customer_query = 
			"insert into "
			+ "customer_table (customer_id, password)"
			+ "values (?, ?);";
	//Create Manager
	private final static String create_manager_query = 
			"insert into "
			+ "manager_table(manager_id, password, hotel_name, hotel_address) "
			+ "values (?, ?, ?, ?);";
	//Get User type
	private final static String get_user_type_query = 
			"select user_id, user_type "
			+ "from user_table "
			+ "where email_id = ?";
	//Get Customer password
	private final static String get_customer_password_query = 
			"select password "
			+ "from customer_table "
			+ "where customer_id = ?";
	//Get Manager password
	private final static String get_manager_password_query = 
			"select password "
			+ "from manager_table "
			+ "where manager_id = ?";
	//Get User
	private final static String get_user_query =
			"select * "
			+ "from user_table "
			+ "where email_id = ?";

    public UserDao() {}
    
    public static int getUserID(Connection connection, String email_id) {
    	int user_id = 0;
    	
    	try {
			PreparedStatement user_id_sql = connection.prepareStatement(get_user_type_query);
			user_id_sql.setString(1, email_id);
			ResultSet result = user_id_sql.executeQuery();
			
			if (result.next()) 
				user_id = result.getInt("user_id");
			
		} catch (SQLException e) { e.printStackTrace(); }
    	
    	return user_id;
    }
    
    public static User getUser(Connection connection, String email_id) {
    	User user = new User();
    	
    	try {
    		
    		PreparedStatement get_user = connection.prepareStatement(get_user_query);
    		get_user.setString(1, email_id);
    		ResultSet result = get_user.executeQuery();
    		
    		if (result.next()) {
    			user.setUser_id(result.getInt(1));
    			user.setUser_name(result.getString(2));
    			user.setUser_type(result.getInt(5));
    			user.setContact_number(result.getLong(6));
    			user.setEmail_id(result.getString(3));
    			user.setAddress(result.getString(4));
    			
    			return user;
    		}
    		
    	} catch (SQLException e) { e.printStackTrace(); }
    	
    	return user;
    }
    
    public static void updateCustomer(Connection connection, Customer user) {
    	try {
			PreparedStatement create_customer = connection.prepareStatement(create_customer_query);
			create_customer.setInt(1, user.getUser_id());
			create_customer.setString(2, user.getPassword());
			
			create_customer.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace(); }
    }

	public static void createUser(Connection connection, User user) {
		try {
			PreparedStatement create_user = connection.prepareStatement(create_user_query);
			create_user.setString(1, user.getUser_name());
			create_user.setString(2, user.getEmail_id());
			create_user.setString(3, user.getAddress());
			create_user.setLong(4, user.getContact_number());
			create_user.setInt(5, user.getUser_type());
			
			create_user.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static void updateManager(Connection connection, Manager user) {
		try {
			
			PreparedStatement create_manager = connection.prepareStatement(create_manager_query);
			
			create_manager.setInt(1, user.getUser_id());
			create_manager.setString(2, user.getPassword());
			create_manager.setString(3, user.getHotel_name());
			create_manager.setString(4, user.getHotel_address());
			
			create_manager.executeUpdate();
		
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static String loginUser(Connection connection, String email_id, String password) {
		try {
			
			PreparedStatement get_user_type = connection.prepareStatement(get_user_type_query);
			get_user_type.setString(1, email_id);
			ResultSet result = get_user_type.executeQuery();
			
			if (result.next()) {
				int user_id = result.getInt("user_id");
				int user_type = result.getInt("user_type");
				String sql;
				
				if (user_type == 1) 
					sql = get_customer_password_query; 

				else sql = get_manager_password_query;
				
				PreparedStatement user_login = connection.prepareStatement(sql);
				user_login.setInt(1, user_id);
				ResultSet user_password = user_login.executeQuery();
				
				if (user_password.next()) {
					if (user_password.getString("password").equals(password)) 
						return user_type + ""; 
					
					else return "Invalid password";
				}
				
			} else { return "User does not exist"; }
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return "Query failed";
		
	}

}
