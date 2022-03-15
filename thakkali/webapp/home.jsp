<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<style type="text/css">
	.hero {
		background-image: url("Images/background.jpg");
		background-position: center;
		background-repeat: no-repeat;
		background-size: cover;
		min-height: 100vh;
	}
	
	.features {
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.features__content {
		flex: 1;
	}
	
	.features__image {
		flex: 2;
		height: 489px;
	}
	
	#image1 {
		position: bottom;
	}
	
	#image2 {
		position: top;
	}
	
	.card {
		z-index: 0;
	}
	
	.card:hover {
		color: white;
		background-color: #3b3c36;
		transform: scale(1.02);
		transition: all 300ms ease-in-out; 
	}
	
	.display-3:hover {
		transform: scale(1.02);
		transition: all 300ms ease-in-out; 
		box-shadow-sm: 0 .125rem .25rem rgba(black, .075);
	}
	
</style>

<title>Home</title>
</head>
<body>
	<section class="hero" id="hero_section"></section>
	
	<section class="hotels bg-light" id="hotels_section">
		<h2 class="display-3 text-center p-3 fw-light" style="color: #3b3c36;">Hotel</h2>
		<div class="container justify-content-center align-items-center p-3 w-75" id="hotels_content"></div>
		<div class="d-flex justify-content-center align-items-center flex-row bd-highlight mx-3">
			<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
				<div class="class="btn-group me-2" role="group" aria-label="First group" id="paginate_home"></div>
			</div>
		</div>
	</section>
	
	<h2 class="display-3 text-center p-3 fw-light" style="color: #3b3c36;">Features</h2>
	<section class="features" id="features_section">
		<div class="features__content m-5">
			<h3 class="mb-2 fs-2 fw-bold">Restaurants in your pocket</h3>
			<p class="fs-6 fw-lighter text-justify">Order from your favorite restaurants & track on the go, 
			with the all-new Thakkali app.</p>
		</div>
		
		<div class="features__image" id="image1"><img src="Images\bottom.jpg" alt="bottom" height=489 width=398></div>
		
		<div class="features__image" id="image2"><img src="Images\top_image.jpg" alt="top" height=489 width=398></div>
	</section>

	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
			<%@ include file="bye_page.jsp" %>
	</section>
	
	<script type="text/javascript">
		getHotels(0, 0 * 9, 9);
		let hotelList;
		
		function getHotels(i, start, hotelsPerPage) {
			const XHR = new XMLHttpRequest();
			
			XHR.open("GET", "/thakkali/get_hotel?page=" + start + "&items=" + hotelsPerPage, true);
			
			XHR.onload = function () {
				hotelList = JSON.parse(this.responseText);
				let pageNumberHtml = "";
				
				let paginate = "", iterator = 0;
				let n = hotelList.data.length;
				
				while (n > 0) {
					n = Math.floor(n / hotelList.items_per_page);
					pageNumberHtml += 
						"<button class='btn btn-outline-dark mb-3' id='"+ (iterator + 1) + ".current_page' " +
						"onclick='doPaginate(" + iterator +", " + (iterator * hotelsPerPage) + ", " + 
						hotelsPerPage + ")'>" + (iterator + 1) + "</button>";						
					
					iterator++;
				}
				
				document.getElementById("paginate_home").innerHTML = pageNumberHtml;
				
				let output = "";
				n = hotelList.data.length;
				const COLS = 3;
				let i = 0;
				
				while (n > 0) {
					let m = Math.min(COLS, n);					
					output +=
						  "<div class='row row-cols-3'>\n";					
						  
					for (let j = 0; j < m; j++) {
						output +=
							"<div class='col p-3'>\n"+
							"<a href='#' id='" + hotelList.data[i + j].hotel_name + "' class='text-dark'> <div class='card shadow-lg' style='width: 18rem;''> " +
							  "<img src='" + hotelList.data[i + j].hotel_image + "' class='card-img-top' alt='" + hotelList.data[i + j].hotel_name + "' height=256px width=286px> " +
							  "<div class='card-body'> " +
							    "<h5 class='card-title'>" + hotelList.data[i + j].hotel_name + "</h5> " +
							    "<p class='card-text'>" + hotelList.data[i + j].hotel_address + "</p> " +
							  "</div> " +
							"</div> </a> " +
						    "</div>\n";						
					} 
					output += "</div>\n";					
					
					n -= COLS;
					i += COLS;
				}
				
				document.getElementById("hotels_content").innerHTML = output;
				
			};
			
			XHR.send();	
		}
	
	</script>
	
</body>
