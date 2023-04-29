/**
 * 
 */
package A3;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Stephanie G.
 *
 */
public class Appointment {

	 
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
	private Date StartTime;
	private Date EndTime;
	private String Name;
	private String Place;
	private int Duration;
	
	  
	public Appointment(String apptmntDate, int duration) {
		    
		this.StartTime = new Date();
		this.EndTime = new Date();
		this.Duration = duration;
	    this.Name = "";
	    this.Place = "";
		
		try {
		  StartTime = sdf.parse(apptmntDate);
		} catch (Exception e) {
		  System.out.println();  
		}
		    
		setEndTimeSlot(StartTime);

	}
	  
	private void setEndTimeSlot(Date apptmntDate) {

		long min = apptmntDate.getTime();
		EndTime.setTime(min + (this.Duration * 60 * 1000));
		
		// System.out.println(this.EndDT);
	}
	
	// Setters and getters
	public Date getStartTime() {
		return this.StartTime;
	}
		  
	public Date getEndTime() {
		return this.EndTime;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	  
	public String getName() {
	    return this.Name;
	}
		  
	public void setPlace(String place) {
		this.Place = place;
	}
  
	public String getPlace() {
		return this.Place;
	}	
	
	// Gets the time and duration of the time slot 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(sdf.format(this.StartTime)).append(" - ")
			.append(this.Duration);
		
		return sb.toString() ;
	}	
	
}
