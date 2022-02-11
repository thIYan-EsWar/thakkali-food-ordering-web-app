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

import com.thakkali.dao.FoodDao;
import com.thakkali.dao.OrderDao;
import com.thakkali.dao.UserDao;
import com.thakkali.model.Foods;
import com.thakkali.model.Orders;
import com.thakkali.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root";
 
    public LoginServlet() {
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
		String email_id = request.getParameter("email_id");
		String password = request.getParameter("password");
		
		String result = UserDao.loginUser(connection, email_id, password);
		User user = UserDao.getUser(connection, email_id);
		HttpSession session = request.getSession();
		
		if (result.equals("1")) {
			ArrayList<Foods> foods = FoodDao.getFoods(connection);
			session.setAttribute("user", user);
			session.setAttribute("foods", foods);
			
			ArrayList<Orders> history = OrderDao.getOrderHistory(connection, user.getUser_id());
			
			for (int i = 0; i < history.size(); i++) 
				FoodDao.getFoodForHistory(connection, history.get(i));
			
			session.setAttribute("history", history);
			response.sendRedirect("home.jsp");
			
		} else if (result.equals("2")) {
			ArrayList<Foods> foods = FoodDao.getFoodsForManagers(connection, user.getUser_id());
			session.setAttribute("user", user);
			session.setAttribute("menu", foods);
			
			response.sendRedirect("home.jsp");
			
		} else {
			session.setAttribute("error", result);
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
