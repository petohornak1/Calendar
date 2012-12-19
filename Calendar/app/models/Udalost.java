package moduls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Udalost {

	public Long id;
	public String eventName;
	public String description;
	public String place;
	public Boolean done;
	public Date dateD = null;
	public String time;
	public String date;

	public Udalost() {
	}

	public Udalost(long id, String eventName, String description, String place, String date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");		
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.place = place;
		this.date = date;
		this.time = time;
		
		try {
			this.dateD = sdf.parse(date+" "+time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.done = false;
	}
	
	public long getId() {
		return id;
	}

	public String getEventName() {
		return eventName;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public String getPlace() {
		return place;
	}

	public Date getDateD() {
		return dateD;
	}

	public String getTime() {
		return time;
	}
		
	public String getEstimatedTime() {
		Date date = new Date(System.currentTimeMillis());
		Long longDate1 = date.getTime();
		Long longDate2 = dateD.getTime();
		
		Long finalDate = longDate2 - longDate1;
		Long seconds = finalDate/1000;
		
		Long minutes = seconds - ((seconds / 3600) * 3600);
		Long sec = minutes - ((minutes / 60) * 60);
		
		StringBuilder estiTime = new StringBuilder();
		if ((seconds / 3600)<10) estiTime.append("0"+Long.toString(seconds / 3600)+":");
			else estiTime.append(Long.toString(seconds / 3600)+":"); 
		if ((minutes / 60)<10) estiTime.append("0"+Long.toString(minutes / 60)+":");
			else estiTime.append(Long.toString(minutes / 60));
		if (sec<10) estiTime.append("0"+Long.toString(sec));
			else estiTime.append(Long.toString(sec));
		
		return estiTime.toString();
	}
	
	public Boolean getDone() {
		return done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}
	
}
