<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Home</title>
</head>
<body>
	<section>
		<div class="search-food">
			<form action="search_food" class="food-container">
				<input type="text" name="food_name" placeholder="Enter food name"> <br>
				<input type="submit" value="search">

			</form>
		</div>
	</section>

	<section>
		<div>
			<%
				User user;
				if ((user = (User) session.getAttribute("user")) != null) {
					
					if (user.getUser_type() == 1) {
						out.println("<h2 class='container text-center'><a href='login.jsp' title='Login/Register'>Foods</a></h2>");
					} else {
						out.println("<h2 class='container text-center'><a href='#' title='Foods'>Foods</a></h2>");
					}
					
				} else {
					out.println("<h2 class='container text-center'><a href='login.jsp' title='Login/Register'>Foods</a></h2>");
				}
			%>
		</div>
		<div class="container">
            <ul>
                <li><img src="Images/menu-momo.jpg" alt="Momo" class="image-container-style"></li>
                <li><img src="Images/menu-burger.jpg" alt="Burger" class="image-container-style"></li>
                <li><img src="Images/menu-pizza.jpg" alt="Pizza" class="image-container-style"></li>
            </ul>
        </div>

	</section>
	<br>
</body>

<footer>
	<%@ include file="contact_us.jsp" %>
	<%@ include file="footer.jsp" %>
</footer>
</html>