<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList" 
%>
  
<%@	page import="com.thakkali.model.Orders"
%>
    
<%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style type="text/css">
	/* Pagination links */
	.pagination a {
	  color: black;
	  float: left;
	  padding: 8px 16px;
	  text-decoration: none;
	  border-radius: 50px;
	  transition: background-color .3s;
	}
	
	/* Style the active/current link */
	.pagination a.active {
	  background-color: dodgerblue;
	  color: white;
	  border-radius: 50px;
	}
	
	/* Add a grey background color on mouse-over */
	.pagination a:hover:not(.active) {
		background-color: #ddd;
		border-radius: 50px;	
	}
	
</style>

<link rel="stylesheet" type="text/css" href="CSS/global.css">
<link rel="stylesheet" type="text/css" href="CSS/style.css">

<title>Order history</title>

<style type="text/css">
	.history_table {
		color: black;
	}
</style>

</head>
<body>
	<%@ include file="check_login.jsp"%>
	
	<div style="min-height: 70vh" class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section">Order History</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap">
						<table class="table table-responsive-xl text-center table-hover">
						  <thead>
						    <tr>
						    	<th>Order Id</th>
						      <th>Date</th>
						      <th>Status</th>
						    </tr>
						  </thead>
						  
						  <tbody id="history_container"></tbody>
						  
						</table>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
	<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
	  
	  <div class="offcanvas-header">
	    <h5 id="offcanvasRightLabel">Order details</h5>
	    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	  </div>
	  
	  <div class="offcanvas-body"><div class="main" id="history_detailed_view_container"></div></div>
	  
	</div>
	
	<section id="pagination" class="d-flex justify-content-center p-2">
		<div class="pagination" id="pagination_container"></div>
	</section>	
	
	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>

   
	<script type="text/javascript">
		let jsonResponse, historiesList, historiesPerPage = 5, totalHistories = 1;
		let currentPage = 1, numberOfPages = 1;
		
		//Initial Page population
		getOrderHistories(1); 
		
		function showDetailedView(orderDetails) {
			let innerHtml = "";
			console.log(orderDetails);
		
			for (let i = 0; i < orderDetails.length; i++) {
				innerHtml +=
			        "<div class='row row-main'> " +
		            "<div class='col-3'> <img class='img-fluid' src='" + orderDetails[i].image_url + "'> </div> " +
		            "<div class='col-6'> " +
		                "<div class='row d-flex'> " +
		                    "<p><b>" + orderDetails[i].food_name + "</b></p> " +
		                "</div> " +
		                "<div class='row d-flex'> " +
		                    "<p class='text-muted'>" + orderDetails[i].ordered_date + "</p> " +
		                "</div> " +
		            "</div> " +
		            "<div class='col-3 d-flex justify-content-end'> " +
		                "<p><b><i>Rs.</i>" + (orderDetails[i].price * orderDetails[i].quantity) + "</b></p> " +
		            "</div> " +
	        		"</div> ";
			}
			
			document.getElementById("history_detailed_view_container")
				.innerHTML = innerHtml;
			
		}
		
		function getOrderHistories(start) {
			if (start >= 0 && start <= totalHistories) {
				const getOrderHistoriesXHR = new XMLHttpRequest();
				getOrderHistoriesXHR.open("GET", "/thakkali/get_order_history?page=" + start, true);
				
				getOrderHistoriesXHR.onload = function (event) {
					if (this.status == 200 || this.status == 304)  jsonResponse = JSON.parse(this.responseText);
					
					totalHistories = jsonResponse.total_items;
					
					let historyContainerInnerHTML = "";
					historiesList = jsonResponse.data;
					numberOfPages = jsonResponse.total_items / historiesPerPage;
					totalHistories = jsonResponse.total_items;
					
					paginateHistoryTab();
					let totalPrices = [];
					
					for (let i = 0; i < historiesList.length; i++) {
						let totalPrice = 0;
						
						for (let j = 0; j < historiesList[i].length; j++) 
							totalPrice += (historiesList[i][j].price * historiesList[i][j].quantity);
						
						totalPrices.push(totalPrice);
					}
					
					for (let i = 0; i < historiesList.length; i++) {
						historyContainerInnerHTML +=
						
						    "<tr class='alert' role='alert'> " +
	
						      "<td class='d-flex align-items-center' id='" + historiesList[i][0].order_id + ".historyContainer' data-bs-toggle='offcanvas' "+
						      "data-bs-target='#offcanvasRight' aria-controls='offcanvasRight' onclick='showDetailedView(" + JSON.stringify(historiesList[i]) + ")'> " +
						      	"<div class='img' style='background-image: url(" + historiesList[i][0].image_url + ");'></div> " +
						      	"<div class='pl-3 email'> " +
						      		"<span>Order Id: " + historiesList[i][0].order_id + "</span> " +
						      		"<span>Ordered on: " + historiesList[i][0].ordered_date + "</span> " +
					      		"</div> " +
						      "</td> " +
						      "<td><b><i>Rs.</i>" + totalPrices[i] + "</b></td> " +
						      "<td class='status'><span class='active'>Successfull</span></td> " +
	
							"</tr> ";
					}
					
					document.getElementById("history_container").innerHTML = historyContainerInnerHTML;
					
				}
				
				getOrderHistoriesXHR.send();
			}
			
		}
		
		function paginateHistoryTab() {
			console.log("Inside paginate...");
			
			const historyPaginateContainer = document.getElementById("pagination_container");
			let output = "";
			
			output += "<a href='' onclick='getOrderHistories(" + (currentPage - 1) + ")'>&laquo;</a>";
			
			for (let i = 0, start = 1; i < totalHistories;) {
				console.log("start: " + start + ", i: " + i);
				
				if (start == 1) output += "<a class='active' href='getOrderHistories(" + start + ")'>" + start + "</a>";
				else output += "<a href='getOrderHistories(" + start + ")'>" + start + "</a>";
				
				i += historiesPerPage;
				start++;
			}
			
			output += "<a href='getOrderHistories(" + (currentPage + 1) + ")'>&raquo;</a>";
			
			historyPaginateContainer.innerHTML = output;
		}
	</script>
  
 
</body>

</html>