<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Order Placed</title>
</head>
<body class="hyaku-height">

	<%@ include file="check_login.jsp"%>
	
	<section style="background-color: #e6ffff; background-size: cover; padding: 2% 0;">
		<h1 class="text-center">Order Placed Successfully!</h1>	
		<div class="history-container">
			<table>
			<thead>
				<tr>
					<th><p>Serial number</p></th>
					<th><p>Food name</p></th>
					<th><p>Quantity</p></th>
					<th><p>Quantity Price</p></th>
				</tr>
			</thead>

			<tbody>
				<%
					String[] names = (String[]) session.getAttribute("names");
					int[] quantities = (int[]) session.getAttribute("quantities");
					float[] prices = (float[]) session.getAttribute("prices");
					float total_price = 0f;
					
					for (int i = 0; i < names.length; i++) {
						total_price += prices[i];
						
						out.println(
								 "<tr>" +
								 "<td> <p class='text-center'> " + (i + 1) + " </p> </td>" +
								 "<td> <p> " + names[i] + " </p> </td>" +
								 "<td> <p class='text-center'> " + quantities[i] + " </p> </td>" +
								 "<td> <p class='text-center'> " + prices[i] + " </p> </td>" +
							     "</tr>"
								);	
					}
				%>
				</tbody>

				<tfoot>
					<tr>
						<td colspan="3" style="font-weight: bold; text-align: center;">Total Price</td>
						<td><%= total_price %></td>
					</tr>
				</tfoot>
			</table>	
		
		</div>
		
		<div class="history-container">
			<form action="take_back">
				<input type="submit" value="back">
			</form>
			
		</div>
		
		
	</section>
	
	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>
	
</body>

</html>