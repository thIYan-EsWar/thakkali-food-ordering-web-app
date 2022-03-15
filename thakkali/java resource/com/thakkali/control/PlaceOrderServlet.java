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
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;

    public PlaceOrderServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		
		connection = JDBCConnection.getDatabaseConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(); 
			@SuppressWarnings("unchecked")
			ArrayList<Orders> history = (ArrayList<Orders>) session.getAttribute("history");
			User user = (User) session.getAttribute("user");
			
			Orders[] orders = (Orders[]) session.getAttribute("orders");
			String[] names = (String[]) session.getAttribute("names");
			float[] prices = (float[]) session.getAttribute("prices");
			
			int[] quantities = new int[names.length];
			
			for (int i = 0; i < orders.length; i++) {
				String value = request.getParameter(names[i]);
				quantities[i] = Integer.parseInt(value.length()==0?"1":value);
				orders[i].setQuantity(quantities[i]);
				prices[i] *= orders[i].getQuantity();
				orders[i].setFood_name(names[i]);
				orders[i].setPrice(prices[i]);
				
				int currFoodCount = FoodDao.getFoodCount(connection, orders[i].getFood_id());
				
				FoodDao.updateFoodCount(connection, orders[i].getFood_id(), 2 * currFoodCount - quantities[i]);
				history.add(orders[i]);
			}
			
			OrderDao.updateOrder(connection, orders);
			
			if (user.getUser_type() == 2)
				session.setAttribute("menu", FoodDao.getFoodsForManagers(connection, user.getUser_id()));
			
			session.setAttribute("orders", orders);
			session.setAttribute("prices", prices);
			session.setAttribute("quantities", quantities);
			session.setAttribute("history", history);
			
			response.sendRedirect("order_placed.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
