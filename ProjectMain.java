/**
 * 
 */
package A3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * @author Stephanie G.
 *
 */
public class ProjectMain {

    /**
     * Main menu options  
     * @return
     */
    public static int getMainMenu(){
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int option = 0;
       
        do {
        	System.out.println("Please select an option: \n"
                + "[1] Teacher \n"
                + "[2] Student \n"
                + "[3] Exit");
        
        	option = sc.nextInt();
        	if(option < 1 || option > 3) {
        		System.out.println("Invalid option. Please try again. \n");
        	}
        } while(option < 1 || option > 3);
        //sc.close();

        return option;
    }
	
    
    /**
     * Teacher menu options  
     * @return
     */
    public static int getTeacherMenu(){
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int option = 0;
       
        do {
        	System.out.println("\nPlease select an option: \n"
                + "[1] Add new teacher \n"
                + "[2] Add new appointment schedule \n"
                + "[3] View my agenda \n"
                + "[4] View student bookings \n"
                + "[5] List of teachers \n"
                + "[6] Save to disk teacher file \n"
                + "[7] Load from disk teacher file \n"
                + "[8] Return to previous menu");
        
        	option = sc.nextInt();
        	if(option < 1 || option > 8) {
        		System.out.println("Invalid option. Please try again. \n");
        	}
        } while(option < 1 || option > 8);
        //sc.close();
        
        return option;
    }
    
    
    /**
     * Student menu options  
     * @return
     */
    public static int getStudentMenu(){
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int option = 0;
       
        do {
        	System.out.println("\nPlease select an option: \n"
                + "[1] Add new student \n"
                + "[2] Book an appointment \n"
                + "[3] Cancel an appointment \n"
                + "[4] View my appointments \n"
                + "[5] List of students \n"
                + "[6] Save to disk student file \n"
                + "[7] Load from disk student file \n"
                + "[8] Return to previous menu");
        
        	option = sc.nextInt();
        	if(option < 1 || option > 8) {
        		System.out.println("Invalid option. Please try again. \n");
        	}
        } while(option < 1 || option > 8);
        //sc.close();

        return option;
    }
	
    
   // Teacher Methods 

    /**
     * Add a new teacher 
     * @param lstTeacher list of teachers
     * @return lstTeacher
     */
    private static ArrayList<Teacher> addTeacher(ArrayList<Teacher> lstTeacher){
        
    	int iIsOkToAdd = 1; 
    	
        if (lstTeacher.size() == 1)
        {
        	System.out.println("The addition is restricted to one (1) teacher only!");
        	return lstTeacher;
        }
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter first name: ");
        String firstName = sc.nextLine();
        
        System.out.println("Enter last name: ");
        String lastName = sc.nextLine();
        
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        // Check if not already in 
        if (lstTeacher.size() > 0)
        {
        	for (int i = 0; i < lstTeacher.size(); i++)
        	{
    			if (email.equals(lstTeacher.get(i).getEmail())) {
    				iIsOkToAdd = 0;
    				break;
    			}
        	}
        }

        // Add the teacher 
        if (iIsOkToAdd == 1)
        {
            Teacher teacher = new Teacher(firstName, lastName, email);
            lstTeacher.add(teacher);
            
            System.out.println("The teacher has been successfully added.\n");
        }
        //sc.close();
        
        return lstTeacher;
    }
    
    
    /**
     * Creates an appointment time slot (office hours) for students to register.
     * @param lstTeacher list of teachers 
     * @param lstTeacherAgenda list of teacher agendas 
     * @return lstTeacherAgenda
     */
    private static ArrayList<TeacherAgenda> addAppointment(ArrayList<Teacher> lstTeacher, ArrayList<TeacherAgenda> lstTeacherAgenda){

    	String inputLine = "";
		Date inDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	if (lstTeacher.size() == 0)
    	{
			System.out.println("There are 0 available teachers! Please add a teacher.");
    		return null;
    	}

    	String lastName = lstTeacher.get(0).getFullName();
    	
    	// Create a teacher agenda
		TeacherAgenda agenda = new TeacherAgenda(lastName);

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\nDo you like to create a new appointment time (Y/N): ");
			inputLine = sc.nextLine().trim();
			
			if ("Y".equals(inputLine))
			{
				System.out.println("Eample: 2023-04-12 09:00:00");
			    System.out.print("Enter date: ");
			    String str = sc.nextLine();
				
			    try {
			    	inDate = sdf.parse(str); 
			    	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	System.out.println("Date: " + sdf.format(inDate));
			      
			    } catch (ParseException e) { 
			    	System.out.println("Parse Exception");
			    }
				
			    System.out.print("Enter the duration in minutes (MM): ");
			    int iTime = Integer.parseInt(sc.nextLine());
			      
			    if (iTime > 0)
			    {
			  	    // Slot creation test cases
			  	    Appointment tSlot = new Appointment(sdf.format(inDate), iTime);
			  	    boolean bIsResultOk = agenda.createAppointment(tSlot);
			  		
			  	    if(bIsResultOk)
			  	    	System.out.println(tSlot + " successfully created.");
			  	    else
			  	    	System.out.println(tSlot + " already exists!");	    
			    }
			}
		} while (!"N".equals(inputLine));    	
		//sc.close();
    	
		lstTeacherAgenda.add(agenda);
    	return lstTeacherAgenda;
    }
    
    
    /**
     * View teacher appointment list 
     * @param lstTeacherAgenda
     */
    private static void viewAgendaList(ArrayList<TeacherAgenda> lstTeacherAgenda){
    
    	StringBuffer sb = new StringBuffer();
    	
    	if (lstTeacherAgenda.size() == 0)
    	{
	    	System.out.println("There are no agenda setup yet!");
    	}
    	
    	for (int i = 0; i < lstTeacherAgenda.size(); i++)
    	{
    		ArrayList<Appointment> lstAppointments = lstTeacherAgenda.get(i).getAppointmentList();
    		if (lstAppointments.size() == 0)
    		{
    	    	System.out.println("There are no appointments setup yet!");
    		}
    		
    		sb.append("\n");
    		for (int j = 0; j < lstAppointments.size(); j++) {
    			sb.append(lstAppointments.get(j).getStartTime())
    				.append(" - ").append(lstAppointments.get(j).getEndTime())
    				.append("\n");
    		}
    	}
    
    	System.out.println(sb.toString());    	
    }
    
    
    /**
     * View students bookings  
     * @param lstTeacherAgenda
     */
    private static void viewStudentRegistration(ArrayList<TeacherAgenda> lstTeacherAgenda){
        
    	StringBuffer sb = new StringBuffer();
    	
    	if (lstTeacherAgenda.size() == 0)
    	{
	    	System.out.println("There are no agenda setup yet!");
    	}
    	
    	for (int i = 0; i < lstTeacherAgenda.size(); i++)
    	{
    		ArrayList<Appointment> lstAppointments = lstTeacherAgenda.get(i).getAppointmentList();
    		if (lstAppointments.size() == 0)
    		{
    	    	System.out.println("There are no appointments setup yet!");
    		}
    		
    		sb.append("\n");
    		for (int j = 0; j < lstAppointments.size(); j++) {
    			
    			sb.append(lstAppointments.get(j).getName())
    				.append(" - ").append(lstAppointments.get(j).getStartTime())
    				.append(" - ").append(lstAppointments.get(j).getEndTime())
    				.append("\n");
    		}
    	}
    
    	System.out.println(sb.toString());    	
    }
    
    
    /**
     * Display the list of teachers 
     * @param lstTeacher
     */
    private static void teachersList(ArrayList<Teacher> lstTeacher){
        
    	StringBuffer sb = new StringBuffer();
    	if (lstTeacher.size() == 0)
    	{
	    	System.out.println("There are no teachers setup yet!");
    	}
    	
		sb.append("\n");
    	for (int i = 0; i < lstTeacher.size(); i++)
    	{
			sb.append(lstTeacher.get(i).toString()).append("\n");
    	}
    
    	System.out.println(sb.toString());    	
    }
    
    
    /**
     * Save the Teachers to hard disk  
     * @param lstTeacher
     */
    private static void saveToDiskTeacherFile(ArrayList<Teacher> lstTeacher){
    
        String fileName = "TeacherData";

        try {
            boolean result = Files.deleteIfExists(Paths.get(fileName));
            if (result) {
                System.out.println("File is deleted!");
            } else {
                System.out.println("Unable to delete the file.");
            }
            
        	FileOutputStream os = new FileOutputStream("TeacherData");
        	ObjectOutputStream oos = new ObjectOutputStream(os);
            
            oos.writeObject(lstTeacher);
            oos.close();
            os.close();

        } catch (IOException ioe) {
        	  
        	System.out.println("There was an error when trying to save data: " + ioe.getMessage());
          
        } finally {
        	  
        	System.out.println("The file teacher has been successfully saved to disk under the name TeacherData");

        }

    }
    
    
    /**
     * Reads the Teacher file from disk 
     * @param lstTeacher
     */
    private static void loadFromDiskTeacherFile(ArrayList<Teacher> lstTeacher){
        
    	lstTeacher.clear();
    	// ArrayList<Teacher> lstTeacherIn = new ArrayList<Teacher>();

    	try {
    		FileInputStream fis = new FileInputStream("TeacherData");
    		ObjectInputStream ois = new ObjectInputStream(fis);

    		while (fis.available() > 0) {
    			lstTeacher.add((Teacher) ois.readObject());
            }    		

    		ois.close();
    	    fis.close();   
    	    
    	   
    	    /*
    	    for (String city : cities) {
    	    	System.out.println(city);
    	    }    	    
    	    */
    	    
    	    
    	} catch (ClassNotFoundException c) {
    		  System.out.println("Class not found exception: " + c.getMessage());
    	} catch (IOException ioe) {
    		System.out.println("There was an error when trying to read data: " + ioe.getMessage());
    	} finally {
    		System.out.println("There teacher file has been successfully imported from disk.");
    	}
    }
    
    
    // Student Methods 
    
    /**
     * Add a new student 
     * @param lstStudent list of students
     * @return lstStudent
     */
    private static ArrayList<Student> addStudent(ArrayList<Student> lstStudent){
        
    	int iIsOkToAdd = 1; 
    	String inputLine = "";
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\nDo you want to add a new student (Y/N): ");
			inputLine = sc.nextLine().trim();
			
			if ("Y".equals(inputLine))
			{
		        System.out.println("Enter first name: ");
		        String firstName = sc.nextLine();
		        
		        System.out.println("Enter last name: ");
		        String lastName = sc.nextLine();
		        
		        System.out.println("Enter email: ");
		        String email = sc.nextLine();
		
		        // We use the email as key for identifying students 
		        // so it needs to be present.
		        if (!email.equals("")) {
			        // Check if not already in 
			        if (lstStudent.size() > 0)
			        {
			        	for (int i = 0; i < lstStudent.size(); i++)
			        	{
			    			if (email.equals(lstStudent.get(i).getEmail())) {
					            System.out.println("\nThe student you are trying to add is already in.\n");
			    				iIsOkToAdd = 0;
			    				break;
			    			}
			        	}
			        }
			
			        // Add the teacher 
			        if (iIsOkToAdd == 1)
			        {
			            Student student = new Student(firstName, lastName, email);
			            lstStudent.add(student);
			            
			            System.out.println("The student has been successfully added.\n");
			        }
		        } else {
		            System.out.println("\nThe student email address is required!\n");
		        }
			}
		} while (!"N".equals(inputLine));    	
		//sc.close();
        
        return lstStudent;
    }
    
    
    /**
     * Book an appointment with the teacher 
     * @param lstStudent
     * @param lstTeacherAgenda
     */
    private static void bookAppointment(ArrayList<Student> lstStudent, ArrayList<TeacherAgenda> lstTeacherAgenda){
    
    	int iIsFound = 0;
    	String inputLine = "";
    	String studentName = "";
    	
		Date inDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        // Check if not already in 
        if (lstStudent.size() > 0)
        {
        	for (int i = 0; i < lstStudent.size(); i++)
        	{
    			if (email.equals(lstStudent.get(i).getEmail())) {
    				studentName = lstStudent.get(i).getFullName() + "-" + lstStudent.get(i).getEmail();
    				iIsFound = 1;
    				break;
    			}
        	}
        }
    	
        
        if ( iIsFound == 1 ) {
        	do {
    			System.out.println("Do you like to book an appointment (Y/N): ");
    			inputLine = sc.nextLine().trim();
    			
    			if ("Y".equals(inputLine))
    			{
    				System.out.println("Eample: 2023-04-12 09:00:00");
    			    System.out.print("Enter date: ");
    			    String str = sc.nextLine();
    				
    			    try {
    			    	inDate = sdf.parse(str); 
    			    	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			    	System.out.println("Date: " + sdf.format(inDate));
    			      
    			    } catch (ParseException e) { 
    			    	System.out.println("Parse Exception");
    			    }
    				
    			    System.out.print("Enter the duration in minutes (MM): ");
    			    int iTime = Integer.parseInt(sc.nextLine());
    			      
    			    if (iTime > 0)
    			    {
        			    System.out.print("Enter place: ");
        			    String place = sc.nextLine();
    			    	
        			    // By default - use only 1 teacher 
        			    TeacherAgenda agenda = lstTeacherAgenda.get(0);
        		    	boolean bIsResultOk = agenda.bookAppointment(studentName, sdf.format(inDate), iTime, place);

        		    	if(bIsResultOk)
        			    	System.out.println("Appointment successfully booked for: " + studentName);
        			    else
        			    	System.out.println("Appointment cannot be booked for: " + studentName);	    
    			    }
    			}
    		} while (!"N".equals(inputLine));    	
    		//sc.close();
        }
        
    }
    
    
    /**
    * Cancel an appointment with the teacher  
     * @param lstTeacherAgenda
     */
    private static void cancelAppointment(ArrayList<TeacherAgenda> lstTeacherAgenda){
        
    	String inputLine = "";
		Date inDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        
    	do {
			System.out.println("Do you like to cancel an appointment (Y/N): ");
			inputLine = sc.nextLine().trim();
			
			if ("Y".equals(inputLine))
			{
				System.out.println("Eample: 2023-04-12 09:00:00");
			    System.out.print("Enter date: ");
			    String str = sc.nextLine();
				
			    try {
			    	inDate = sdf.parse(str); 
			    	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	System.out.println("Date: " + sdf.format(inDate));
			      
			    } catch (ParseException e) { 
			    	System.out.println("Parse Exception");
			    }
				
			    // By default - use only 1 teacher 
			    TeacherAgenda agenda = lstTeacherAgenda.get(0);
		    	boolean bIsResultOk = agenda.cancelAppointment(sdf.format(inDate));
		    	
		    	if(bIsResultOk)
			    	System.out.println("Appointment successfully canceled");
			    else
			    	System.out.println("Appointment cannot be canceled");	    
			    }
		} while (!"N".equals(inputLine));    	
		//sc.close();
    }
    
    /**
     * View all my teacher appointments (the student) 
     * @param lstTeacherAgenda
     */
    private static void viewStudentAppointmentList(ArrayList<Student> lstStudent, ArrayList<TeacherAgenda> lstTeacherAgenda){
        
    	StringBuffer sb = new StringBuffer();
    	int iIsStudentFound = 0;
    	String studentName = "";
   	
    	if (lstTeacherAgenda.size() == 0)
    	{
	    	System.out.println("There are no agenda setup yet!");
    	}
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        
        // Check if not already in 
        if (lstStudent.size() > 0)
        {
        	for (int i = 0; i < lstStudent.size(); i++)
        	{
    			if (email.equals(lstStudent.get(i).getEmail())) {
    				studentName = lstStudent.get(i).getFullName() + "-" + lstStudent.get(i).getEmail();
    				iIsStudentFound = 1;
    				break;
    			}
        	}
        }
    	//sc.close();
        
        if (iIsStudentFound == 1) {
        	for (int i = 0; i < lstTeacherAgenda.size(); i++)
        	{
        		ArrayList<Appointment> lstAppointments = lstTeacherAgenda.get(i).getAppointmentList();
        		if (lstAppointments.size() > 0) {
	        		for (int j = 0; j < lstAppointments.size(); j++) {
	        			if (lstAppointments.get(j).getName().equals(studentName)) {
		        			sb.append(lstAppointments.get(j).getStartTime())
	        					.append(" - ").append(lstAppointments.get(j).getEndTime())
	        					.append("\n");
	        			}
	        		}
        		}
        	}
        }
    
    	System.out.println(sb.toString());    	
    }

    
    /**
     * Display the list of students 
     * @param lstStudent
     */
    private static void studentList(ArrayList<Student> lstStudent){
        
    	StringBuffer sb = new StringBuffer();
    	if (lstStudent.size() == 0)
    	{
	    	System.out.println("There are no students setup yet!");
    	}
    	
		sb.append("\n");
    	for (int i = 0; i < lstStudent.size(); i++)
    	{
			sb.append(lstStudent.get(i).toString()).append("\n");
    	}

    	System.out.println(sb.toString());    	
    }
    

    /**
     * Save the Teachers to hard disk  
     * @param lstTeacher
     */
    private static void saveToDiskStudentFile(ArrayList<Student> lstStudent){
    
    	try (FileOutputStream fos = new FileOutputStream("StudentData");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);) 
    	{
    		oos.writeObject(lstStudent);
    	} catch (IOException ioe) {
    		System.out.println("There was an error when trying to save data: " + ioe.getMessage());
    	} finally {
    		System.out.println("The file student has been successfully saved to disk under the name StudentData");
    	}
    }
    
    
    /**
     * Reads the Student file from disk 
     * @param lstStudent
     */
    private static ArrayList<Student> loadFromDiskStudentFile(){
        
    	ArrayList<Student> lstStudentIn = new ArrayList<Student>();
    	
    	try (FileInputStream fis = new FileInputStream("StudentData");
    			ObjectInputStream ois = new ObjectInputStream(fis);) {

    		while (fis.available() > 0) {
    			lstStudentIn.add((Student) ois.readObject());
            }    		
    		
    	} catch (ClassNotFoundException c) {
    		  System.out.println("Class not found exception: " + c.getMessage());
    	} catch (IOException ioe) {
    		System.out.println("There was an error when trying to read data: " + ioe.getMessage());
    	} finally {
    		System.out.println("There student file has been successfully imported from disk.");
    	}
    	
    	return lstStudentIn;
    }
    
    
    
    
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();  		
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<TeacherAgenda> teacherAgendaList = new ArrayList<TeacherAgenda>();  		
		
        int mainOption = 0; 
        int teacherOption = 0;
        int studentOption = 0; 
        
        do
        {
            mainOption = getMainMenu();
            switch(mainOption){
                case 1:
                	teacherOption = getTeacherMenu();
                	if (teacherOption == 1) {
                		teacherList = addTeacher(teacherList);
                	} else if (teacherOption == 2) {
                		teacherAgendaList = addAppointment(teacherList, teacherAgendaList);
                	} else if (teacherOption == 3) {
                		viewAgendaList(teacherAgendaList);
                	} else if (teacherOption == 4) {
                		viewStudentRegistration(teacherAgendaList);
                	} else if (teacherOption == 5) {
                		teachersList(teacherList);
                	} else if (teacherOption == 6) {
                		saveToDiskTeacherFile(teacherList);
                	} else if (teacherOption == 7) {
                		loadFromDiskTeacherFile(teacherList);
                	} else {
                	}
            		break;
                case 2:
                	studentOption = getStudentMenu();
                	if (studentOption == 1) {
                		studentList = addStudent(studentList);
                	} else if (studentOption == 2) {
                		bookAppointment(studentList, teacherAgendaList);
                	} else if (studentOption == 3) {
                		cancelAppointment(teacherAgendaList);
                	} else if (studentOption == 4) {
                		viewStudentAppointmentList(studentList, teacherAgendaList);
                	} else if (studentOption == 5) {
                		studentList(studentList);
                	} else if (teacherOption == 6) {
                		saveToDiskStudentFile(studentList);
                	} else if (teacherOption == 7) {
                		studentList = loadFromDiskStudentFile();
                	} else {
                	}
            		break;
                case 3:
                	System.out.print("Goodbye!");
                    System.exit(0);
            }
           
        }while(mainOption != 3);
    }

}
