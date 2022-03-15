package com.thakkali.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.thakkali.dao.OrderDao;
import com.thakkali.dao.UserDao;
import com.thakkali.model.Customer;
import com.thakkali.model.Orders;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection connection;
	
    public RegisterServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session = request.getSession();
		
		user.setUser_name(request.getParameter("name"));
		user.setEmail_id(request.getParameter("email_id"));
		user.setAddress(request.getParameter("address"));
		user.setContact_number(Long.parseLong(request.getParameter("contact_number")));
		int user_type = Integer.parseInt(request.getParameter("user_type"));
		
		user.setUser_type(user_type);
		user.setPassword(request.getParameter("password"));
		
		if (UserDao.getUserID(connection, user.getEmail_id()) != 0){
			request.getSession().setAttribute("error", "Email id already in use!");
			response.sendRedirect("home.jsp");
		}
		else {
			UserDao.createUser(connection, user);
			user.setUser_id(UserDao.getUserID(connection, user.getEmail_id()));
			
			session.setAttribute("user", user);
			session.setAttribute("success", "");
			
			if (user_type == 1) {
				Customer customer = new Customer();
				customer.extendFromUser(user);
				
//				UserDao.updateCustomer(connection, customer);
				response.sendRedirect("home.jsp");
				
			} else if (user_type == 2) {
//				Manager manager = new Manager();
//				manager.extendFromUser(user);
//				
//				request.getSession().setAttribute("manager", manager);
//				response.sendRedirect("home.jsp");
				
				
				if (request.getParameter("image_radio").equals("image_upload")) {
					
					Part imagePart = request.getPart("image_upload");
					String imageName = imagePart.getSubmittedFileName();
					
					String uploadPath = "client_resourses/images/hotel_images/" + imageName;
					
					FileOutputStream fileOutputStream = new FileOutputStream(uploadPath);
					
					InputStream inputStream = imagePart.getInputStream();
					
					byte[] imageByte = new byte[inputStream.available()];
					inputStream.read(imageByte);
					
					fileOutputStream.write(imageByte);
					fileOutputStream.close();
					
					request.setAttribute("hotel_image_path", uploadPath);
					
				} else {
					request.setAttribute("hotel_image_path", request.getParameter("image_url"));
				}
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register_hotel");
				requestDispatcher.forward(request, response);
				
			} else {
				request.getSession().setAttribute("error", "Invalid user type");
				
				session.removeAttribute("user");
				
				response.sendRedirect("home.jsp");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
