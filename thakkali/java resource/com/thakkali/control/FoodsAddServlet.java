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
import javax.servlet.http.HttpSession;

import com.thakkali.dao.FoodDao;
import com.thakkali.model.Foods;
import com.thakkali.model.User;

public class FoodsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root";
 
	public FoodsAddServlet() {
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
		Foods food = new Foods();
		
		User user = (User) request.getSession().getAttribute("user");
		
		food.setFood_name(request.getParameter("food_name"));
		food.setFood_category(request.getParameter("food_category"));
		food.setDescription(request.getParameter("food_description"));
		food.setPrice(Float.parseFloat(request.getParameter("unit_price")));
		food.setImage_url(request.getParameter("image_url"));
		food.setImage_alt(request.getParameter("image_alt"));
		
		food.extendFromUser(user);
		
		FoodDao.addFood(connection, food);
		
		HttpSession session = request.getSession();
		User manager = (User) session.getAttribute("user");
		session.setAttribute("menu", FoodDao.getFoodsForManagers(connection, manager.getUser_id()));
		
		response.sendRedirect("d_admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
