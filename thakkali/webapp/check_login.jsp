<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("home.jsp");
	}
	
%>