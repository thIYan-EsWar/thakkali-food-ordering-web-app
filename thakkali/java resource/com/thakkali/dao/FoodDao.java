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
			+ "food_table (food_name, food_category,"
			+ "food_description, unit_price, image_url, image_alt, availability, manager_id) "
			+ "values  (?, ?, ?, ?, ?, ?, ?, ?);";
	//Delete Food
	private final static String delete_food_query =
			"delete from "
			+ "food_table "
			+ "where food_id = ? and manager_id = ?;";
	//Total Food count
	private final static String get_total_foods_query =
			"select count(food_id) "
			+ "from food_table;";
	
	//Get Food
	private final static String get_food_query = 
			"select * "
			+ "from food_table "
			+ "order by food_name;";
	
	//Get food pagination
	private final static String get_food_pagination_query =
			"select * "
			+ "from food_table "
			+ "order by food_name "
			+ "limit ?, ?";
	//Get Menu
	private final static String get_menu_query = 
			"select * "
			+ "from food_table "
			+ "where manager_id = ?;";
	//Get food history
	private final static String get_food_history_query =
			"select food_name, unit_price "
			+ "from food_table "
			+ "where food_id = ?;";
	//Get food by name
	private final static String get_food_by_name_query = 
			"select * "
			+ "from food_table "
			+ "where food_name = ?";
	
	//Update Quantity
	private final static String update_food_count_query =
			"update food_table "
			+ "set availability = ? "
			+ "where food_id = ?;";
	
	//Get current food count
	private final static String get_food_count_query = 
			"select availability "
			+ "from food_table "
			+ "where food_id = ?;";
	
	public FoodDao() {}
	
	public static void addFood(Connection connection, Foods food) {
		try {
			
			PreparedStatement add_food = connection.prepareStatement(add_food_query);
			add_food.setInt(8, food.getManager_id());
			add_food.setString(1, food.getFood_name());
			add_food.setString(2, food.getFood_category());
			add_food.setString(3, food.getDescription());
			add_food.setDouble(4, food.getPrice());
			add_food.setString(5, food.getImage_url());
			add_food.setString(6, food.getImage_alt());
			add_food.setInt(7, food.getAvailability());
			
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
	
	public static int getTotalFoods(Connection connection) {
		int totalFoods = 0;
		
		try {
			ResultSet result = connection.createStatement().executeQuery(get_total_foods_query);
			
			if (result.next()) 
				totalFoods = result.getInt(1);
			
			return totalFoods;
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return totalFoods;
	}
	
	public static ArrayList<Foods> getFoods(Connection connection) {
		ArrayList<Foods> foods = new ArrayList<>();
		
		try {
			
			Statement get_food = connection.createStatement();
			ResultSet results = get_food.executeQuery(get_food_query);
			
			while (results.next()) {
				Foods food = new Foods();
				
				food.setFood_id(results.getInt(1));
				food.setManager_id(results.getInt(9));
				food.setFood_name(results.getString(2));
				food.setFood_category(results.getString(3));
				food.setDescription(results.getString(4));
				food.setPrice(results.getFloat(5));
				food.setImage_url(results.getString(6));
				food.setImage_alt(results.getString(7));
				food.setAvailability(results.getInt(8));
				
				foods.add(food);
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return foods;
	}
	
	public static ArrayList<Foods> getFoodsPagination(Connection connection, int pageNumber, int foodsPerPage) {
		ArrayList<Foods> foods = new ArrayList<>();
		
		
		try {
			PreparedStatement get_food = connection.prepareStatement(get_food_pagination_query);
			get_food.setInt(1, pageNumber);
			get_food.setInt(2, foodsPerPage);
			
			ResultSet results = get_food.executeQuery();
			
			while (results.next()) {
				Foods food = new Foods();
				
				food.setFood_id(results.getInt(1));
				food.setManager_id(results.getInt(9));
				food.setFood_name(results.getString(2));
				food.setFood_category(results.getString(3));
				food.setDescription(results.getString(4));
				food.setPrice(results.getFloat(5));
				food.setImage_url(results.getString(6));
				food.setImage_alt(results.getString(7));
				food.setAvailability(results.getInt(8));
				
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
				food.setManager_id(results.getInt(9));
				food.setFood_name(results.getString(2));
				food.setFood_category(results.getString(3));
				food.setDescription(results.getString(4));
				food.setPrice(results.getFloat(5));
				food.setImage_url(results.getString(6));
				food.setImage_alt(results.getString(7));
				food.setAvailability(results.getInt(8));
				
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
	
	public static void updateFoodCount(Connection connection, int food_id, int current_count) {
		try {
			
			PreparedStatement update_food_count = connection.prepareStatement(update_food_count_query);
			update_food_count.setInt(1, current_count);
			update_food_count.setInt(2, food_id);
			
			update_food_count.executeUpdate();
			update_food_count.close();
			
		} catch (SQLException e) { e.printStackTrace(); } 
	}
	
	public static int getFoodCount(Connection connection, int food_id) {
		int food_count = 0;
		
		try {
			
			PreparedStatement query = connection.prepareStatement(get_food_count_query);
			query.setInt(1, food_id);
			
			ResultSet result = query.executeQuery();
			
			if (result.next()) 
				food_count = result.getInt(1);
			
			return food_count;
			
		} catch (SQLException e) { e.printStackTrace(); } 
		
		return food_count;
	}
}
