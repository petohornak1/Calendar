package controllers;

import com.typesafe.plugin.*;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.text.*;

public class Application extends Controller {

	static Form<User> signInForm = form(User.class);
	static Form<User> registrationForm = form(User.class);
	static Form<User> identifyForm = form(User.class);
	
	static Calendar cal;
    static int month;
    private static int[] a = new int[42];
	static int firstDay;
	static int lastDay;
	static int monthInYear;
	static int year;



	public static Result index() {
		Application.cal();
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
			User user = User.signIn(signingInUser.email, signingInUser.password);
			if (user != null) {			
				return ok(calendar.render(user, month, monthInYear));
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

  public static  void cal() {
			
        DateFormat formater;
		formater = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		formater.format(date);
		cal=Calendar.getInstance();
	
		cal.set(Calendar.DAY_OF_MONTH,0);
		firstDay = cal.get(Calendar.DAY_OF_WEEK);
		cal.setTime(date);
	
		
		month = cal.get(Calendar.DAY_OF_MONTH);
		
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		monthInYear = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		calculate();

	}
 
 public static int den(int i){
 int help = a[i];
 return help;
 
 }

 public static void prevMonth(){
	 int previousMonth = monthInYear-1;
	 
	 cal.set(Calendar.MONTH,previousMonth);
 month=cal.get(Calendar.DAY_OF_MONTH);
 lastDay= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 monthInYear=cal.get(Calendar.MONTH);
 year=cal.get(Calendar.YEAR);;

 }


public static void calculate(){
	int helper;
	int previousMonthMax;
	int previousMonth;	
	if(monthInYear!=1){
		previousMonth=monthInYear-1;
	} else {
		previousMonth=11;

	}
	cal.set(Calendar.MONTH, previousMonth);	
	previousMonthMax=cal.getActualMaximum(Calendar.DAY_OF_MONTH);	
	cal.set(Calendar.MONTH, monthInYear);
	switch(firstDay){
		case 1: 
			a[0]=1;
			helper=1;
			for(int i=1;i<lastDay;i++){
				helper++;
				
					a[i]=helper;
				
			}
			helper=1;
			for(int i=lastDay;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 2: a[0]=previousMonthMax;
			a[1]=1;
			helper=1;
			for(int i=2;i<lastDay+1;i++){
				helper++;
				
					a[i]=helper;
				
			}
			helper=1;
			for(int i=lastDay+1;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 3: a[0]=previousMonthMax-1;
			a[1]=previousMonthMax;
			a[2]=1;
			helper=1;
			for(int i=3;i<lastDay+2;i++){
				helper++;
				
					a[i]=helper;
				
			}
			helper=1;
			for(int i=lastDay+2;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 4: a[0]=previousMonthMax-2;
			a[1]=previousMonthMax-1;
			a[2]=previousMonthMax;
			a[3]=1;
			helper=1;
			for(int i=4;i<lastDay+3;i++){
			
				helper++;	
				a[i]=helper;
			
			}
			helper=1;
			for(int i=lastDay+3;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 5: a[0]=previousMonthMax-3;
			a[1]=previousMonthMax-2;
			a[2]=previousMonthMax-1;
			a[3]=previousMonthMax;
			a[4]=1;
			helper=1;
			for(int i=5;i<lastDay+4;i++){
					helper++;	
					a[i]=helper;
			
			}
			helper=1;
			for(int i=lastDay+4;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 6: a[0]=previousMonthMax-4;
			a[1]=previousMonthMax-3;
			a[2]=previousMonthMax-2;
			a[3]=previousMonthMax-1;
			a[4]=previousMonthMax;
			a[5]=1;
			helper=1;
			for(int i=6;i<lastDay+5;i++){
				helper++;
				
					a[i]=helper;
				
			}
			helper=1;
			for(int i=lastDay+5;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		case 7: a[0]=previousMonthMax-5;
			a[1]=previousMonthMax-4;
			a[2]=previousMonthMax-3;
			a[3]=previousMonthMax-2;
			a[4]=previousMonthMax-1;
			a[5]=previousMonthMax;
			a[6]=1;
			helper=1;
			for(int i=7;i<lastDay+6;i++){
				helper++;
				
					a[i]=helper;
				
			}
			helper=1;
			for(int i=lastDay+6;i<=41;i++){
				a[i]=helper;
				helper++;
			}
			break;
		default: System.out.println("Something went wrong");
			break;
	}
			
	
}

}
