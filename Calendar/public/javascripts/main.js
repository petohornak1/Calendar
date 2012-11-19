function validateSignInForm() {
	var email = document.forms["signInForm"]["email"].value;
	var password = document.forms["signInForm"]["password"].value;
	if(email == null || email == ""){
		document.getElementById("email_field").setAttribute("class", "control-group error");		
		document.getElementById("email_help").innerHTML="Enter your email address.";
		document.getElementById("password_field").setAttribute("class", "clearfix");
		document.getElementById("password_help").innerHTML="";
		return false;
	}
	document.getElementById("email_field").setAttribute("class", "clearfix");
	document.getElementById("email_help").innerHTML="";
	if(password == null || password == ""){		
		document.getElementById("password_field").setAttribute("class", "control-group error");
		document.getElementById("password_help").innerHTML="Enter your password.";
		return false;
	}	
	return true;
}

function validateSignUpForm() {	
	
	document.getElementById("email_field").setAttribute("class", "control-group");
	document.getElementById("password_field").setAttribute("class", "control-group");
	document.getElementById("rePassword_field").setAttribute("class", "control-group");
	document.getElementById("firstName_field").setAttribute("class", "control-group");
	document.getElementById("lastName_field").setAttribute("class", "control-group");
	document.getElementById("birthday_field").setAttribute("class", "control-group");
	
	var ok = true;
	var error = "Required field cannot be left blank.";
	
	var email = document.forms["signUpForm"]["email"].value;
	var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;	
	if(email == null || email == ""){
		document.getElementById("email_error").innerHTML=error;
	}	else {
		document.getElementById("email_error").innerHTML="";
	}
	if(!emailPattern.test(email)){
		document.getElementById("email_field").setAttribute("class", "control-group error");		
		ok = false;	
	} 
	
	var password = document.forms["signUpForm"]["password"].value;
	var passwordPattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
	if(password == null || password == ""){
		document.getElementById("password_error").innerHTML=error;
	}	else {
		document.getElementById("password_error").innerHTML="";
	}
	if(!passwordPattern.test(password)){
		document.getElementById("password_field").setAttribute("class", "control-group error");
		document.getElementById("rePassword_field").setAttribute("class", "control-group error");		
		ok = false;	
	} 
	
	var rePassword = document.forms["signUpForm"]["rePassword"].value;
	if(password != rePassword){	
		document.getElementById("rePassword_field").setAttribute("class", "control-group error");
		document.getElementById("rePassword_error").innerHTML="Passwords must match.";
		ok = false;			
	}
	
	var firstName = document.forms["signUpForm"]["firstName"].value;
	if(firstName == null || firstName == ""){
		document.getElementById("firstName_field").setAttribute("class", "control-group error");
		document.getElementById("firstName_error").innerHTML=error;
		ok = false;
	} else {
		document.getElementById("firstName_error").innerHTML="";
	}
	
	var lastName = document.forms["signUpForm"]["lastName"].value;
	if(lastName == null || lastName == ""){
		document.getElementById("lastName_field").setAttribute("class", "control-group error");
		document.getElementById("lastName_error").innerHTML=error;
		ok = false;
	} else {
		document.getElementById("lastName_error").innerHTML="";
	}
	
	var birthday = document.forms["signUpForm"]["birthday"].value;	
	if(birthday == null || birthday == ""){
		document.getElementById("birthday_error").innerHTML=error;	
	}	else {
		document.getElementById("birthday_error").innerHTML="";	
	}
	if(!isValidDate(birthday)){
		document.getElementById("birthday_field").setAttribute("class", "control-group error");		
		ok = false;
	} 					
	
	return ok;
}

function isValidDate(date) {	
    var aoDate, ms, month, day, year;     
    aoDate = date.split("/");     
    if (aoDate.length !== 3) {    	
        return false;
    }  
    
    day = aoDate[0] - 0;
    month = aoDate[1] - 1;     
    year = aoDate[2] - 0;       
    ms = (new Date(year, month, day)).getTime();    
    aoDate = new Date();
    aoDate.setTime(ms);    
    if (aoDate.getFullYear() !== year ||
        aoDate.getMonth() !== month ||
        aoDate.getDate() !== day) {
        return false;
    }
    if(aoDate > (new Date().setFullYear(new Date().getFullYear()-10)) || aoDate < (new Date().setFullYear(new Date().getFullYear()-100))) {
    	return false;
    }
    return true;
}