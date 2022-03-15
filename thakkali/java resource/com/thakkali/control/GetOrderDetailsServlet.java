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
import com.thakkali.dao.OrderDao;
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;
import com.thakkali.utils.JSONResponseTemplate;

public class GetOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Connection connection; 
    
    public GetOrderDetailsServlet() {
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
		
		int start = Integer.parseInt(request.getParameter("page"));
		User user = (User) request.getSession().getAttribute("user");
		JSONResponseTemplate<ArrayList<Orders>> jsonResponse = new JSONResponseTemplate<>();
		
		jsonResponse.setCurrent_page(start / 5 + 1);
		jsonResponse.setItems_per_page(5);
		jsonResponse.setStart(start);
		jsonResponse.setTotal_items(OrderDao.getTotalOrders(connection));
		jsonResponse.setData(OrderDao.getOrderDetailsAJAX(connection, user.getUser_id(), start - 1));
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(jsonResponse));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
