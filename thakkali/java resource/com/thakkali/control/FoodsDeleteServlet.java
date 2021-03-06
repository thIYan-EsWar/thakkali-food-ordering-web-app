package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thakkali.dao.FoodDao;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class FoodsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	
    public FoodsDeleteServlet() {
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
		String[] s_food_ids = request.getParameterValues("select_foods");
		int[] food_ids = new int[s_food_ids.length];
		HttpSession session = request.getSession();
		
		User manager = (User) session.getAttribute("user");
		
		for (int i = 0; i < food_ids.length; i++) {
			food_ids[i] = Integer.parseInt(s_food_ids[i]);
		}
		
		FoodDao.deleteFoods(connection, food_ids, manager.getUser_id());
		session.setAttribute("menu", FoodDao.getFoodsForManagers(connection, manager.getUser_id()));
		response.sendRedirect("d_admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
