package controllers;

import java.util.Calendar;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	static Form<User> signInForm = form(User.class);
	static Form<User> registrationForm = form(User.class);
	
	static Calendar cal;
    static int month;
    static int a[];
static int firstDay;
static int lastDay;

  
  public static Result index() {
    return ok(index.render(signInForm, registrationForm, 0));	 	
  }
  
  public static Result signIn() {
		Form<User> filledForm = signInForm.bindFromRequest();
		User signingInUser = filledForm.get();	
		if(signingInUser.email.isEmpty()) {
			return ok(signInError.render(signInForm, signingInUser.email));	
		}
		if (User.signIn(signingInUser.email, signingInUser.password)) {			
			return ok(calendar.render(month));
		} else {			
			return ok(signInError.render(signInForm, signingInUser.email));			
		}
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
  
  public static  void cal() {
		cal =  Calendar.getInstance();
		
		month = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH,0);
		firstDay = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		calculate();	

	}

public static int den(int i) {

return i;

}


public static void calculate(){

	switch(firstDay){
		case 1: a[0]=1;
			for(int i=1;i<=35;i++){
				if(a[i]<= lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 2: a[0]=0;
			a[1]=1;
			for(int i=2;i<=35;i++){
				if(a[i]<= lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 3: a[0]=0;
			a[1]=0;
			a[2]=1;
			for(int i=3;i<=35;i++){
				if(a[i]<=lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 4: a[0]=0;
			a[1]=0;
			a[2]=0;
			a[3]=1;
			for(int i=4;i<=35;i++){
				if(a[i]<=lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 5: a[0]=0;
			a[1]=0;
			a[2]=0;
			a[3]=0;
			a[4]=1;
			for(int i=5;i<=35;i++){
				if(a[i]<=lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 6: a[0]=0;
			a[1]=0;
			a[2]=0;
			a[3]=0;
			a[4]=0;
			a[5]=1;
			for(int i=6;i<35;i++){
				if(a[i]<=lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		case 7: a[0]=0;
			a[1]=0;
			a[2]=0;
			a[3]=0;
			a[4]=0;
			a[5]=0;
			a[6]=1;
			for(int i=7;i<=35;i++){
				if(a[i]<=lastDay){
					a[i]=a[i-1]++;}
				else a[i]=0;
			}
			break;
		default: System.out.println("Something went wrong");
			break;
	}

}

  
}