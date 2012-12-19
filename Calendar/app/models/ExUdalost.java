package moduls;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class ExUdalost extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Column(nullable = false)
	public String eventName;
	
	@Column(nullable = false)
	public String description;
	
	@Column(nullable = false)
	public String place;
	
	@Column(nullable = false)
	public String time;
	
	@Column(nullable = false)
	public String date;


		public static Finder<Long,ExUdalost> find = new Finder(
		    Long.class, ExUdalost.class
		  );
		
		public static List<ExUdalost> all() {
		  return find.all();
		}
	
		public static void create(ExUdalost exUdalost) {
		  exUdalost.save();
		}
		
		public static void delete(Long id) {
		  find.ref(id).delete();
		}

}
