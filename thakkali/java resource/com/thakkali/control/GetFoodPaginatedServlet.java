package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thakkali.dao.FoodDao;
import com.thakkali.model.Foods;
import com.thakkali.utils.JDBCConnection;
import com.thakkali.utils.JSONResponseTemplate;

public class GetFoodPaginatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;

    public GetFoodPaginatedServlet() {
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
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int foodsPerPage = Integer.parseInt(request.getParameter("items"));
		
		int totalFoods = FoodDao.getTotalFoods(connection);
		
		JSONResponseTemplate<Foods> apiResponse = new JSONResponseTemplate<> ();
		apiResponse.setCurrent_page(pageNumber);
		apiResponse.setItems_per_page(foodsPerPage);
		apiResponse.setStart(pageNumber + foodsPerPage);
		apiResponse.setTotal_items(totalFoods);
		
		apiResponse.setData(FoodDao.getFoodsPagination(connection, pageNumber, foodsPerPage));
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(apiResponse));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
