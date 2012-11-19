package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	static Form<User> signInForm = form(User.class);
	static Form<User> registrationForm = form(User.class);
  
  public static Result index() {
    return ok(index.render(signInForm, registrationForm));
  }
  
  public static Result signIn() {
		Form<User> filledForm = signInForm.bindFromRequest();
		User signingInUser = filledForm.get();						
		if (User.signIn(signingInUser.email, signingInUser.password)) {
			return ok(calendar.render("Vitajte"));
		} else {			
			return ok(index.render(signInForm, registrationForm));
		}
	}
  
  public static Result signUp() { 
		Form<User> filledForm = registrationForm.bindFromRequest();
		User newUser = filledForm.get();	
		if (!User.isEmailUnique(newUser.email)) {
			return ok(index.render(signInForm, registrationForm));
		}		
		User.save(newUser);
		return index();
	}
  
}