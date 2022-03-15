<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ page import="java.util.ArrayList, com.thakkali.model.Foods" %>

<%@ include file="header.jsp" %>

	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		
		<style>
			.BoxContainer {
				min-height: 70vh;
				position: relative;
			}
			
			.btn:hover {
				transform: scale(1.02);
				transition: all 200ms ease-in-out;
			}
			
			.black_text {
				color: black;
			}
			
			.card {
				min-height: 512px;
			}
			
			
			.card a {
				color: black;
				text-decoration: none;
			}

			
			#food-list-container .card:hover {
				color: white;
				background-color: black;
				transform: scale(1.02);
				transition: all 200ms ease-in-out; 
			}
			
			#food-list-container .card:hover ul li {
				color: white;
			}
			
			.list-group-item {
				border: none;
				background-color: transparent;
			}
			
			#food-cart .card {
				position: sticky;
				top: 0;
			}
			
			.no-box {
				border: none;	
			}
			
			.no-bg-color {
				background-color: none;
			}
			
			.main {
			    padding: 0 2rem
			}
			
			.main img {
			    border-radius: 7px
			}
			
			.main p {
			    margin-bottom: 0;
			    font-size: 0.75rem
			}
			
			#sub-title p {
			    margin: 1vh 0 2vh 0;
			    font-size: 1rem
			}
			
			.row-main {
			    padding: 1.5vh 0;
			    align-items: center
			}
			
			hr {
			    margin: 1rem -1vh;
			    border-top: 1px solid rgb(214, 214, 214)
			}
			
			.total {
			    font-size: 1rem
			}
			
			@media(height: 1366px) {
			    .main p {
			        margin-bottom: 0;
			        font-size: 1.2rem
			    }
			
			    .total {
			        font-size: 1.5rem
			    }
			}
			
			.modal {
				background-image: linear-gradient(135deg, lightgray, transparent);
				overflow: hidden;
			}
						
			
		</style>
		
		<title>Foods</title>
	</head>

	<body>

		<%@ include file="check_login.jsp" %>

		<!-- Modal -->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Order Placed Successfully</h5>
		        <button type="button" class="btn-close" onclick="closeConfirmation()"></button>
		      </div>
		      <div class="modal-body">	
		      	
	      	    <div class="main"> <span id="sub-title"><p><b>Payment Summary</b></p></span>
	      	    
				<div id="order_summary_container"></div>
		        <hr>
		        <div class="total">
	            <div class="row">
	                <div class="col"> <b> Total:</b> </div>
	                <div class="col d-flex justify-content-end" id="total_price"></div>
	            </div>
				</div>
	      	    
				</div>      	
	      	</div>
	      
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-dark" data-dismiss="modal" onclick="closeConfirmation()">Done</button>
	      </div>
	    
	    </div>
	  </div>
	</div>
		
		<form method="post">
			<section class="BoxContainer bg-light d-flex justify-content-center">
				<div class="container-sm w-50" style="margin-left: 80px; flex: 3;" id="food-list-container"></div>
				
				<div class="mt-5" style="flex: 1;" id="food-cart">
					<div class="card me-5 shadow-lg" style="width: 24rem;">
					  <img src="Images/cart_empty.jpg" class="card-img-top" alt="Cart Empty">
					  <div class="card-body">
					    <h5 class="card-title">Food Cart</h5>
					  </div>
					  <form> 
						  <ul class="list-group list-group-flush" id="cart-list-container"></ul>
						  <div class="card-body">
						    <button type="button" class="btn btn-outline-dark" id="place_order_button" onclick="checkOutFoods()">Order</button>
						  </div>
					
						</form>
					</div>
				</div>
			
			</section>
			
			<section class="d-flex justify-content-center align-items-center flex-row bd-highlight mx-3" id="page_number">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
					<div class="btn-group" role="group" aria-label="First group" id="page_number"></div>
				</div>
			</section>

		</form>

		<script type="text/javascript">
			var cartItemsList = [];
			var imageURLArray = [];
			
			if (cartItemsList.length > 0) document.getElementById("place_order_button").removeAttribute("disabled");
			else document.getElementById("place_order_button").setAttribute("disabled", "disabled");

			function closeConfirmation() {
				$("#exampleModalCenter").modal("hide");
				window.location.href = "/thakkali/history.jsp";
				
			}
			
			function checkOutFoods() {
				
				let xhrCheckout = new XMLHttpRequest();
				
				xhrCheckout.open("GET", "/thakkali/checkout?food_cart=" + encodeURIComponent(JSON.stringify(cartItemsList), true));
				xhrCheckout.setRequestHeader('Content-type', 'application/json');
				
				xhrCheckout.onload = function () {
					
					const orderSummary = document.getElementById("order_summary_container");
					let orderSummaryInnerHTML = "";
					let totalPrice = 0;
					
					for (let i = 0; i < cartItemsList.length; i++) {
						orderSummaryInnerHTML +=
							"<div class='row row-main'> " +
							"<div class='col-3'> <img class='img-fluid' src='" + imageURLArray[i] + "'> </div> " +
				            "<div class='col-6'> " +
				                "<div class='row d-flex'> " +
				                    "<p><b>" + cartItemsList[i].name + "</b></p> " +
				                "</div> " +
				                "<div class='row d-flex'> " +
				                    "<p class='text-muted'>Ordered Quantity" + cartItemsList[i].quantity + "</p> " +
				                "</div> " +
				            "</div> " +
				            "<div class='col-3 d-flex justify-content-end'> " +
				                "<p><b><i>Rs.</i>" + (cartItemsList[i].price * cartItemsList[i].quantity) + "</b></p> " +
				            "</div> " +
			        		"</div>";
			        		
			        	totalPrice += (cartItemsList[i].price * cartItemsList[i].quantity);
					}
					
					orderSummary.innerHTML = orderSummaryInnerHTML;
					document.getElementById("total_price")
						.innerHTML = "<b>Rs." + totalPrice + "</b>"
					
					if (this.status == 200 || this.status == 304) {
						$("#exampleModalCenter").modal("show");
					}
				}
				
				xhrCheckout.send();
				
			}
		
			// Food count updation function body
			function changeSelectedFoodCount(id, price) {
				let changeCount = document.getElementById("selected-food-count-" + id);
				let priceBox = document.getElementById("selected-food-price-" + id);
				
				for (let i = 0; i < cartItemsList.length; i++) {
					if (cartItemsList[i].id == id) cartItemsList[i].quantity = changeCount.value;
				}
				priceBox.setAttribute("placeholder", price * changeCount.value);
				
			}
			
			// Food list redering function body
			function renderFoodCartList(items) {
				
				if (cartItemsList.length > 0) document.getElementById("place_order_button").removeAttribute("disabled");
				else document.getElementById("place_order_button").setAttribute("disabled", "disabled");
				
				let output = "";

				for (let i = 0; i < items.length; i++) {
					output += 
						"<li class='list-group-item' id='selected-food'> " +
				    	"<div class='row g-3'> " +
						  "<div class='col-sm-6'> " +
						    "<p class='no-box form-control' id='selected-food-name-" + items[i].id + "'>" + items[i].name + "</p> " +
						  "</div> " +
						  
						  "<div class='col-sm-3'> " +
						  	"<input type='text' class='no-box no-bg-color form-control text-right' disabled placeholder='" + items[i].price + "' id='selected-food-price-" + items[i].id + "'> " +  
						  "</div> " +
						  
						  "<div class='col-sm-3'> " +
						  	"<input type='number' class='form-control text-right' placeholder='1' id='selected-food-count-" + 
						  	items[i].id + "' min='0' max='" + items[i].max_count + "' onchange='changeSelectedFoodCount(" + 						  	
						  	items[i].id +", "+ items[i].price + ")'> " +
							  
						  	"</div> " +
						    "</div> </li> ";						
				}

				document.getElementById("cart-list-container").innerHTML = output;
			}
		
			// Add 2 Cart function body
			function addToCart(id, name, price, maxCount, imageURL) {
				
				if (maxCount > 0) {
					document.getElementById("food-item-" + id).classList.toggle("bg-dark");
					document.getElementById("food-item-" + id).classList.toggle("text-white");
					document.getElementById("food-item-title-" + id).classList.toggle("text-white");

					let countBubble = document.getElementById("food-item-count-bubble-" + id);
					let cartList = document.getElementById("selected-food");

					if (countBubble.innerText == 0) {
						countBubble.innerText = 'x';
						
						countBubble.classList.add("bg-white");
						countBubble.classList.add("text-dark");
						
						cartItemsList.push(
								{
									id: id,
									name: name,
									price: price,
									quantity: 1,
									max_count: maxCount,
									image_url: imageURL
								}
							);
						
						imageURLArray.push(imageURL);
						
					} else {
						countBubble.innerText = 0;
						
						countBubble.classList.remove("bg-white");
						countBubble.classList.remove("text-dark");
						
						for (let i = 0; i < cartItemsList.length; i++) 
							if (cartItemsList[i].id == id){ 
								cartItemsList.splice(i);
								imageURLArray.splice(i);
								break;
							}
					}
					renderFoodCartList(cartItemsList);	
				}
			}
		
			
			//Main body
			const XHR = new XMLHttpRequest();
			doPaginate(0, 0, 9);
			
			function doPaginate(i, start, foodsPerPage) {
				let foodList;
				
				previous = i;
				
				const foodContainer = document.getElementById("food-list-container");
				XHR.open("GET", "/thakkali/get_food_page?page=" + i + "&items="+ foodsPerPage, true);
				
				XHR.onload = function (event) {
					if(this.status == 200 || this.status == 304) foodList = JSON.parse(this.responseText);
					
					let pageNumberHtml = "";
					
					let n = foodList.total_items;
					let iterator = 0;
					
					while (n > 0) {
						n = Math.floor(n / foodList.items_per_page);
						pageNumberHtml += 
							"<button type='button' class='btn btn-outline-dark' id='"+ (iterator + 1) + ".current_page' " +
							"onclick='doPaginate(" + iterator +", " + (iterator + foodsPerPage) + ", " + 
									foodsPerPage + ")'>" + (iterator + 1) + "</button>";
						
						iterator++;
						
					}
					
					document.getElementById("page_number").innerHTML = pageNumberHtml;	
				
					let output = "";
					n = foodList.data.length;
					const COLS = 3;
					let i = 0;
					
					while (n > 0) {
						let m = Math.min(COLS, n)					
						output +=
							  "<div class='row row-cols-3'>\n";
							  
						for (let j = 0; j < m; j++) {
							output +=
								"<div class='col p-3' > " +
									"<div class='card card shadow-lg' style='width: 18rem;' id='food-item-" + foodList.data[i + j].food_id + 
												"' onclick='addToCart(" + foodList.data[i + j].food_id + ", " + JSON.stringify(foodList.data[i + j].food_name) + ", " + 
												   foodList.data[i + j].price + ", " + foodList.data[i + j].availability + ", " + JSON.stringify(foodList.data[i + j].image_url) + ")'> " +
													
									  "<img src='" + foodList.data[i + j].image_url + "' class='card-img-top' alt='" + foodList.data[i + j].image_alt + "' height=286px width=286px> " +
									  "<div class='card-body'> " +
									  
										  "<ul class='list-group'> " + 
											  "<li class='list-group-item d-flex justify-content-between align-items-center'> " +
											  		"<h5 class='card-title h5' id='food-item-title-" + foodList.data[i + j].food_id + "'>" + foodList.data[i + j].food_name + "</h5> " +
												    "<span class='badge bg-dark rounded-pill' id='food-item-count-bubble-" + foodList.data[i + j].food_id + "'>0</span> " +
											  "</li> " +
									  	  "</ul> " +
									  	  
									    "<p class='card-text'>" + foodList.data[i + j].description + "</p> " +
									    "<p class='card-text'>INR " + foodList.data[i + j].price + "</p> ";
									    
								if (foodList.data[i + j].availability == 0) {
									output += "<p class='card-text text-danger'>"+ foodList.data[i + j].food_name +" is currently unavailable :(</p> ";
								} else {
									output += "<p class='card-text'>Quantity Available: " + foodList.data[i + j].availability + "</p> ";
								}	    
									    
								output += 
									  "</div> " +
									"</div> " +
						    	"</div>";
						}
						
						output += "</div>\n";					
						
						n -= COLS;
						i += COLS;
					}
				
				foodContainer.innerHTML = output;							
				}
				
				XHR.send();
			}
			
			
			var placeOrderButton = document.getElementById("place_order_button");
			placeOrderButton.addEventListener("click", (event) => {
				
			});
			
		</script>

	<section class="mt-2 container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>

	</body>
	</html>
			
			
