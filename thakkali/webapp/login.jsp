<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">
<script type="text/javascript" src="./javascript/app.js" defer></script>

<title>Login</title>
</head>
<body>
	<div style="min-height: 70vh">
		<section class="search-food">
        <div class="food-container">
            <form action="login" method="post" id="myForm">
                <input type="text" name="email_id" placeholder="Enter email" onkeyup="canSubmitForm('myForm', 'form-btn')"> <br>
                <input type="password" name="password" placeholder="Enter password" onkeyup="canSubmitForm('myForm', 'form-btn')"> <br>
                <input type="submit" value="login" disabled="disabled" id="form-btn">
            </form>
            <a href="register.jsp" title="Register" class="btn btn-link">
                New user? Register
            </a>
        </div>
    </section>
	</div>
    <br>
    
   	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>
</body>

</html>