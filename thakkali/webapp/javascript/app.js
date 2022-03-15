/**
 * 
 */

function canSubmitForm(formName, buttonName) {
	const forms = document.forms[formName].elements;
	let canSubmit = true;
	
	for (let i = 0; i < forms.length; i++)
		if (forms[i].value.length == 0) {
			canSubmit = false;
			break;
		}
	
	console.log(canSubmit);
	
	document.getElementById(buttonName).disabled = !canSubmit;
	
}

var registration_form_button = document.getElementById("registration_form_route");
registration_form_button.addEventListener("click", (event) => {
	
	document.querySelector("#image_section > img").setAttribute("src", "Images/signup-image.jpg");
	
	document.getElementById("form_section").classList.add("d-none");
	document.getElementById("form_section").classList.add("invisible");
	
	document.getElementById("register_form_section").classList.remove("d-none");
	document.getElementById("register_form_section").classList.remove("invisible");
});
 


var myloginButton = document.getElementById("login-btn");
myloginButton.addEventListener("click", (event) => {
	event.preventDefault();
	
	document.querySelector("body").toggleAttribute("style", "overflow: hidden;");
	
	document.getElementById("overlay_container_login")
		.classList.remove("zero_visibility"); 
});

var myCloseButton = document.getElementById("login_close_btn");
myCloseButton.addEventListener("click", (event) => {
	event.preventDefault();
	
	document.querySelector("#image_section > img").setAttribute("src", "Images/signin-image.jpg");
	
	document.getElementById("form_section").classList.remove("d-none");
	document.getElementById("form_section").classList.remove("invisible");
	
	document.getElementById("register_form_section").classList.add("d-none");
	document.getElementById("register_form_section").classList.add("invisible");
	
	document.getElementById("overlay_container_login")
		.classList.add("zero_visibility");
		
});



var addFoodTab = document.getElementById("add_food_tab");
var deleteFoodTab = document.getElementById("delete_food_tab");
var increaseCountTab = document.getElementById("increase_count_tab");

if (addFoodTab != null) 
	addFoodTab.addEventListener("click", (event) => {
			event.preventDefault();
			addFoodTab.classList.add("active");
			document.getElementById("add_food_form_tab").classList.remove("zero_visibility");
			
			deleteFoodTab.classList.remove("active");
			document.getElementById("delete_food_form_tab").classList.add("zero_visibility");
			
			increaseCountTab.classList.remove("active");
			document.getElementById("increase_count_form_tab").classList.add("zero_visibility");
		});

if (deleteFoodTab != null) 
	deleteFoodTab.addEventListener("click", (event) => {
			event.preventDefault();
			deleteFoodTab.classList.add("active");
			document.getElementById("delete_food_form_tab").classList.remove("zero_visibility");
			
			addFoodTab.classList.remove("active");
			document.getElementById("add_food_form_tab").classList.add("zero_visibility");
			
			increaseCountTab.classList.remove("active");
			document.getElementById("increase_count_form_tab").classList.add("zero_visibility");
		});
		
if (increaseCountTab != null) 
	increaseCountTab.addEventListener("click", (event) => {
			event.preventDefault();
			increaseCountTab.classList.add("active");
			document.getElementById("increase_count_form_tab").classList.remove("zero_visibility");
			
			addFoodTab.classList.remove("active");
			document.getElementById("add_food_form_tab").classList.add("zero_visibility");
			
			deleteFoodTab.classList.remove("active");
			document.getElementById("delete_food_form_tab").classList.add("zero_visibility"); 
});




