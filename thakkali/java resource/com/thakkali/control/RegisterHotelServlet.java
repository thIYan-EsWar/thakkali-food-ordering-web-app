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
import com.thakkali.model.Manager;

public class RegisterHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root";

    public RegisterHotelServlet() {
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
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		
		manager.setHotel_name(request.getParameter("hotel_name"));
		manager.setHotel_address(request.getParameter("hotel_address"));
		
		UserDao.updateManager(connection, manager);
		
		request.getSession().removeAttribute("manager");
		response.sendRedirect("login.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
