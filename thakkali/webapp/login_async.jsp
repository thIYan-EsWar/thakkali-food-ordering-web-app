<style>
	.modal-backdrop {
		z-index: -4;
	}

</style>

<section class="main login_form zindex-offcanvas m-0 d-flex flex-column justify-content-between w-75" id="login_form_section">
	  <div class="container-fluid h-custom w-100">
	    <div class="row d-flex justify-content-center align-items-center h-100 mt-0">
	      
	      <div class="col-md-9 col-lg-6 col-xl-5" id="image_section">
	        <img src="Images/signin-image.jpg" class="img-fluid"
	          alt="Sample image">
	      </div>
	      
	      <!-- Registration section -->
	      <div class="col-md-9 col-lg-6 col-xl-5 d-none invisible" id="register_form_section">
			<div class="login-form d-flex flex-column mt-0" style="color: black;" id="registration_form">
				<form action="register" id="registration" method="post" enctype="multipart/form-data">
				  <div class="mb-3">
				    <label for="exampleInputName" class="form-label">Full Name</label>
				    <input type="text" class="form-control" id="exampleInputName" aria-describedby="emailHelp" name="name">
				    <div id="nameHelp" class="form-text">Please enter your full name with a space a between every word.</div>
				  </div>
				  
				  <div class="mb-3">
				    <label for="exampleInputEmail1" class="form-label">Email address</label>
				    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email_id">
				    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
				  </div>
				  
	  			  <div class="mb-3">
				    <label for="exampleInputAddress" class="form-label">Residential Address</label>
				    <input type="text" class="form-control" id="exampleInputAddress" aria-describedby="emailHelp" name="address">
				    <div id="addressHelp" class="form-text">Please enter your full residential address.</div>
				  </div>
				  
	  			  <div class="mb-3">
				    <label for="exampleInputNumber" class="form-label">Contact Number</label>
				    <input type="text" class="form-control" id="exampleInputNumber" aria-describedby="emailHelp" name="contact_number">
				    <div id="contactHelp" class="form-text">Please enter your contact number with your respective country code.</div>
				  </div>			  

				  <div class="mb-3">
				    <label for="password" class="form-label">Password</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" name="password" id="password">
				  </div>
	
	  			  <div class="mb-3">
				    <label for="exampleInputUserType" class="form-label">Subscription Type</label>
				    
				    <div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="user_type" id="1_user" value="1" checked>
					  <label class="form-check-label" for="inlineRadio1">Customer</label>
					</div>
					<div class="form-check form-check-inline">
					  <input class="form-check-input" type="radio" name="user_type" id="2_user" value="2" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
					  <label class="form-check-label" for="inlineRadio2">Manager</label>
					</div>
				  </div>

				<!-- Modal -->
				<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="staticBackdropLabel">Register Hotel</h5>
				        <button type="reset" class="btn-close" data-bs-dismiss="modal" aria-label="Close" id="modal_close"></button>
				      </div>
				      <div class="modal-body"><%@ include file="hotel_register_form.jsp" %></div>
					    </div>
					  </div>
					</div>
				  
				  
				  <button type="submit" class="btn btn-outline-dark">Register</button>
				</form>
			</div>	
	      </div>
	      
	      <!-- Login section -->
	      <div class="col-md-9 col-lg-6 col-xl-5" id="form_section">
	        <form action="login" method="post" id="login_form" class="w-100">
	
	          <!-- Email input -->
	          <div class="form-outline mb-4">
	            <input type="email" id="form3Example3" class="form-control form-control-lg"
	              placeholder="Enter a valid email address" name="email_id" />
	            <label class="form-label" for="form3Example3">Email address</label>
	          </div>
	
	          <!-- Password input -->
	          <div class="form-outline mb-3">
	            <input type="password" id="form3Example4" class="form-control form-control-lg"
	              placeholder="Enter password" name="password" />
	            <label class="form-label" for="form3Example4">Password</label>
	          </div>
	
	          <div class="d-flex justify-content-between align-items-center">
	            <!-- Checkbox -->
	            <div class="form-check mb-0">
	              <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
	              <label class="form-check-label" for="form2Example3">
	                Remember me
	              </label>
	            </div>
	            <a href="#!" class="text-body">Forgot password?</a>
	          </div>
	
	          <div class="text-center text-lg-start mt-4 pt-2">
	            <button type="submit" class="btn btn-dark btn-lg"
	              style="padding-left: 2.5rem; padding-right: 2.5rem;" id="login-btn">Login</button>
	            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <button type="button"
	                class="btn btn-link link-danger" id="registration_form_route">Register</button></p>
	          </div>
	        </form>
	      </div>
	
	      </div>
	    </div>
	    
	    <script type="text/javascript">
	    	const modalCloseButton = document.getElementById("modal_close");
	    	
	    	modalCloseButton.addEventListener("onclick", (event) => {
	    		event.preventDefault();
	    		
	    		document.getElementById("").removeAttribute("checked");
	    	});
	    
	    </script>

</section>



