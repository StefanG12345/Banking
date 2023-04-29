/**
 * 
 */
package A3;

/**
 * @author Stephanie G.
 *
 */
public class Teacher extends Person { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor    
	public Teacher(String firstName, String lastName, String email) {       
		super(firstName, lastName, email);       
	}        
		
	@Override    
	public String toString() {       
		return "Teacher: " + super.toString();    
	} 
	
} 
	
