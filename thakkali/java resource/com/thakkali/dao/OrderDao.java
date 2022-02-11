package com.thakkali.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			+ "values (?, ?, ?, ?);";
	//Get order id
	private final static String get_order_id_query = 
			"select order_id "
			+ "from order_table "
			+ "where customer_id = ? "
			+ "order by order_id desc "
			+ "limit 1;";
	//Get order history
	private final static String get_order_history =
			"select * "
			+ "from order_details "
			+ "where order_id in ("
				+ "select order_id "
				+ "from order_table "
				+ "where customer_id = ?);";
	
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
				update_query.setTimestamp(4, foods[i].getOrdered_date());
				update_query.setInt(3, foods[i].getQuantity());
				
				update_query.executeUpdate();

			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return foods;
	}
	
	public static ArrayList<Orders> getOrderHistory(Connection connection, int customer_id) {
		ArrayList<Orders> previous_orders = new ArrayList<>();
		
		try {
			
			PreparedStatement query = connection.prepareStatement(get_order_history);
			query.setInt(1, customer_id);
			ResultSet results = query.executeQuery();
			
			while (results.next()) {
				Orders order = new Orders();
				
				order.setOrder_id(results.getInt("order_id"));
				order.setCustomer_id(customer_id);
				order.setFood_id(results.getInt("food_id"));
				order.setQuantity(results.getInt("quantity"));
				order.setOrdered_date(results.getTimestamp("ordered_date"));
				
				previous_orders.add(order);
				
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return previous_orders;
		
	}

}
