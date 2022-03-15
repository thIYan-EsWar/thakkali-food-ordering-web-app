package com.thakkali.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thakkali.dao.HotelDao;
import com.thakkali.model.Hotels;
import com.thakkali.utils.JDBCConnection;
import com.thakkali.utils.JSONResponseTemplate;

@WebServlet(asyncSupported = true, urlPatterns = { "/get_hotel" })
public class GetHotelPaginatedServlet extends HttpServlet {
	private static Connection connection;
	private static final long serialVersionUID = 1L;

    public GetHotelPaginatedServlet() {
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
		int page = Integer.parseInt(request.getParameter("page"));
		int itemsPerPage = Integer.parseInt(request.getParameter("items"));
		
		JSONResponseTemplate<Hotels> hotels = new JSONResponseTemplate<>();
		hotels.setItems_per_page(itemsPerPage);
		hotels.setCurrent_page(page);
		hotels.setStart(itemsPerPage * page);
		
		hotels.setTotal_items(HotelDao.getHotelsCount(connection));
		
		hotels.setData(HotelDao.getHotels(connection, page, itemsPerPage));
		
		response.getWriter().write(new Gson().toJson(hotels));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
