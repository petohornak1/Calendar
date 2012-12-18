package models;

import play.db.ebean.*;
import javax.persistence.*;

@Entity
public class User extends Model {

	public static Finder<Long, User> find = new Finder(Long.class,User.class);

	@Id
	public Long id;

	@Column(nullable = false, unique = true)
	public String email;

	@Column(nullable = false)
	public String password;

	@Transient
	public String rePassword;

	@Column(nullable = false)
	public String firstName;

	@Column(nullable = false)
	public String lastName; 
 
	@Column(nullable = false)
	public String birthday;
	
	public User getInstance(){
		return this;
	}

	public static boolean isEmailUnique(String email) {
		if (find.where().like("email", email).findUnique() == null) {
			return true;
		} else {
			return false;
		}
	}

	public static void save(User user) {
		user.save();
	}

	public static User signIn(String email, String password) {
		return find.where().like("email", email)
				.like("password", password).findUnique();				
	}
	
	public static User sendPass(String email) {
		return find.where().like("email", email).findUnique();				
	}
}
