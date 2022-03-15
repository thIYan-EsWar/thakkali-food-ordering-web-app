package com.thakkali.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.thakkali.model.Hotels;

public class HotelDao {
	//private static 
	//Count hotels
	private static final String get_hotels_count_query =
			"select count(manager_id) "
			+ "from hotel_table;";	
	//Get Hotel
	private static final String get_hotels_query =
			"select * "
			+ "from hotel_table "
			+ "limit ?, ?;";
	//Update Hotel Image
	private static final String update_hotel_image_query =
			"update hotel_table "
			+ "set hotel_image = ? "
			+ "where manager_id = ?;";
	//add hotel with default image
	private static final String insert_hotel_default_query = 
			"insert into "
					+ "hotel_table (manager_id, hotel_name, hotel_address) "
					+ "values (?, ?, ?);";
	//add hotels
	private static final String insert_hotel_query = 
			"insert into "
			+ "hotel_table (manager_id, hotel_name, hotel_address, hotel_image) "
			+ "values (?, ?, ?, ?);";
	
	public HotelDao() {}
	
	public static void insertHotelDefaultInfo(Connection connection, Hotels hotel) {
		
		try {
			 
			PreparedStatement insert_hotel = connection.prepareStatement(insert_hotel_default_query);
			insert_hotel.setInt(1, hotel.getManager_id());
			insert_hotel.setString(2, hotel.getHotel_name());
			insert_hotel.setString(3, hotel.getHotel_address());
			
			insert_hotel.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace(); }
		
	}	
	
	public static void insertHotelInfo(Connection connection, Hotels hotel) {
		
		try {
			 
			PreparedStatement insert_hotel = connection.prepareStatement(insert_hotel_query);
			insert_hotel.setInt(1, hotel.getManager_id());
			insert_hotel.setString(2, hotel.getHotel_name());
			insert_hotel.setString(3, hotel.getHotel_address());
			insert_hotel.setString(4, hotel.getHotel_image());
			
			insert_hotel.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace(); }
		
	}
	
	public static int getHotelsCount(Connection connection) {
		int hotels_count = 0;
		
		try {
			Statement count_hotels = connection.createStatement();
			ResultSet result = count_hotels.executeQuery(get_hotels_count_query);	
			
			
			if (result.next()) hotels_count = result.getInt(1);
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return hotels_count;
	}
	
	public static ArrayList<Hotels> getHotels(Connection connection, int start, int itemsPerPage) {
		ArrayList<Hotels> hotels = new ArrayList<>();
		
		try {
			
			PreparedStatement get_hotels = connection.prepareStatement(get_hotels_query);
			
			get_hotels.setInt(1, start);
			get_hotels.setInt(2, itemsPerPage);
			
			ResultSet results = get_hotels.executeQuery();
			
			while (results.next()) {
				Hotels hotel = new Hotels();
				hotel.setManager_id(results.getInt("manager_id"));
				hotel.setHotel_name(results.getString("hotel_name"));
				hotel.setHotel_address(results.getString("hotel_address"));
				hotel.setHotel_image(results.getString("hotel_image"));
				
				hotels.add(hotel);
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		
		return hotels;
		
	}
	
	public static void updateHotelImage(Connection connection, String image_path, int manager_id) {
		try {
			
			PreparedStatement update_hotel_image = connection.prepareStatement(update_hotel_image_query);
			update_hotel_image.setString(1, image_path);
			update_hotel_image.setInt(2, manager_id);
			
			update_hotel_image.executeUpdate();
			
			
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
}