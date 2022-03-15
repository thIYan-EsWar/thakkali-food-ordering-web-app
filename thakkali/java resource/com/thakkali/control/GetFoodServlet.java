package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thakkali.dao.FoodDao;
import com.thakkali.model.Foods;
import com.thakkali.utils.JDBCConnection;

public class GetFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

    public GetFoodServlet() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Foods> foods = FoodDao.getFoods(connection);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(foods));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
