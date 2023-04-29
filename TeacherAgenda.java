package A3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeacherAgenda {

	private ArrayList<Appointment> appointmentList;

	@SuppressWarnings("unused")
	private String agendaName;
	int numberOfTimeSlot;	
	

	public TeacherAgenda(String name) {
		this.agendaName = name;
	    this.numberOfTimeSlot = 0;
	}	

	
	public boolean createAppointment(Appointment timeSlot) {

		int iIsOkToAdd = 0;
		boolean bIsTimeSlotAdded = true;
		
		if (this.numberOfTimeSlot == 0) {
			appointmentList = new ArrayList<Appointment>();
			appointmentList.add(timeSlot);
			
			this.numberOfTimeSlot++;
			return bIsTimeSlotAdded;
		}
		
		
		if (this.numberOfTimeSlot > 0) {
			
			// We need to check all time slots, specifically for time overlaps 
			for (int i = 0; i < appointmentList.size(); i++) {
				if ((appointmentList.get(i).getEndTime()).compareTo(timeSlot.getStartTime()) < 0) {
					iIsOkToAdd = 1;
				}
			}
		      
			if (iIsOkToAdd == 1) {
				appointmentList.add(timeSlot);
				return bIsTimeSlotAdded;
			}
		}

		return !bIsTimeSlotAdded;
	}	
	

	
	public boolean bookAppointment(String name, String date, int dur, String place) {
		    
		boolean bIsAppointmentBooked = true;
		int iSlotIndex = 0;
		
		Date appointmentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			appointmentDate = sdf.parse(date);
		} catch (Exception e) {
			System.out.println();  
		}

		for (int i = 0; i < appointmentList.size(); i++) {
			if (appointmentDate.equals(appointmentList.get(i).getStartTime())) {
				iSlotIndex = i;
		        break;
			}
		}
		    
		if (iSlotIndex > 0) {
			if (appointmentList.get(iSlotIndex).getName().equals("")) {
				appointmentList.get(iSlotIndex).setName(name);
				appointmentList.get(iSlotIndex).setPlace(place);
				
				return bIsAppointmentBooked;
			}
		}
		
		return !bIsAppointmentBooked;
	}	
	
	/**
	 * 
	 * @param date 
	 * @return
	 */
	public boolean cancelAppointment(String date) {
		    
		Date appointmentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    
		try {
			appointmentDate = sdf.parse(date);
		} catch (Exception e) {
			System.out.println();  
		}
		    
		for (int i = 0; i < appointmentList.size(); i++) {
			if (appointmentDate.equals(appointmentList.get(i).getStartTime())) {
				appointmentList.get(i).setName("");
				appointmentList.get(i).setPlace("");

				return true;
			}
		}
		    
		return false;
	}
	
	public ArrayList<Appointment> getAppointmentList() {
		return appointmentList;
	}
	
}
