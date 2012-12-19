package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class Event extends Model {
	
	public static Finder<Long, Event> find = new Finder(Long.class,Event.class);
	
	@Id
	public Long id;
	
	@Column(nullable = false)
	public Long userId;
	
	@Column(nullable = false)
	public String eventName;
	
	@Column
	public String description;
	
	@Column
	public String place;
	
	@Column(nullable = false)
	public Boolean done;
	
	@Column(nullable = false)
	public String date;	
	
	@Column(nullable = false)
	public String time;	

	public static List<Event> all() {
		return find.all();
	}

	public static void create(Event event) {
		event.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static List<Event> getUserEvents(Long userId){
		List<Event> userEvents = new ArrayList<Event>();
		for(Event e: all()){
			if(e.userId==userId) userEvents.add(e);
		}
		return userEvents;
	}

}
