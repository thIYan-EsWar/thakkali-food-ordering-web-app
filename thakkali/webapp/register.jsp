<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">
<script type="text/javascript" src="./javascript/app.js" defer></script>

<title>Register</title>
</head>
<body>

	<div style="min-height: 70vh;">
		<section class="search-food">
			<div class="food-container">
				<form action="register" id="myForm">
					
					<table>
						<tbody>
							<!-- Name -->
							<tr>
								<th>
								<label for="name">Name:</label>
								</th>
								<td>
									<input type="text" name="name" id="name" onkeyup="canSubmitForm('myForm', 'form-btn')">
								</td>
							</tr>
	
							<!-- Email -->
							<tr>
								<th>
								<label for="email_id">Email:</label>
								</th>
								<td>
									<input type="email" name="email_id" id="email_id" onkeyup="canSubmitForm('myForm', 'form-btn')">
								</td>
							</tr>
	
							<!-- Address -->
							<tr>
								<th>
								<label for="address">Address:</label>
								</th>
								<td>
									<input type="text" name="address" id="address" onkeyup="canSubmitForm('myForm', 'form-btn')">
								</td>
							</tr>
	
							<!-- Contact number -->
							<tr>
								<th>
								<label for="contact_number">Contact number:</label>
								</th>
								<td>
									<input type="text" name="contact_number" id="contact_number" onkeyup="canSubmitForm('myForm', 'form-btn')">
								</td>
							</tr>
	
							<!-- User type -->
							<tr>
								<th>
								<label for="user_type">User type:</label>
								</th>
								<td>
									<select name="user_type" id="user_type" onkeyup="canSubmitForm('myForm', 'form-btn')">
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
									<input type="password" name="password" id="password" onkeyup="canSubmitForm('myForm', 'form-btn')">
								</td>
							</tr>
	
							<tr>
								<td colspan="2">
									<input type="submit" value="register" disabled="disabled" id="form-btn">
								</td>	
							</tr>
						</tbody>
					</table>
	
				</form>
			</div>
		</section>
		
	</div>
	
	<br>
	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>
</body>

</html>