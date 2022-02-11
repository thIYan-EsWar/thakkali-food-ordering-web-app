package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thakkali.dao.UserDao;
import com.thakkali.model.Customer;
import com.thakkali.model.Manager;
import com.thakkali.model.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root"; 
	
    public RegisterServlet() {
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
		User user = new User();
		
		user.setUser_name(request.getParameter("name"));
		user.setEmail_id(request.getParameter("email_id"));
		user.setAddress(request.getParameter("address"));
		user.setContact_number(Long.parseLong(request.getParameter("contact_number")));
		int user_type = Integer.parseInt(request.getParameter("user_type"));
		user.setUser_type(user_type);
		
		if (UserDao.getUserID(connection, user.getEmail_id()) != 0){
			request.getSession().setAttribute("error", "Email id already in use!");
			response.sendRedirect("login.jsp");
		}
		else {
			UserDao.createUser(connection, user);
			user.setUser_id(UserDao.getUserID(connection, user.getEmail_id()));
			
			if (user_type == 1) {
				Customer customer = new Customer();
				customer.extendFromUser(user);
				customer.setPassword(request.getParameter("password"));
				
				UserDao.updateCustomer(connection, customer);
				response.sendRedirect("login.jsp");
			
			} else if (user_type == 2) {
				Manager manager = new Manager();
				manager.extendFromUser(user);
				manager.setPassword(request.getParameter("password"));
				
				request.getSession().setAttribute("manager", manager);
				response.sendRedirect("hotel_info.jsp");
				
			} else {
				request.getSession().setAttribute("error", "Invalid user type");
				response.sendRedirect("login.jsp");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
