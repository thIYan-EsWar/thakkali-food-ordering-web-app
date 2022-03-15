package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thakkali.dao.FoodDao;
import com.thakkali.model.Foods;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;
import com.thakkali.utils.JSONResponseTemplate;

@WebServlet("/get_food_4_admin")
public class GetFoodForManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	public GetFoodForManagerServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		User manager = (User) request.getSession().getAttribute("user");
		
		JSONResponseTemplate<Foods> foodList = new JSONResponseTemplate<>();
		foodList.setData(FoodDao.getFoodsForManagers(connection, manager.getUser_id()));
		
		response.getWriter().write(new Gson().toJson(foodList));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
