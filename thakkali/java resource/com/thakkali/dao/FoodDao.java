package com.thakkali.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.thakkali.model.Foods;
import com.thakkali.model.Orders;

public class FoodDao {
	//Add Food
	private final static String add_food_query = 
			"insert into "
			+ "food_table (manager_id, food_name, food_category,"
			+ "food_description, unit_price, image_url, image_alt) "
			+ "values  (?, ?, ?, ?, ?, ?, ?);";
	//Delete Food
	private final static String delete_food_query =
			"delete from "
			+ "food_table "
			+ "where food_id = ? and manager_id = ?;";
	//Get Food
	private final static String get_food_query = 
			"select * "
			+ "from food_table "
			+ "order by food_name;";
	//Get Menu
	private final static String get_menu_query = 
			"select * "
			+ "from food_table "
			+ "where manager_id = ? "
			+ "order by food_name;";
	//Get food history
	private final static String get_food_history_query =
			"select food_name, unit_price "
			+ "from food_table "
			+ "where food_id = ?;";
	//Get food by name
	private final static String get_food_by_name_query = 
			"select * "
			+ "from food_table "
			+ "where food_name = ?;";
	
	public FoodDao() {}
	
	public static void addFood(Connection connection, Foods food) {
		try {
			
			PreparedStatement add_food = connection.prepareStatement(add_food_query);
			add_food.setInt(1, food.getManager_id());
			add_food.setString(2, food.getFood_name());
			add_food.setString(3, food.getFood_category());
			add_food.setString(4, food.getDescription());
			add_food.setDouble(5, food.getPrice());
			add_food.setString(6, food.getImage_url());
			add_food.setString(7, food.getImage_alt());
			
			add_food.executeUpdate();
			add_food.close();
			
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public static void deleteFood(Connection connection, int food_id, int manger_id) {
		try {
			
			PreparedStatement delete_food = connection.prepareStatement(delete_food_query);
			delete_food.setInt(1, food_id);
			delete_food.setInt(2, manger_id);
			
			delete_food.executeUpdate();
			delete_food.close();
			
		} catch (SQLException e) { e.printStackTrace(); } 
	}
	
	public static void deleteFoods(Connection connection, int[] food_ids, int manager_id) {
		for (int food_id: food_ids) 
			FoodDao.deleteFood(connection, food_id, manager_id);
	}
	
	public static ArrayList<Foods> getFoods(Connection connection) {
		ArrayList<Foods> foods = new ArrayList<>();
		
		try {
			
			Statement get_food = connection.createStatement();
			ResultSet results = get_food.executeQuery(get_food_query);
			
			while (results.next()) {
				Foods food = new Foods();
				
				food.setFood_id(results.getInt(1));
				food.setManager_id(results.getInt(2));
				food.setFood_name(results.getString(3));
				food.setFood_category(results.getString(4));
				food.setDescription(results.getString(5));
				food.setPrice(results.getFloat(6));
				food.setImage_url(results.getString(7));
				food.setImage_alt(results.getString(8));
				
				foods.add(food);
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return foods;
	}
	
	public static ArrayList<Foods> getFoodsForManagers(Connection connection, int manager_id) {
		ArrayList<Foods> foods = new ArrayList<>();
		
		try {
			
			PreparedStatement get_food = connection.prepareStatement(get_menu_query);
			get_food.setInt(1, manager_id);
			ResultSet results = get_food.executeQuery();
			
			while (results.next()) {
				Foods food = new Foods();
				
				food.setFood_id(results.getInt(1));
				food.setManager_id(results.getInt(2));
				food.setFood_name(results.getString(3));
				food.setFood_category(results.getString(4));
				food.setDescription(results.getString(5));
				food.setPrice(results.getFloat(6));
				food.setImage_url(results.getString(7));
				food.setImage_alt(results.getString(8));
				
				foods.add(food);
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return foods;
	}

	public static void getFoodForHistory(Connection connection, Orders order) {
		
		try {
			
			PreparedStatement get_food_history = connection.prepareStatement(get_food_history_query);
			get_food_history.setInt(1, order.getFood_id());
			ResultSet result = get_food_history.executeQuery();
			
			if (result.next()) {
				order.setFood_name(result.getString("food_name"));
				order.setPrice(order.getQuantity() * result.getFloat("unit_price"));
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
	}
	
	public static Foods getFoodByName(Connection connection, String name) {
		
		Foods food = new Foods();
		
		try {
			
			PreparedStatement query = connection.prepareStatement(get_food_by_name_query);
			query.setString(1, name);
			ResultSet result = query.executeQuery();
			
			if (result.next()) {
				food.setFood_id(result.getInt("food_id"));
				food.setFood_name(result.getString("food_name"));
				food.setManager_id(result.getInt("manager_id"));
				food.setFood_category(result.getString("food_category"));
				food.setDescription(result.getString("food_description"));
				food.setPrice(result.getFloat("unit_price"));
				food.setImage_url(result.getString("image_url"));
				food.setImage_alt(result.getString("image_alt"));
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return food;
		
	}
}
