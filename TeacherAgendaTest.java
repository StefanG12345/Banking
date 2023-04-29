/**
 * 
 */
package A3;

/**
 * @author Stephanie G.
 *
 */
public class TeacherAgendaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		TeacherAgenda agenda = new TeacherAgenda("Stefan");
		
	    // Slot creation test cases
	    Appointment tSlot1 = new Appointment("2023-04-15 09:00:00", 30);
	    boolean bIsResultOk = agenda.createAppointment(tSlot1);
		
	    if(bIsResultOk)
	    	System.out.println(tSlot1 + " successfully created");
	    else
	    	System.out.println(tSlot1 + " already exists");	    
	    
	    
	    Appointment tSlot2 = new Appointment("2023-08-15 09:00:00", 45);
	    bIsResultOk = agenda.createAppointment(tSlot2);
	    
	    // Expected output: 2023-08-15 09:15:00 - 45 minutes cannot be created
	    if(bIsResultOk)
	    	System.out.println(tSlot2 + " successfully created");
	    else
	    	System.out.println(tSlot2 + " cannot be created");
	 
	    
	    Appointment tSlot3 = new Appointment("2023-08-15 11:00:00", 45);
	    bIsResultOk = agenda.createAppointment(tSlot3);

	    // Expected output: 28/08/15 11:00:00 - 45 minutes successfully created 
	    if(bIsResultOk)
	    	System.out.println(tSlot3 + " successfully created");
	    else
	    	System.out.println(tSlot3 + " cannot be created");
	    
	    
	    // Appointment booking test cases 

	    // Student A wants to book an appointment
	    bIsResultOk = agenda.bookAppointment("Student A", "2023-08-15 11:00:00", 30, "CSC-100");
	    
	    /* Expected output: Appointment successfully booked for Student A */
	    if(bIsResultOk)
	    	System.out.println("Appointment successfully booked for Student A");
	    else
	    	System.out.println("Appointment cannot be booked for Student A");	    
	    
	    
	    // Student B wants to book an appointment 
	    bIsResultOk = agenda.bookAppointment("Student B", "2023-08-15 11:00:00", 45, "CSC-100");

	    /* Expected output: Appointment cannot be booked for Student B */
	    if(bIsResultOk)
	    	System.out.println("Appointment successfully booked for Student B");
	    else
	    	System.out.println("Appointment cannot be booked for Student B");
	    
	    
	    // Student A wants to cancel an appointment
	    bIsResultOk = agenda.cancelAppointment("2023-08-15 11:00:00");

	    /* Expected output: Appointment successfully canceled */
	    if(bIsResultOk)
	    	System.out.println("Appointment successfully canceled");
	    else
	    	System.out.println("Appointment cannot be canceled");	    
	    
	    
	    // Student A wants to re-book an appointment 
	    bIsResultOk = agenda.bookAppointment("Student A", "2023-07-15 11:00:00", 30, "CSC-100");

	    /* Expected output: Appointment successfully booked for Ram */
	    if(bIsResultOk)
	    	System.out.println("Appointment successfully booked for Student A");
	    else
	    	System.out.println("Appointment cannot be booked for Student A");	    
	    
	    
	    // Student A wants to book an appointment 
	    bIsResultOk = agenda.bookAppointment("Student A", "28/08/15 09:00:00", 20, "CSC-100");

	    /* Expected output: Appointment successfully booked for Student A */
	    if(bIsResultOk)
	    	System.out.println("Appointment successfully booked for Student A");
	    else
	    	System.out.println("Appointment cannot be booked for Student B");	    

	    
	}

}
