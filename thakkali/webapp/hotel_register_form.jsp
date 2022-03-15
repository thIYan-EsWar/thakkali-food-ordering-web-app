<form action="register" id="registration" method="post" enctype="multipart/form-data">
	<div class="row mb-3">
	   <label for="hotel_name" class="col-sm-2 col-form-label">Name</label>
	   <div class="col-sm-10">
	     <input type="text" class="form-control" name="hotel_name" id="hotel_name">
	   </div>
	 </div>
	 <div class="row mb-3">
	   <label for="inputPassword3" class="col-sm-2 col-form-label">Address</label>
	   <div class="col-sm-10">
	     <input type="text" class="form-control"  name="hotel_address" id="hotel_address">
	   </div>
	 </div>
	 <fieldset class="row mb-3">
	   <legend class="col-form-label col-sm-2 pt-0">Hotel Image</legend>
	   <div class="col-sm-10">
	   
	<div class="form-check">
	  <input class="form-check-input" type="radio" name="image_radio" id="image_radio" value="image_url" checked onclick="selectUploadType('image_url')">
	  <label class="form-check-label" for="image_url">
		  <div class="col-12">
		    <label for="inputImageUrl" class="form-label">Image URL</label>
		    <input type="text" class="form-control" placeholder="https://www."  name="image_url" id="image_url">
		  </div>
	  </label>
	</div>
		
	 <div class="form-check mt-3">
	   <input class="form-check-input" type="radio" name="image_radio" id="image_radio" value="image_upload" onclick="selectUploadType('image_upload')">
	   <label class="form-check-label" for="image_upload">
		   	<div class="input-group mb-3">
			    <input type="file" class="form-control" id="image_upload" name="image_upload" disabled accept="image/*">
		  	</div>
	    </label>
	  </div>
	  
	  </div>
	    
	  </fieldset>
	     	
	 <div class="modal-footer">
	   <button type="reset" class="btn btn-outline-dark" data-bs-dismiss="modal" id="modal_close">Close</button>
	   <button type="submit" class="btn btn-dark">Submit</button>
	 </div>	

</form>


<script>
	let previousSelection = "image_url";
	let radioSelection = document.getElementById("image_radio");
	
	function selectUploadType(selectedValue) {			
		document.getElementById(selectedValue).removeAttribute("disabled");
		document.getElementById(previousSelection).setAttribute("disabled", "disabled");
		
		previousSelection = selectedValue;
	}

</script>

</script>