function validateSignUpForm() {	
	
	document.getElementById("email_field").setAttribute("class", "control-group");
	document.getElementById("email_error").innerHTML="";
	document.getElementById("password_field").setAttribute("class", "control-group");
	document.getElementById("password_error").innerHTML="";
	document.getElementById("rePassword_field").setAttribute("class", "control-group");
	document.getElementById("rePassword_error").innerHTML="";	
	document.getElementById("birthday_field").setAttribute("class", "control-group");
	document.getElementById("birthday_error").innerHTML="";
	
	var ok = true;
	
	var email = document.forms["signUpForm"]["email"].value;
	var password = document.forms["signUpForm"]["password"].value;
	var rePassword = document.forms["signUpForm"]["rePassword"].value;
	var firstName = document.forms["signUpForm"]["firstName"].value;
	var lastName = document.forms["signUpForm"]["lastName"].value;
	var birthday = document.forms["signUpForm"]["birthday"].value;			
	
	if(isBlankSignUpField(email) && isBlankSignUpField(password) && isBlankSignUpField(rePassword) && isBlankSignUpField(firstName) && isBlankSignUpField(lastName) && isBlankSignUpField(birthday)){
		document.getElementById("error").setAttribute("class", "");
		document.getElementById("error").innerHTML="";
	} else {
		document.getElementById("error").setAttribute("class", "alert alert-error");
		document.getElementById("error").innerHTML="You must fill in all of the fields.";
		return false;
	}
	
	var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;		
	if(!emailPattern.test(email)){
		document.getElementById("email_field").setAttribute("class", "control-group error");
		document.getElementById("email_error").innerHTML="Please enter a valid email address.";
		ok = false;	
	} 
	
	var passwordPattern = /(?=.*[a-zA-Z0-9@#$%^&+=]).{6,}/;	
	if(!passwordPattern.test(password)){
		document.getElementById("password_field").setAttribute("class", "control-group error");
		document.getElementById("password_error").innerHTML="Minimum of 6 characters in length.";	
		ok = false;	
	} 
	
	if(password != rePassword){	
		document.getElementById("rePassword_field").setAttribute("class", "control-group error");
		document.getElementById("rePassword_error").innerHTML="Passwords must match.";
		ok = false;			
	}
	
	if(!isValidDate(birthday)){
		document.getElementById("birthday_field").setAttribute("class", "control-group error");
		document.getElementById("birthday_error").innerHTML="Please enter a valid date.";
		ok = false;
	} 
	return ok;
}

function isBlankSignUpField(field) {	
	if(field == null || field == ""){			
		return false;
	}
	return true;	
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