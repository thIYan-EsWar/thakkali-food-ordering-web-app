<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.thakkali.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>


<style type="text/css">
header {
	position: relative;
}

.zero_visibility {
	visibility: hidden;
}

.overlay_container {
	position: fixed;
	display: flex;
	justify-content: center;
	align-items: center;
	top: 0;
	right: 0;
	width: 100%;
	height: 100vh;
	background-image: linear-gradient(120deg, rgb(220, 220, 220),
		transparent);
	overflow: hidden;
	z_index: 100;
}

.form_container {
	position: absolute;
	background-color: white;
	width: 1133px;
	height: 700px;
	border-radius: 5px;
	opacity: 1;
	z-index: 400;
}

.form_container--tall {
	height: 900px;
}

.form_container--fat {
	wiidth: 580px
}

.login_form {
	position: absolute;
	text-align: left;
	z-index: 500;
}

.registration_form {
	position: absolute;
	text-align: left;
	z-index: 500;
}

.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}

.h-custom {
	height: 100%;
}

@media ( max-width : 450px) {
	.h-custom {
		height: 100%;
	}
}

.smooth-flow {
	transition: all 300ms ease-in-out;
}

.no-scroll {
	overflow: hidden;
}
</style>

<title>Header</title>
</head>
<body>
	<header class="position-relative">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="#"><img src="Images/logo.png"
					alt="logo" height=96 width=96></a>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="home.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="about_us.jsp">About</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#contacts">Contact</a>
						</li>

						<%
						User user = (User) session.getAttribute("user");

						if (user != null) {
							if (user.getUser_type() == 2)
								out.print("<li class='nav-item'> " + "<a class='nav-link' href='d_admin.jsp'>Admin</a>" + "</li>");

							out.print("<li class='nav-item dropdown'>"
							+ "<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-bs-toggle='dropdown' aria-expanded='false'> "
							+ user.getUser_name() + " </a>" + "<ul class='dropdown-menu' aria-labelledby='navbarDropdown'>"
							+ "<li><a class='dropdown-item' href='d_foods.jsp'>Foods</a></li>"
							+ "<li><a class='dropdown-item' href='history.jsp'>Order History</a></li>"
							+ "<li><hr class='dropdown-divider'></li>"
							+ "<li><a class='dropdown-item' href='logout.jsp'>Logout</a></li>" + "</ul></li>");
						} else {
							out.print("<li class='nav-item'>" + "<a class='nav-link' type='button' href='' id='login-btn'>Login</a>" + "</li>");
						}
						%>

					</ul>
					<form class="d-flex" action="search_food" method="get"
						id="searchForm">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search"
							onkeyup="canSubmitForm('searchForm', 'search')"> <input
							class="btn btn-outline-light" type="submit" id="search"
							disabled="disabled">
					</form>
				</div>
			</div>
		</nav>

		<div class="position-absolute" style="z-index: 10;">
			<div id="error"
				class="toast align-items-center text-white bg-danger border-0 mb-0"
				role="alert" aria-live="assertive" aria-atomic="true"
				id="toast_message_e">
				<div class="d-flex">
					<div class="toast-body" id="display"><%=(String) session.getAttribute("error")%></div>
					<button type="button" class="btn-close btn-close-dark me-2 m-auto"
						data-bs-dismiss="toast" aria-label="Close"></button>
				</div>
			</div>
		</div>

		<div class="position-absolute" style="z-index: 10;">
			<div id="success"
				class="toast align-items-center text-white bg-success border-0"
				role="alert" aria-live="assertive" aria-atomic="true"
				id="toast_message_s">
				<div class="d-flex">
					<div class="toast-body" id="display">Login Successful</div>
					<button type="button" class="btn-close btn-close-dark me-2 m-auto"
						data-bs-dismiss="toast" aria-label="Close"></button>
				</div>
			</div>
		</div>

		<div
			class='overlay_container zero_visibility d-flex justify-content-center align-items-center'
			id='overlay_container_login'>
			<div class='form_container d-flex flex-column align-items-end p-3'
				id='form_container'>
				<div>
					<button type='reset' class='btn-close' aria-label='Close'
						id='login_close_btn' class='zindex-tooltip'></button>
				</div>
			</div>
			<%@ include file="login_async.jsp"%>
		</div>

	</header>

	<script type="text/javascript" src="javascript/app.js"></script>

	<script type="text/javascript">
		if (
	<%=session.getAttribute("error") != null%>
		) {
			$(document).ready(function() {
				$("#error").toast("show");
			});
	<%session.removeAttribute("error");%>
		}

		if (
	<%=session.getAttribute("success") != null%>
		) {
			$(document).ready(function() {
				$("#success").toast("show");
			});
	<%session.removeAttribute("success");%>
		}
	</script>

</body>
</html>