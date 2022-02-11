<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" 
%>
  
<%@	page import="com.thakkali.model.Orders"
%>
    
<%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Order history</title>

</head>
<body>
	<%@ include file="check_login.jsp"%>

	<section style="background-color: #e6ffff; background-size: cover; padding: 2% 0;">
		<div class="history-container">
			<table>
				<thead>
					<tr>
						<th><p>Serial number</p></th>
						<th><p>Name</p></th>
						<th><p>Quantity</p></th>
						<th><p>Price</p></th>
						<th><p>Date</p></th>
					</tr>
				</thead>

				<tbody>
					<%
						ArrayList<Orders> history = (ArrayList<Orders>) session.getAttribute("history");
						
						if (history != null) {
							for (int i = 0; i < history.size(); i++) {
								out.println(
										"<tr>" +
											"<td><p class='text-center'>" + (i + 1) + "</p></td>" +
											"<td><p>" + history.get(i).getFood_name() + "</p></td>" +
											"<td><p class='text-center'>" + history.get(i).getQuantity() + "</p></td>" + 
											"<td><p class='text-center'>" + history.get(i).getPrice() + "</p></td>" +
											"<td><p  style='width: 200px;'> " + history.get(i).getOrdered_date() + "</p></td>" +
										"</tr>" 
										);	
							}
						}
					%>
				</tbody>	
			</table>
			
		</div>
	</section>
	<br>
</body>

<footer class="text-center">
	<%@ include file="contact_us.jsp" %>
	<%@ include file="footer.jsp" %>
</footer>
</html>