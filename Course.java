/*
 * Various Imports Used for course start/end times, exceptions, hashmap, and scanner
 */

import java.text.DateFormat;		
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Course {		// Beginning of class
	
	Scanner in = new Scanner(System.in);	// Declare a scanner
	
	private int numberStudents;						// Maximum number of students in a Course
	private String[] daysOfWeek = new String[7];	// Holds the meeting dates for the Course
	private String courseTitle;						// Holds the Course Title
	private String acronym;							// If the class has an acronym, stored here
	private Student[] students;						// An array that holds the names of the students
	private String startTime;						// Course start time String input
	private String endTime;							// Course end time String input
	private Date start;								// Course start time as a Date
	private Date end;								// Course end time as a Date
	public int count = 0;							// Number of times per week the course meets
	public int countStudents = 0;					// Number of students currently enrolled
	
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();	// Hash Map declaration
	StudentLinkedList coursell = new StudentLinkedList();		// Each course has an organized linked list of students
		
	/**
	 * Default Constructor for Course
	 * Using prompts, user creates the course
	 * Throws a ParseException if the entered start or end time does not match the format
	 */
	public Course() throws ParseException{	// Default Constructor
		
		try {		// Try to...
			
			System.out.println("What is the name of the course: ");	// Ask for course title
			String name = in.nextLine();							// Capture course title
			setCourseTitle(name);									// Set the course title
			
			System.out.println("Is there an acronym for the course (Yes or No): ");	// Is there an acronym / Course ID?
			String t = in.nextLine();												// User inputs selection
			
			while (!(t.equalsIgnoreCase("yes") || t.equalsIgnoreCase("no"))){		// While the user hasn't entered yes or no...
				
				System.out.println("Is there an acronym for the course (Yes or No): ");	// Re-prompt
				t = in.nextLine();														// Re-input the selection
			}
			
			if (t.equalsIgnoreCase("yes")){											// If user chooses 'yes'
				System.out.println("What is the acronym:");								// Ask for the acronym / Course ID
				t = in.nextLine();														// Capture it
				setAcronym(t);															// Store it
			}																		// If user chooses 'no', then continue
			
			System.out.println("What time does the course start (HH:mm aa)");		// Ask for a start time with a format
			String timeTemp = in.nextLine();										// User inputs a start time
			setStartTime(timeTemp);													// Start time is stored (String)
			System.out.println("\nWhat time does the course end (HH:mm aa)");		// Ask for an end time with a format
			timeTemp = in.nextLine();												// User inputs an end time
			setEndTime(timeTemp);													// End time is stored (String)
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");	// Declares a Date format
			Date d1 = sdf.parse(getStartTime());						// Converts the start time string into a Date
			Date d2 = sdf.parse(getEndTime());							// Converts the end time string into a Date
			setStart(d1);												// Sets start time (Date)
			setEnd(d2);													// Sets end time (Date)
			
			hmap.put(1, "Monday");				// Puts key-1 with value-Monday into hmap
		    hmap.put(2, "Tuesday");				// Puts key-2 with value-Tuesday into hmap
		    hmap.put(3, "Wednesday");			// Puts key-3 with value-Wednesday into hmap
		    hmap.put(4, "Thursday");			// Puts key-4 with value-Thursday into hmap
		    hmap.put(5, "Friday");				// Puts key-5 with value-Friday into hmap
		    hmap.put(6, "Saturday");			// Puts key-6 with value-Saturday into hmap
		    hmap.put(7, "Sunday");				// Puts key-7 with value-Sunday into hmap
			
			int temp;		// User input variable
			System.out.println("\nWhat days of the week does the course meet\nMonday = 1, Tuesday = 2, " // Asks for days that the course meets
					+ "Wednesday = 3, Thursday = 4, Friday = 5, Saturday = 6, Sunday = 7 (type 'done' when finished): ");
			temp = in.nextInt();		// User selects the day
			String tempS = Integer.toString(temp);	// Converts the choice into a string
			
			while (!tempS.equalsIgnoreCase("done")){	// While the user has not input 'done'
				
				if (temp == 1 || temp == 2 || temp == 3 || temp == 4 || temp == 5 || temp == 6 || temp == 7){	// If the user selects one of the days...
					String day = hmap.get(temp);		// Get the value from the entered key
					setDaysOfWeek(day);					// Add it to the days of the week
				} else {		// If user doesn't enter a proper key...
					System.out.println("No days were added\n");	// No days will be added
				}		// End if
				
				System.out.println("Next day (type 'done' to exit): ");	// Re-prompt
				tempS = in.next();	// Capture the next input
				if (isNumeric(tempS)){	// If the input is a number
					temp = Integer.parseInt(tempS);		// Convert input to an integer
				}	// End if
			}	// Once user inputs 'done', continue
			
			System.out.println("How many students are in the class: ");	// Ask for the number of students
			
				int number = in.nextInt();	// Capture the number
				setNumberStudents(number);	// Store the number of students
				this.students = new Student[getNumberStudents()];	// initialize students array to the maximum number of students
				
			System.out.println("Course successfully created");	// Course successfully created
				
		} catch (InputMismatchException e){	// Any InputMismatchException will be caught
			
			System.out.println("Input was not consistent with the prompt. Exiting Course Build \n\n\n");	// Present a message
			setNumberStudents(0);	// Sets the number of students in the class to 0
	
		} catch (NumberFormatException j){	// Any NumberFormatException will be caught
			
			System.out.println("");	// Print some white space
			setNumberStudents(0);	// Set number of students to 0
		}
		
		catch (ParseException e){	// Any date entry that does not match the format will be caught
			
			System.out.println("The date you entered did not match the format I requested"); // Display why user is getting this error
			setNumberStudents(0); // Set number of students to 0
		}	// End of try-catch
		
	}	// End of course constructor

	/**
	 * Returns the days of the week array
	 */
	public String[] getDaysOfWeek() {	// Returns days of the week in a string
		
		String[] temp = new String[7];		// Creates a new array called temp
		
		int count = 0;						// Creates a counter to hold indices
		
		for (String element: daysOfWeek){	// For every day in the array
			
			temp[count] = element;			// Place it into temp
			count++;						// Increment count by 1
			
		}	// End for
		return temp;	// Return the temp array
		
	}	// End getDaysOfWeek()

	/**
	 * Takes in a string daysOfWeek
	 * Adds it to the array holding the weekly meeting dates
	 */
	public void setDaysOfWeek(String daysOfWeek) {	// Adds weekly meeting days to the course
		
		for (int i = 0; i < this.daysOfWeek.length; i++){	// For every day in the array
			
	         if (daysOfWeek.equals(this.daysOfWeek[i])){	// If the day entered is already in the array
	        	 
	        	 System.out.println("You cannot add the same day");	// User cannot add the same day
	        	 return;	// Leave the method
	         }	// End if
	         
		}	// End of for
		
	        	 if (count <= 6){	// If the array is not full
	        		 
	     			this.daysOfWeek[count] = daysOfWeek;	// Add the day to the array
	     			count++;								// Increment count by 1
	     			System.out.println(daysOfWeek + " has been added to the weekly class schedule\n");	// Display that the day has been added
	     		} else {	// If array is full
	     			System.out.println("Too many days have been entered");	// Can't add the day
	     			return;	// Leave the method
	     		}	// End if
	      
	} // End of setDaysOfWeek()
	
	/**
	 * Takes in a Student parameter
	 * Inserts the student into the linked list
	 */
	public void addStudent(Student s){	// Adds a student to the course
		
		if (countStudents < getNumberStudents()){	// If the number of enrolled students is less than the maximum
			
			coursell.insert(s);	// Insert student into Linked List to be organized
			System.out.println("Student " + s.getFullName() + " successfully added to course " + getCourseTitle());	// Student successfully added
			countStudents++;	// Increment the number of enrolled students by 1
			
		} else {	// If course is full
			System.out.println("Could not add " + s.getFullName() + " because it exceeds the total number of students");	// Can't add student to course
		}		// End if
		
	}	// End addStudent()
	
	/**
	 * Prints the names of the students in the course to the console
	 */
	public void displayStudents(){	// Displays the organized list of students
		
		coursell.displayStudents();	// Displays the students from the linked list
		
	}	// End displayStudents()
	
	/**
	 * Takes in a String parameter
	 * Returns true if the string is a number, false if it isn't a number
	 */
	public static boolean isNumeric(String str)	{ // Checks if a string is a number
		
	  return str.matches("-?\\d+(\\.\\d+)?"); 	// If string has any of these characters = true, else = false
	  
	}	// End isNumeric()
	
	/**
	 * Displays a Course's information
	 * No parameters
	 */
	public void displayCourseContent(){	// Displays the course information
		
		System.out.println("\n----------- Course Information -----------");	// Header
		System.out.println("Course Title: " + getCourseTitle());			// Course title
		if (getAcronym() != null)											// If it has an acronym/Course ID
			System.out.println("Course Acronym: " + getAcronym());			// Print it
		System.out.println("Start Time: " + getStartTime());				// Start time
		System.out.println("End Time: " + getEndTime());					// End time
		System.out.println("Meeting Days");									// Meeting Days
		String[] temp = getDaysOfWeek();									// Get the meeting days
		for (String element: temp){											// For every day
			if (element == null)											// If its null..
				break;															// Leave the loop
			System.out.println("\t" + element);								// Display the day
		}	// End for
		
		System.out.println("Current Number of Enrolled Students: " + countStudents);	// Number of enrolled students
		System.out.println("Maximum Enrollment for class: " + getNumberStudents());		// Maximum number of students
		System.out.println("----------- End Course Information -----------");			// End header
		
	}	// End of displayCourseContent()

	/*
	 * Getters and Setters for each of the private variables
	 */
	
	public int getNumberStudents() {
		return numberStudents;
	}

	public void setNumberStudents(int numberStudents) {
		this.numberStudents = numberStudents;
	}
	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}	// End of Class
