<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<title>Hotel Info</title>
</head>
<body>
	
    <section class="search-food">
        <div class="food-container">
            <form action="register_hotel" method="post">
                <table>
                    <tbody>
                        <tr>
                        	<th><label for="hotel_name">Hotel Name:</label></th>
                            <td><input type="text" name="hotel_name" id="hotel_name"></td>
                        </tr>

                        <tr>
                            <th><label for="hotel_address">Address:</label></th>
                            <td><input type="text" name="hotel_address" id="hotel_address"></td>
                        </tr>

                        <tr>
                            <td colspan="2"><input type="submit" value="register"></td>
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