package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thakkali.dao.OrderDao;
import com.thakkali.model.Orders;

public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root";

    public PlaceOrderServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password); 
		}
		catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
	}

	public void destroy() {
		try { connection.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(); 
			@SuppressWarnings("unchecked")
			ArrayList<Orders> history = (ArrayList<Orders>) session.getAttribute("history");
			
			Orders[] orders = (Orders[]) session.getAttribute("orders");
			String[] names = (String[]) session.getAttribute("names");
			float[] prices = (float[]) session.getAttribute("prices");
			int[] quantities = new int[names.length];
			
			for (int i = 0; i < orders.length; i++) {
				quantities[i] = Integer.parseInt(request.getParameter(names[i]));
				orders[i].setQuantity(quantities[i]);
				prices[i] *= orders[i].getQuantity();
				orders[i].setFood_name(names[i]);
				orders[i].setPrice(prices[i]);
				
				history.add(orders[i]);
			}
			
			OrderDao.updateOrder(connection, orders);
			
			session.setAttribute("orders", orders);
			session.setAttribute("prices", prices);
			session.setAttribute("quantities", quantities);
			session.setAttribute("history", history);
			
			response.sendRedirect("order_placed.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
