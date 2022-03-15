package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thakkali.dao.OrderDao;
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class OrderFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	
    public OrderFoodServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] values = request.getParameterValues("explore_foods");
		
		Orders[] orders = new Orders[values.length];
		String[] names = new String[values.length];
		float[] prices = new float[values.length];
		int[] availabilities = new int[values.length];
		
		HttpSession session = request.getSession();
		
		User customer = (User) session.getAttribute("user");
		int food_id = 0, i = 0;
		int order_id = OrderDao.placeOrder(connection, customer.getUser_id());
		
		for (String value: values) {
			orders[i] = new Orders();
			
			String[] s_values = value.split(",");
			
			food_id = Integer.parseInt(s_values[0]);
			names[i] = s_values[1];
			prices[i] = Float.parseFloat(s_values[2]);
			availabilities[i] = Integer.parseInt(s_values[3]);
			
			orders[i].setCustomer_id(customer.getUser_id());
			orders[i].setOrder_id(order_id);
			orders[i].setFood_id(food_id);
			orders[i].setOrdered_date(Timestamp.valueOf(LocalDateTime.now()));
			
			i++;
		}
		
		session.setAttribute("orders", orders);
		session.setAttribute("names", names);
		session.setAttribute("prices", prices);
		session.setAttribute("availabilities", availabilities);
		response.sendRedirect("confirm_order.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
