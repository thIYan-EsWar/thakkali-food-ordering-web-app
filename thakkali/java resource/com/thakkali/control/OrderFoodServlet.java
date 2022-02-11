package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

public class OrderFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/thakkali";
	private static final String username = "root";
	private static final String password = "root";

    public OrderFoodServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password); 
		}
		catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
	}

	public void destroy() {
		try { connection.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] values = request.getParameterValues("explore_foods");
		
		Orders[] orders = new Orders[values.length];
		String[] names = new String[values.length];
		float[] prices = new float[values.length];
		
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
			
			orders[i].setCustomer_id(customer.getUser_id());
			orders[i].setOrder_id(order_id);
			orders[i].setFood_id(food_id);
			orders[i].setOrdered_date(Timestamp.valueOf(LocalDateTime.now()));
			
			i++;
		}
		
		session.setAttribute("orders", orders);
		session.setAttribute("names", names);
		session.setAttribute("prices", prices);
		response.sendRedirect("confirm_order.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
