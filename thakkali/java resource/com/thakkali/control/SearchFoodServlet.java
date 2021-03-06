package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thakkali.dao.FoodDao;
import com.thakkali.utils.JDBCConnection;

public class SearchFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;

    public SearchFoodServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().setAttribute("searched_item", FoodDao.getFoodByName(connection, request.getParameter("food_name")));
			response.sendRedirect("d_foods.jsp");
		} else {
			System.out.println("In search food servlet");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
