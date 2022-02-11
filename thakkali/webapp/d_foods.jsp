 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList, com.thakkali.model.Foods" %>
    
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Foods</title>
</head>
<body>

	<%@ include file="check_login.jsp"%>

	<!-- explore section -->
	<section class="container">
		<div>
			<form action="order_foods" method="get">
				<div class="search-result">
					<ul>
						<%
							if (session.getAttribute("foods") != null) {
								Foods searched = (Foods) session.getAttribute("searched_item");
								if (searched != null) {
									
									if (searched.getFood_id() != 0) {
										String checkbox = searched.getFood_id() + "," + searched.getFood_name() + "," + searched.getPrice();
	
										out.println(
												"<li><img src='" + searched.getImage_url() + "' alt='" + searched.getImage_alt() + "'></li> " +
												"<div style='padding: 8% 0;'>" +
												"<li><input type='checkbox' name='explore_foods' value='" + checkbox + "'></li> " +
												"<li>" + searched.getFood_name() + "</li> " +
												"<li>" + searched.getFood_category() + "</li> " +
												"<li>" + searched.getDescription() + "</li> " +
												"<li>" + searched.getPrice() + "</li>" +
												"</div>" + 
												"<p class='float-fix'></p>");	
									} else {
										out.println("<h2 style='padding: 2% 2%;' class='text-center'>Food not Found :( </h2>");
									}
								}
							}
						%>
						
					</ul>
				</div>
				
				<div style="background-color: lightgray; padding: 4% 0;">
					<div class="container">
						<ul style="display: inline;">
							<%	
								ArrayList<Foods> foods = (ArrayList<Foods>) session.getAttribute("foods");	
								
								for (Foods food: foods) {
									String checkbox = food.getFood_id() + "," + food.getFood_name() + "," + food.getPrice();
									
									out.println(
											"<li> <div class='explore-foods'> <ul>" +
											"<li><img src='" + food.getImage_url() + "' alt='" + food.getImage_alt() + "'></li> " +
											"<li><input type='checkbox' name='explore_foods' value='" + checkbox + "'></li> " +
											"<li>" + food.getFood_name() + "</li> " +
											"<li>" + food.getFood_category() + "</li> " +
											"<li>" + food.getDescription() + "</li> " +
											"<li>Rs." + food.getPrice() + "</li> " +
											"</ul> </div> <p class='float-fix'></p> </li>");	
								}
							
						%>
						
						</ul>
						<br>
						
						<input type="submit" value="order" style="width: 20%; padding: 1%; margin: 0 auto;" class="container button-style">
					
					</div>
			</div>
			</form>
		</div>
	</section>
	<br>
	

</body>

<footer class="text-center">
	<%@ include file="contact_us.jsp" %>
	<%@ include file="footer.jsp" %>
</footer>
</html>