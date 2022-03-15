package com.thakkali.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.thakkali.dao.FoodDao;
import com.thakkali.model.Foods;
import com.thakkali.model.User;
import com.thakkali.utils.JDBCConnection;

@MultipartConfig
public class FoodsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String SAVE_PATH = "";
	private static Connection connection;
 
	public FoodsAddServlet() {
        super();
    }    

	public void init(ServletConfig config) throws ServletException {
		connection = JDBCConnection.getDatabaseConnection();
		SAVE_PATH = config.getInitParameter("food_image_src");
	}

	public void destroy() {
		try {
			connection.close();			
		} catch (SQLException e) { e.printStackTrace(); } 
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Foods food = new Foods();
		User user = (User) request.getSession().getAttribute("user");
		
		String uploadPath = "";
//		int maxMemSize = 50 * 1024;
//		int maxFileSize = 50 * 1024;
		
		if (request.getParameter("admin_image_radio").equals("admin_image_url")) {
			uploadPath = request.getParameter("admin_image_url");
			
		} else {
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			factory.setSizeThreshold(maxMemSize);
			
			ServletFileUpload uploadManager = new ServletFileUpload(factory);
//			uploadManager.setSizeMax(maxFileSize);
			
			try {
				
				List<FileItem> fileItems = uploadManager.parseRequest(request);
				Iterator<FileItem> i = fileItems.iterator();
				
				File file;
				
				while (i.hasNext()) {
					FileItem fileItem = (FileItem) i.next();
					
					if (fileItem.isFormField()) {
						String fileName = fileItem.getName();
						
	                if( fileName.lastIndexOf("\\") >= 0 ) 
	                   file = new File( SAVE_PATH + fileName.substring( fileName.lastIndexOf("\\"))) ;
	                else 
	                   file = new File( SAVE_PATH + fileName.substring(fileName.lastIndexOf("\\") + 1)) ;
					
					fileItem.write(file);
					}
				}
				
//				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
//				List<FileItem> files = servletFileUpload.parseRequest(request);
//				
//				for (FileItem file: files) {
//					file.write(new File(SAVE_PATH + file.getName()));
//					uploadPath = SAVE_PATH + file.getName();
//					
//					System.out.println(uploadPath);
//				}
//				
			
			} catch (Exception e) { e.printStackTrace(); }
		}
		
		
		food.setFood_name(request.getParameter("food_name"));
		food.setFood_category(request.getParameter("food_category"));
		food.setDescription(request.getParameter("food_description"));
		food.setPrice(Float.parseFloat(request.getParameter("unit_price")));
		food.setImage_alt(request.getParameter("image_alt"));
		food.setAvailability(Integer.parseInt(request.getParameter("availability")));
		
		food.extendFromUser(user);
		
		food.setImage_url(uploadPath);
		
		FoodDao.addFood(connection, food);
		request.getSession().setAttribute("menu", FoodDao.getFoodsForManagers(connection, user.getUser_id()));
		response.sendRedirect("d_admin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
