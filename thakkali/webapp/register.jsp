<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Register</title>
</head>
<body>

	<section class="search-food">
		<div class="food-container">
			<form action="register">
				
				<table>
					<tbody>
						<!-- Name -->
						<tr>
							<th>
							<label for="name">Name:</label>
							</th>
							<td>
								<input type="text" name="name" id="name">
							</td>
						</tr>

						<!-- Email -->
						<tr>
							<th>
							<label for="email_id">Email:</label>
							</th>
							<td>
								<input type="email" name="email_id" id="email_id">
							</td>
						</tr>

						<!-- Address -->
						<tr>
							<th>
							<label for="address">Address:</label>
							</th>
							<td>
								<input type="text" name="address" id="address">
							</td>
						</tr>

						<!-- Contact number -->
						<tr>
							<th>
							<label for="contact_number">Contact number:</label>
							</th>
							<td>
								<input type="text" name="contact_number" id="contact_number">
							</td>
						</tr>

						<!-- User type -->
						<tr>
							<th>
							<label for="user_type">User type:</label>
							</th>
							<td>
								<select name="user_type" id="user_type">
									<option value="1">
										Customer
									</option>
									<option value="2">
										Manager
									</option>
								</select>
							</td>
						</tr>

						<!-- Password -->
						<tr>
							<th>
							<label for="password">Password:</label>
							</th>
							<td>
								<input type="password" name="password" id="password">
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<input type="submit" value="register">
							</td>	
						</tr>
					</tbody>
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