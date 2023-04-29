/**
 * 
 */
package A3;

import java.io.Serializable;

/**
 * @author Stephanie G.
 *
 */
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;    
	private String lastName;
	private String email;
	 
	public Person() {}
	
	public Person(String firstName, String lastName, String email) 
	{       
		this.firstName = firstName;       
		this.lastName = lastName;       
		this.email = email;    
	}
	
	
	// Getters    
	public String getFirstName() 
	{       
		return firstName;    
	}    
	
	public String getLastName() 
	{       
		return lastName;    
	}    


	public String getFullName() 
	{       
		return lastName.trim() + ", " + firstName.trim();
	}    
	
	public String getEmail() 
	{       
		return email.trim();    
	}        
	
	public String toString() 
	{       
		return getFullName() + "(" + getEmail() + ")";    
	} 
	
}
