<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Login</title>
</head>
<body>
    <section class="search-food">
        <div class="food-container">
            <form action="login" method="post">
                <input type="text" name="email_id" placeholder="Enter email"> <br>
                <input type="password" name="password" placeholder="Enter password"> <br>
                <input type="submit" value="login">
            </form>
        
        	<%
        		String error;
        		if ((error = (String) session.getAttribute("error")) != null) {
        			out.println("<p class='error-message'>" + error + "</p>");
        			session.removeAttribute("error");
                }

        	%>
        
            <a href="register.jsp" title="Register">
                New user? Register
            </a>
        </div>
    </section>
    <br>
</body>

<footer class="text-center">
	<%@ include file="contact_us.jsp" %>
    <%@ include file="footer.jsp" %>
</footer>
</html>