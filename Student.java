import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {	// Beginning of class
	
	private String fName;		// First name
	private String lName;		// Last name
	private String fullName;	// Full, concatenated name
	public Student next;		// Next student
	public Student previous;	// Previous student
	
	Scanner in = new Scanner(System.in);	// Scanner for input
	
	/**
	 * Constructor for the student object
	 */
	public Student(){	// Student constructor
		
		try {	// Try to...
			
			System.out.println("What is the student's first name: ");	// Ask for first name
			String temp = in.nextLine();								// Capture it
			setfName(temp);												// Store it
			System.out.println("What is the student's last name: ");	// Ask for last name
			temp = in.nextLine();										// Capture it
			setlName(temp);												// Store it
			temp = getfName() + " " + getlName();						// Concatenate first and last names
			setFullName(temp);											// Set full name
			
		} catch (InputMismatchException e){			// Catch an InputMismatchException
			
			System.out.println("Input was not consistent with the prompt");	// User didn't input correctly
			return;	// Return nothing
			
		}	// End of try-catch
		
	}	// End of student constructor
	
	// Getters and setters for the student properties
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

}	// End of class
