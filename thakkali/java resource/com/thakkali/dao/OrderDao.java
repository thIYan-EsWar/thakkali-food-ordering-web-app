package com.thakkali.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.thakkali.model.CheckOut;
import com.thakkali.model.Orders;

public class OrderDao {
	//Place order
	private final static String place_order_query = 
			"insert into "
			+ "order_table (customer_id) "
			+ "values (?);";
	//Update order details
	private final static String update_order_query = 
			"insert into "
			+ "order_details "
			+ "values (?, ?, ?, ?, ?, ?, ?);";
	//Get order id
	private final static String get_order_id_query = 
			"select order_id "
			+ "from order_table "
			+ "where customer_id = ? "
			+ "order by order_id desc "
			+ "limit 1;";
	//Get order ids
	private final static String get_order_ids_query =
			"select order_id "
			+ "from order_table "
			+ "where customer_id = ? "
			+ "limit ?, 5;";
	//Get order history
	private final static String get_order_history_query =
			"select * "
			+ "from order_details "
			+ "where order_id in ("
				+ "select order_id "
				+ "from order_table "
				+ "where order_id = ?);";
	//Get Order Count 
	private final static String get_total_orders_query = 
			"select count(order_id) "
			+ "from order_table;";
	
	public OrderDao() {}
	
	public static int placeOrder(Connection connection, int customer_id) {
		int order_id = 0;
		
		try {
			
			PreparedStatement place_order = connection.prepareStatement(place_order_query);
			place_order.setInt(1, customer_id);
			place_order.executeUpdate();
			
			PreparedStatement get_order_id = connection.prepareStatement(get_order_id_query);
			get_order_id.setInt(1, customer_id);
			ResultSet result = get_order_id.executeQuery();
			
			if (result.next()) 
				order_id = result.getInt("order_id");
	
			
		} catch (SQLException e) { e.printStackTrace(); } 
		
		return order_id;
	}
	
	public static Orders[] updateOrder(Connection connection, Orders[] foods) {
		
		try {
			
			for (int i = 0; i < foods.length; i++) {
				
				PreparedStatement update_query = connection.prepareStatement(update_order_query);
				update_query.setInt(1, foods[i].getOrder_id());
				update_query.setFloat(2, foods[i].getFood_id());
				update_query.setInt(3, foods[i].getQuantity());
				update_query.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				update_query.setString(5, foods[i].getFood_name());
				update_query.setFloat(6, foods[i].getPrice() * foods[i].getQuantity());
				
				update_query.executeUpdate();

			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return foods;
	}
	
	public static void updateOrder(Connection connection, CheckOut[] foods) {
		
		try {
			
			for (int i = 0; i < foods.length; i++) {
				
				PreparedStatement update_query = connection.prepareStatement(update_order_query);
				update_query.setInt(1, foods[i].getOrder_id());
				update_query.setFloat(2, foods[i].getId());
				update_query.setInt(3, foods[i].getQuantity());
				update_query.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				update_query.setString(5, foods[i].getName());
				update_query.setFloat(6, foods[i].getPrice());
				update_query.setString(7, foods[i].getImage_url());
				
				update_query.executeUpdate();

			}
			
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	private static ArrayList<Orders> getOrderHistory(Connection connection, int customer_id, int order_id) {
		ArrayList<Orders> orders = new ArrayList<>();
		
		try {
			
			PreparedStatement query = connection.prepareStatement(get_order_history_query);
			query.setInt(1, order_id);
			ResultSet results = query.executeQuery();
			
			while (results.next()) {
				Orders order = new Orders();
				
				order.setOrder_id(results.getInt("order_id"));
				order.setCustomer_id(customer_id);
				order.setFood_id(results.getInt("food_id"));
				order.setFood_name(results.getString("food_name"));
				order.setQuantity(results.getInt("quantity"));
				order.setOrdered_date(results.getTimestamp("ordered_date"));
				order.setPrice(results.getFloat("price"));
				order.setImage_url(results.getString("image_url"));
				
				orders.add(order);
				
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return orders;
		
	}
	
	public static ArrayList<ArrayList<Orders>> getOrderDetailsAJAX(Connection connection, int user_id, int page) {
		ArrayList<ArrayList<Orders>> orderHistories = new ArrayList<>();
		
		try {
		
		PreparedStatement getOrderIDs = connection.prepareStatement(get_order_ids_query);
		getOrderIDs.setInt(1, user_id);
		getOrderIDs.setInt(2, page);
		
		ResultSet results = getOrderIDs.executeQuery();
		
		while (results.next()) {
			orderHistories.add(OrderDao.getOrderHistory(connection, user_id, results.getInt(1)));
		}
		
		} catch (SQLException e) { e.printStackTrace(); } 
		
		
		return orderHistories;
	}

	public static int getTotalOrders(Connection connection) {
		
		try {
			
			ResultSet result = connection.createStatement().executeQuery(get_total_orders_query);
			if (result.next()) return result.getInt(1);
			
		} catch (SQLException e) { e.printStackTrace(); } 
		
		return 0;
	}
	

}
