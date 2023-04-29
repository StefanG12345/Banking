/**
 * 
 */
package A3;

/**
 * @author Stephanie G.
 *
 */
public class Student extends Person { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor    
	public Student(String firstName, String lastName, String email)	{       
		super(firstName,  lastName, email);       
	}
	 
	@Override    
	public String toString() {       
		return "Student: " + super.toString();    
	} 
	 
}
