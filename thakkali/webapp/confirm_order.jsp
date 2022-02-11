<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.Arrays"
%>

<%@ page import="com.thakkali.model.Orders, com.thakkali.model.Foods"
%>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Confirm Order</title>

</head>
<body>
	<%@ include file="check_login.jsp"%>
	
	<section style="background-color: #e6ffff; background-size: cover; padding: 2% 0;">
	
	<div class="history-container">
		<form action="place_order" method="post">
			<table>
				<thead>
					<tr>
						<th><p>Serial Number</p></th>
						<th><p>Food Name</p></th>
						<th><p>Quantity</p></th>
					</tr>
				</thead>
				
				<tbody>
					<%
					String[] names = (String[]) session.getAttribute("names");
					float[] prices = (float[]) session.getAttribute("prices");
					
					for (int i = 0; i < names.length; i++) {
						out.println(
									"<tr> " +
										"<td> <p class='text-center'> "	+ (i + 1) +" </p> </td> " +
										"<td> <p> "	+ names[i] +" </p> </td> " +
										"<td> <p> " + "<input type='number' name='" + names[i] + "' min='0'>" +" </p> </td> " +
									"</tr> "
								);	
						}
					%>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="4">
							<input type="submit" value="place order">
						</td>
					</tr>
				
				</tfoot>
				
			</table>
		</form>
	</div>
	
	</section>
</body>

<footer class="text-center">
	<%@ include file="contact_us.jsp" %>
	<%@ include file="footer.jsp" %>
</footer>
</html>