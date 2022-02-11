<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList, com.thakkali.model.Foods" %>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Admin</title>

</head>
<body>

	<%@ include file="check_login.jsp"%>

	<section class="search-food">
		<div class="admin-container">
			<form action="add_food" method="post">
				<table>
					<thead>
						<tr>
							<th><label for="food_name">Food name</label></th>
							<th><label for="food_category">Category</label></th>
							<th><label for="food_description">Description</label></th>
							<th><label for="unit_price">Price</label></th>
							<th><label for="image_url">URL</label></th>
							<th><label for="image_alt">Image</label></th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td><input type="text" name="food_name" id="food_name"></td>
							<td>
								<select name="food_category" id="food_category">
									<option value="Tamilian">Tamilian</option>
									<option value="Japanese">Japanese</option>
									<option value="Indian">Indian</option>
									<option value="Italian">Italian</option>
									<option value="other">Other</option>
								</select>
							</td>
							<td><input type="text" name="food_description" id="food_description"></td>
							<td><input type="number" name="unit_price" id="unit_price" min="0"></td>
							<td><input type="text" name="image_url" id="image_url"></td>
							<td><input type="text" name="image_alt" id="image_alt"></td>
						</tr>
						<tr>
							<td colspan="6">
								<input type="submit" value="add">
							</td>
						</tr>
					</tbody>
				</table>
				
			</form>
		</div>
	</section>

	<section class="delete-food">
		<div>
			<form action="delete_food" method="post">
				<table>
					<thead>
						<tr>
							<th>Select</th>
							<th>Name</th>
						</tr>
					</thead>

					<tbody>
						<%	
							if (session.getAttribute("menu") != null) {
								ArrayList<Foods> foods = (ArrayList<Foods>) session.getAttribute("menu");
						
								for (Foods food: foods) {
									out.println(
										"<tr> " +
										"<td> " +
										"<input type='checkbox' name='select_foods' value='" + food.getFood_id() + "'> " +
										"</td>" +
										"<td> " +
										food.getFood_name() +
										"</td> " +
										"</tr>"	
									);	
								}	
							}
					
						%>
					</tbody>

					<tfoot>
						<tr>
							<td colspan="2">
								<input type="submit" value="delete">
							</td>
						</tr>
					</tfoot>
				</table>
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