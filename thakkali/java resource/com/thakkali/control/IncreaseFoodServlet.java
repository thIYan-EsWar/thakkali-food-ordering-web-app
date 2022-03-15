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


public class IncreaseFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

    public IncreaseFoodServlet() {
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
		
		String[] values = request.getParameterValues("values");
		HttpSession session = request.getSession();
		User manager = (User) session.getAttribute("user");
		
		for (String value: values) {
			String[] strValue = value.split(",");
			
			int food_id = Integer.parseInt(strValue[0]);
			String sCount = request.getParameter(strValue[0]);
			int count = Integer.parseInt(sCount.length() > 0? sCount: "0") + Integer.parseInt(strValue[1]);
			
			FoodDao.updateFoodCount(connection, food_id, count);			
		}
		
		session.setAttribute("menu", FoodDao.getFoodsForManagers(connection, manager.getUser_id()));
		response.sendRedirect("d_admin.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
