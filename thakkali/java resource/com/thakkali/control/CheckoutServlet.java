package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thakkali.dao.OrderDao;
import com.thakkali.model.CheckOut;
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
       
    public CheckoutServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
	}

	public void destroy() {
		try {
			connection.close();			
		} catch (SQLException e) { e.printStackTrace(); } 
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String foodCart = new String(request.getParameter("food_cart").getBytes("iso-8859-1"), "UTF-8");
		CheckOut[] items = new Gson().fromJson(foodCart, CheckOut[].class);
		User user = (User) request.getSession().getAttribute("user");
		
		int orderId = OrderDao.placeOrder(connection, user.getUser_id()); 
		
		for (int i = 0; i < items.length; i++) 
			items[i].setOrder_id(orderId);
		
		OrderDao.updateOrder(connection, items);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
