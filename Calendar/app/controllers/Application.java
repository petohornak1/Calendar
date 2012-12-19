package controllers;

import com.typesafe.plugin.*;

import models.Event;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;
import views.xml.*;


import java.text.*;

public class Application extends Controller {

	static Form<User> signInForm = form(User.class);
	static Form<User> registrationForm = form(User.class);
	static Form<User> identifyForm = form(User.class);
	static Form<User> chasForm = form(User.class);
	static Form<User> chpsForm = form(User.class);
	static Form<Event> eventForm = form(Event.class);
	
	static User logged;			

	public static Result ws(String email, String password) {
		User user = User.signIn(email, password);
		if(user == null){
			return ok(errorResponse.render(email, password));
		} else {
			return ok(okResponse.render(user, Event.getUserEvents(user.id)));
		}
    }
	
	public static Result index() {		
	    return ok(index.render(signInForm, registrationForm, 0));			
	  }
	
	public static Result identify() {		
	    return ok(identify.render(identifyForm));	 		
	  }
	  
	  public static Result signIn() {
			Form<User> filledForm = signInForm.bindFromRequest();
			User signingInUser = filledForm.get();	
			if(signingInUser.email.isEmpty()) {
				return ok(signInError.render(signInForm, signingInUser.email));	
			}
			logged = User.signIn(signingInUser.email, signingInUser.password);
			return logged();
		}
	  
	  public static Result signOut() {
			logged = null;
			return redirect(routes.Application.index());
		}
	  
	  public static Result logged() {
		  if (logged != null) {			
				return ok(calendar.render(logged, eventForm));
			} else {				
				return ok(signInError.render(signInForm, ""));			
			}
	  }
	  
	  public static Result account() {
			if(logged == null) return redirect(routes.Application.index());
			return ok(account.render(logged, chasForm, chpsForm));
		}
	  
	  public static Result events() {
			if(logged == null) return redirect(routes.Application.index());
			return ok(events.render(logged, Event.getUserEvents(logged.id)));
		}
	  
	  public static Result signUp() { 
			Form<User> filledForm = registrationForm.bindFromRequest();
			User newUser = filledForm.get();	
			if (!User.isEmailUnique(newUser.email)) {
				return ok(index.render(signInForm, registrationForm, 1));
			}		
			User.save(newUser);
			return ok(signedUp.render());
		}
	  
	  public static Result changeAccount(Long id, String email, String pass) { 		  
		    Form<User> filledForm = chasForm.bindFromRequest();		    
			User changedUser = filledForm.get(); 
			changedUser.email = email;
			changedUser.password = pass;
			logged = changedUser;
			User.update(id, changedUser);			
			return redirect(routes.Application.logged());
		}
	  
	  public static Result changePass(Long id, String email, String firstName, String lastName, String birthday) { 			
		    Form<User> filledForm = chpsForm.bindFromRequest();		    
			User changedUser = filledForm.get(); 
			if(!logged.password.equals(changedUser.oldPassword)) return ok(changePassError.render());
			changedUser.email = email;	
			changedUser.firstName = firstName;
			changedUser.lastName = lastName;
			changedUser.birthday = birthday;
			logged = changedUser;
			User.update(id, changedUser);			
			return redirect(routes.Application.logged());
		}
	  
	  public static Result sendPass() { 
			Form<User> filledForm = identifyForm.bindFromRequest();
			User user = filledForm.get();
			user = User.sendPass(user.email);
			if (user == null) {
				return ok(identified.render(0, ""));
			} else {
				MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
				mail.setSubject("Calendar password");
				mail.addRecipient(user.email);
				mail.addFrom("Calendar <noreply@calendar.com>");					
				mail.sendHtml("<html><body>Your password: " + user.password + "</body></html>" );				
				return ok(identified.render(1, user.email));
			}						
		}	  
	  
	  public static Result newEvent(Long userId) {
			Form<Event> filledForm = eventForm.bindFromRequest();
			Event event = filledForm.get();	
			event.userId = userId;
			event.done = false;
			Event.create(event);
			return redirect(routes.Application.logged());			
		}
	  
	  public static Result updateEvent(Long id) {
			Event event = Event.getEvent(id);
			event.done = !event.done;
			Event.update(id, event);
			return redirect(routes.Application.events());			
		}
	  
	  public static Result deleteEvent(Long id) {
			Event.delete(id);
			return redirect(routes.Application.events());			
		}
}
