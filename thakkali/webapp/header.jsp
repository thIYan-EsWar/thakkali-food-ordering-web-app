<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.thakkali.model.User" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Header</title>
</head>
<body>
        <section class="container">
        <div class="logo">
            <ul>
                <li><img src="Images/logo.png" alt="logo"></li>
                <div class="text-right nav-bar-style">
                	<li><a href="home.jsp" title="Home" class="list-content-style">Home</a></li>
	                
	                <%
	                if (session.getAttribute("user") == null) {
	                    out.println("<li><a href='login.jsp' title='Login/Register' class='list-content-style'>Foods</a></li>");
	                } else {
	                    User user = (User) session.getAttribute("user");
	                    if (user.getUser_type() == 1) {
	                        out.println("<li><a href='d_foods.jsp' title='Foods' class='list-content-style'>Foods</a></li>");
	                        out.println("<li><a href='history.jsp' title='Order History' class='list-content-style'>Order History</a></li>");
	                    } else {
	                        out.println("<li><a href='d_admin.jsp' title='Admin' class='list-content-style'>Admin</a></li>");
	                    }
	                }
	                
	                %>
	                
	                <li><a href="about_us.jsp" title="About Us" class='list-content-style'>About Us</a></li>
	                <li><a href="#contacts" title="Contact Us" class='list-content-style'>Contact Us</a></li>
	                
	                <%
	                    if (session.getAttribute("user") == null) {
	                        out.println("<li><a href='login.jsp' title='Login/Register' class='list-content-style'>Login/Register</a></li>");
	                    } else {
	                        out.println("<li><a href='logout.jsp' title='Logout' class='list-content-style'>Logout</a></li>");
	                    }
	                
	                    response.setHeader("Cache-Control", "no-cache, no-store, must-revaildate");
	                    response.setHeader("Pragma", "no-chache");
	                    response.setHeader("Expires", "0");
	     
	                %>
                </div>    
            </ul>
        </div>
        
        <p class="float-fix"></p>
    </section>

</body>
</html>