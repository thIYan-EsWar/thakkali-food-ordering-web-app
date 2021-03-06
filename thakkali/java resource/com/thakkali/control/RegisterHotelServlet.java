package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thakkali.dao.HotelDao;
import com.thakkali.model.Hotels;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

public class RegisterHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;

    public RegisterHotelServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Hotels hotel = new Hotels();
		User manager = (User) request.getSession().getAttribute("user");
		
		hotel.setManager_id(manager.getUser_id());
		hotel.setHotel_name(request.getParameter("hotel_name"));
		hotel.setHotel_address(request.getParameter("address"));
		
//		System.out.println(hotel);
		
		if (request.getAttribute("hotel_image_path") != null) {
			hotel.setHotel_image((String) request.getAttribute("hotel_image_path"));
			HotelDao.insertHotelInfo(connection, hotel);
		
		} else {
			HotelDao.insertHotelDefaultInfo(connection, hotel);			
		}
		
		response.sendRedirect("home.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
