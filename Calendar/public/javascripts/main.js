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

function validateChasForm() {			
	
	var ok = true;
	
	var firstName = document.forms["chasForm"]["firstName"].value;
	var lastName = document.forms["chasForm"]["lastName"].value;
	var birthday = document.forms["chasForm"]["birthday"].value;
	
	if(!(isBlankSignUpField(firstName) && isBlankSignUpField(lastName) && isBlankSignUpField(birthday))){		
		document.getElementById("error_chas").setAttribute("class", "alert alert-error");
		document.getElementById("error_chas").innerHTML="You must fill in all of the fields.";
		return false;
	} else {
		document.getElementById("error_chas").setAttribute("class", "");
		document.getElementById("error_chas").innerHTML="";
	}
	
	if(!isValidDate(birthday)){
		document.getElementById("birthday_field_chas").setAttribute("class", "control-group error");
		document.getElementById("birthday_error_chas").innerHTML="Please enter a valid date.";
		ok = false;
	} 
	return ok;
}

function validateChpsForm() {	
	
	document.getElementById("password_field_chps").setAttribute("class", "control-group");
	document.getElementById("password_error_chps").innerHTML="";
	document.getElementById("rePassword_field_chps").setAttribute("class", "control-group");
	document.getElementById("rePassword_error_chps").innerHTML="";
	
	var ok = true;
	
	var oldPassword = document.forms["chpsForm"]["oldPassword"].value;
	var password = document.forms["chpsForm"]["password"].value;
	var rePassword = document.forms["chpsForm"]["rePassword"].value;
	
	if(!(isBlankSignUpField(oldPassword) && isBlankSignUpField(password) && isBlankSignUpField(rePassword))){		
		document.getElementById("error_chps").setAttribute("class", "alert alert-error");
		document.getElementById("error_chps").innerHTML="You must fill in all of the fields.";
		return false;
	} else {
		document.getElementById("error_chps").setAttribute("class", "");
		document.getElementById("error_chps").innerHTML="";
	}		
	
	var passwordPattern = /(?=.*[a-zA-Z0-9@#$%^&+=]).{6,}/;	
	if(!passwordPattern.test(password)){
		document.getElementById("password_field_chps").setAttribute("class", "control-group error");
		document.getElementById("password_error_chps").innerHTML="Minimum of 6 characters in length.";	
		ok = false;	
	} 
	
	if(password != rePassword){	
		document.getElementById("rePassword_field_chps").setAttribute("class", "control-group error");
		document.getElementById("rePassword_error_chps").innerHTML="Passwords must match.";
		ok = false;			
	}	
	return ok;
}

function validateEventForm() {	
	
	document.getElementById("eventName_field").setAttribute("class", "control-group");
	document.getElementById("eventName_error").innerHTML="";
	document.getElementById("date_field").setAttribute("class", "control-group");
	document.getElementById("date_error").innerHTML="";
	document.getElementById("time_field").setAttribute("class", "control-group");
	document.getElementById("time_error").innerHTML="";		
	
	var ok = true;
	
	var event = document.forms["eventForm"]["eventName"].value;
	var date = document.forms["eventForm"]["date"].value;
	var time = document.forms["eventForm"]["time"].value;			
	
	if(isBlankSignUpField(event)) {
		document.getElementById("eventName_field").setAttribute("class", "");
		document.getElementById("eventName_error").innerHTML="";
	} else {
		document.getElementById("eventName_field").setAttribute("class", "control-group error");
		document.getElementById("eventName_error").innerHTML="Required field, cannot be left blank";
		ok = false;
	}
	
	if(isBlankSignUpField(date)) {
		document.getElementById("date_field").setAttribute("class", "");
		document.getElementById("date_error").innerHTML="";
	} else {
		document.getElementById("date_field").setAttribute("class", "control-group error");
		document.getElementById("date_error").innerHTML="Required field, cannot be left blank";
		ok = false;
	}
	
	if(isBlankSignUpField(time)) {
		document.getElementById("time_field").setAttribute("class", "");
		document.getElementById("time_error").innerHTML="";
	} else {
		document.getElementById("time_field").setAttribute("class", "control-group error");
		document.getElementById("time_error").innerHTML="Required field, cannot be left blank";
		ok = false;
	}
	if(!ok) return false;
	
	if(!isValidDate2(date)){
		document.getElementById("date_field").setAttribute("class", "control-group error");
		document.getElementById("date_error").innerHTML="Please enter a valid date.";
		ok = false;
	} 
	var timePattern = /^([0-1]?[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?$/;
	if(!timePattern.test(time)){
		document.getElementById("time_field").setAttribute("class", "control-group error");
		document.getElementById("time_error").innerHTML="Please enter a valid time.";	
		ok = false;	
	} 
	if(ok) window.alert("Event successfully added!");
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

function isValidDate2(date) {	
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
    return true;
}