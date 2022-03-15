package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thakkali.dao.FoodDao;
import com.thakkali.dao.OrderDao;
import com.thakkali.dao.UserDao;
import com.thakkali.model.Foods;
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
 
    public LoginServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String email_id = request.getParameter("email_id");
		String password = request.getParameter("password");
		
		
		String result = UserDao.loginUser(connection, email_id, password);
		User user = UserDao.getUser(connection, email_id);
		HttpSession session = request.getSession();
		int totalFoods = FoodDao.getTotalFoods(connection);
		
		session.setAttribute("user", user);
		ArrayList<Foods> foods = FoodDao.getFoods(connection);
		session.setAttribute("foods", foods);
		session.setAttribute("total_foods", totalFoods);
		
		if (result.equals("1")) {
			session.setAttribute("success", "");
			response.sendRedirect("home.jsp");
		}
		
		else if (result.equals("2")) {
			ArrayList<Foods> menu = FoodDao.getFoodsForManagers(connection, user.getUser_id());
			session.setAttribute("menu", menu);	
			session.setAttribute("success", "");
			response.sendRedirect("home.jsp");
			
		} else {
			session.removeAttribute("user");
			session.removeAttribute("foods");
			session.removeAttribute("total_foods");
			session.removeAttribute("history");
			session.removeAttribute("menu");
			session.setAttribute("error", result);
			
			response.sendRedirect("home.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
