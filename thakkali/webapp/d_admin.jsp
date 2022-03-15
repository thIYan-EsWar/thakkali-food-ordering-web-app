<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList, com.thakkali.model.Foods" %>

<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="CSS/global.css">

<style type="text/css">
	.card {
		min-height: 70vh;
	}

	.nav-item > button {
		color: black;
	}

	.nav-item:hover {
		cursor: pointer;
	}
	
	.add_food_form {
		text-align: left;
		
	}
	
	#add_food_btn:hover {
		color: black;
		background-color: white;
	}
	
	.zero_visibility {
		display: none;
		visibility: hidden;
	}
	
	.form-check:hover {
		cursor: pointer;
	}
	
	.delete_food_btn:hover {
		color: black;
		background-color: white;		
	}
	
	.form-range::-webkit-slider-thumb {
		background-color: black;
		position: relative;
	}
	
	.display {
		position: absolute;
		top: -20px;
		width: 50px;
		height: 50px;
		border-radius: 100px;
		background-color: black;
	}
</style>

<title>Admin</title>
 <%
     response.setHeader("Cache-Control", "no-cache, no-store, must-revaildate");
     response.setHeader("Pragma", "no-chache");
     response.setHeader("Expires", "0");

 %>

</head>
<body>

	<%@ include file="check_login.jsp"%>
	
	<div class="card text-center">
	  <div class="card-header">
	    <ul class="nav nav-tabs card-header-tabs">
	      <li class="nav-item">
	        <button  id="add_food_tab" class="nav-link active" aria-current="true" href="#">Add Foods</button>
	      </li>
	      <li class="nav-item">
	        <button id="delete_food_tab" class="nav-link" href="#">Delete Items</button>
	      </li>
	      <li class="nav-item">
	        <button id="increase_count_tab" class="nav-link" href="#">Alter Quantity</button>
	      </li>
	    </ul>
	  </div>
	  
	  <div class="card-body" id="add_food_form_tab">
	    <h5 class="card-title fs-1 fw-bold">Add Food Item</h5>
	    <p class="card-text">Please add all the information before submitting.</p>
	    <form class="add_food_form row g-3 container-sm ms-4" id="add_food_form" action="add_food" method="post" enctype="multipart/form-data">
		  <div class="col-md-6">
		    <label for="inputName" class="form-label">Food name</label>
		    <input type="text" class="form-control" id="inputName" name="food_name" id="food_name">
		  </div>
			  <div class="input-group col-md-6 mb-3">
			    <span class="input-group-text">Rs</span>
			    <input type="text" class="form-control" aria-label="Amount (to the nearest rupees)" name="unit_price" id="unit_price" min="0">
			  	<span class="input-group-text">.00</span>
			  </div>
			  <div class="col-12">
			    <label for="inputDescription" class="form-label">Description</label>
			    <input type="text" class="form-control" id="inputDescription" placeholder="A brief description..." name="food_description" id="food_description">
			  </div>
			  
			  <div class="form-check">
				  <input class="form-check-input" type="radio" name="admin_image_radio" checked id="admin_image_radio" value="admin_image_url" onclick="selectedUploadType1('admin_image_url')">
				  <label class="form-check-label" for="admin_image_url">
					  <div class="col-12">
					    <label for="image_url" class="admin_image_url">Image URL</label>
					    <input type="text" class="form-control" placeholder="https://www."  name="admin_image_url" id="admin_image_url">
					  </div>
				  </label>
				</div>
				
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="admin_image_radio" id="admin_image_radio" value="admin_image_upload" onclick="selectedUploadType1('admin_image_upload')">
				  <label class="form-check-label" for="admin_image_upload">
					  <div class="input-group mb-3">
					    <input type="file" multiple="true" class="form-control" id="admin_image_upload" name="admin_image_upload" disabled accept="Image/*">
					  </div>
				  </label>
				</div>
			  
			  <div class="col-md-6">
			    <label for="inputQuantity" class="form-label">Quantity</label>
			    <input type="text" class="form-control" name="availability" id="availability" min="0">
			  </div>
			  <div class="col-md-4">
			    <label for="inputState" class="form-label" name="food_category" id="food_category">Category</label>
			    <select id="food_category" name="food_category" class="form-select">
					<option value="Tamilian">Tamilian</option>
					<option value="Japanese">Japanese</option>
					<option value="Indian">Indian</option>
					<option value="Italian">Italian</option>
					<option value="other">Other</option>
			    </select>
			  </div>
			  <div class="col-md-2">
			    <label for="inputImageName" class="form-label">Image Name</label>
			    <input type="text" class="form-control" name="image_alt" id="image_alt">
			  </div>
			  <div class="col-12">
			    <button type="submit" class="btn btn-dark" id="add_food_btn">Add</button>
			  </div>
			</form>
		  </div>
		  
		  <div id="delete_food_form_tab" class="p-5 zero_visibility">
			<form class="row gy-2 gx-3 align-items-center" action="delete_food" method="post">
			  <div class="row gy-2 gx-3 align-items-center" id="delete_food_form"></div>
			  <div class="col-auto">
			    <button type="submit" class="btn btn-dark delete_food_btn">Delete</button>
			  </div>
			</form>
			</div>
	  
	  	  <div id="increase_count_form_tab" class="p-5 zero_visibility">
  				<form class="row gy-2 gx-3 align-items-center" action="increase_food" method="post">
  					<div class="row gy-2 gx-3 align-items-center" id="increase_food_form"></div>
				  	<div class="col-auto">
				    	<button type="submit" class="btn btn-dark delete_food_btn">Change</button>
				  	</div>
				</form>
			</div>
	  	  </div>
	
	<section class="container-fluid bg-dark flex flex-column justify-content-center align-items-center" style="color: white;">
		<%@ include file="bye_page.jsp" %>
	</section>
	
	<script type="text/javascript" src="javascript/app.js"></script>
	
	<script type="text/javascript">
		getFoodList();
		
		function selectFood(id) {
			var checkBox = document.getElementById(id);
			
			if (checkBox.checked) {
				var slider = document.getElementById(id + ".slider");
				var display = document.getElementById(id + ".display"); 
				
				slider.removeAttribute("disabled");
				
				slider.addEventListener("mousemove", (event) => {
					display.setAttribute("placeholder", "+ " + slider.value);
				});
			}
			else 
				document.getElementById(id + ".slider")
					.setAttribute("disabled", "disabled");		
		}
		
		document.getElementById("add_food_btn")
			.addEventListener("click", (event) => {
				getFoodList();
			});
		
		var previousSelection1 = "admin_image_url";
		var radioSelection1 = document.getElementById("admin_image_radio");
		
		function selectedUploadType1(selectedValue1) {			
			document.getElementById(selectedValue1).removeAttribute("disabled");
			document.getElementById(previousSelection1).setAttribute("disabled", "disabled");
			
			previousSelection1 = selectedValue1;
		}
	
		function getFoodList() {		
			const XHR = new XMLHttpRequest();
			let foodList = null;
			
			XHR.open("get", "/thakkali/get_food_4_admin", true);
			
			XHR.onload = function () {
				if (this.status == 200 || this.status == 304) {
					foodList = JSON.parse(this.responseText);
					deleteFoodPopulate(foodList);
					increaseFoodPopulate(foodList);
				}
			};
			
			XHR.send();	
		}
		
		function deleteFoodPopulate(foodList) {
			let output = "";
			
			for (let i = 0; i < foodList.data.length; i++) {
				output += 
					"<div class='col-auto form-check'> " +
					"<input class='form-check-input' type='checkbox' name='select_foods' id='select_foods' value='" + foodList.data[i].food_id + "'> " +
					"<label class='form-check-label' for='autoSizingCheck'> " +
					"</label> </div>" +
							
					"<div class='col-auto'> " +
					"<label class='visually-hidden' for='autoSizingInput'>Food name</label> " +
					"<input type='text' class='form-control' disabled='disabled' id='autoSizingInput' placeholder='" + foodList.data[i].food_name + "'> " +
					"</div> " +
							
					"<div class='col-auto'> " +
					"<label class='visually-hidden' for='autoSizingInput'>Quantity</label> " +
					"<input type='text' class='form-control' disabled='disabled' id='autoSizingInput' placeholder='" + foodList.data[i].availability + "'> " +
					"</div> " +
							
					"<div></div>";
			}
			
			document.getElementById("delete_food_form").innerHTML = output;

		}

		function increaseFoodPopulate(foodList) {
			let output = "";
			for (let i = 0; i < foodList.data.length; i++) { 
				if ((100 - foodList.data[i].availability) > 0) {
					output +=
							"<div class='col-auto form-check'> \n" +
		    				"<input class='form-check-input' onclick='selectFood(" + foodList.data[i].food_id + ")' name='values' type='checkbox' id='" + foodList.data[i].food_id + "' value='" + foodList.data[i].food_id + "," + foodList.data[i].availability + "'> \n" +
		    				"<label class='form-check-label' for='autoSizingCheck'> \n" +
		    				"</label> </div> \n";
				}
		    	
				else {
					output +=
							"<div class='col-auto form-check'>\n " +
		    				"<input class='form-check-input' disabled name='values' type='checkbox' id='values' value='" + foodList.data[i].food_id + "," + foodList.data[i].availability + "'> \n" +
		    				"<label class='form-check-label' for='autoSizingCheck'> \n" +
		    				"</label> </div> \n";
				}
				
				output += 		
						"<div class='col-auto'> \n" +
						"<label class='visually-hidden' for='" + foodList.data[i].food_id + ".name'>Food name</label> \n" +
						"<input type='text' class='form-control' disabled='disabled' id='" + foodList.data[i].food_id + ".name' placeholder='" + foodList.data[i].food_name + "'> \n" +
						"</div> \n" +
								
						"<div class='col-auto'> \n" +
						"<label class='visually-hidden' for='" + foodList.data[i].food_id + ".quantity'>Quantity</label> \n" +
						"<input type='text' class='form-control' disabled='disabled' id='" + foodList.data[i].food_id + ".quantity' placeholder='" + foodList.data[i].availability + "'> \n" +
						"</div> \n" +

						"<div class='col-auto'> \n" +
						"<label class='visually-hidden' for='" + foodList.data[i].food_id + ".display'>Extra</label> \n" +
						"<input type='text' class='form-control' disabled='disabled' id='" + foodList.data[i].food_id + ".display' placeholder='+ 0'> \n" +
						"</div> \n" +
						
						"<input type='range' disabled='disabled' class='form-range ms-5' style='width: 30%' min='0' max='" + (100 - foodList.data[i].availability) + "' steps='1' name='" + foodList.data[i].food_id + "' id= '" + foodList.data[i].food_id + ".slider'> \n" +
						"<output for='" + foodList.data[i].food_id + "' onforminput='value=foo.valueAsNumber;'></output> \n" +
								
						"<div></div> \n";
			}
			
			document.getElementById("increase_food_form").innerHTML = output;
			
		}
		
	</script>

</body>


</html>