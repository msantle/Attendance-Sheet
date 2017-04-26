import java.text.ParseException;			// Exception for time input error
import java.util.InputMismatchException;	// Exception for an input mismatch
import java.util.Scanner;					// Scanner class

// Matthew Antle
// CIS 152 Final - Attendance Sheet

public class Driver {				// Beginning of main class
	
	static CourseList cl = new CourseList();			// Main course list, holds every course; referred to as 'grand course list'
	static CourseList monday = new CourseList();	 	// Monday course list, holds courses that meet on Monday
	static CourseList tuesday = new CourseList();		// Tuesday course list, holds courses that meet on Tuesday
	static CourseList wednesday = new CourseList();		// Wednesday course list, holds courses that meet on Wednesday
	static CourseList thursday = new CourseList();		// Thursday course list, holds courses that meet on Thursday
	static CourseList friday = new CourseList();		// Friday course list, holds courses that meet on Friday
	static CourseList saturday = new CourseList();		// Saturday course list, holds courses that meet on Saturday
	static CourseList sunday = new CourseList();		// Sunday course list, holds courses that meet on Sunday
		
	/**
	 * @param args
	 * Throws ParseException if there is an error in the date
	 */
	public static void main(String[] args) throws ParseException {		// Beginning of main method
		
		Scanner in = new Scanner(System.in);			// Scanner declaration
		
		System.out.println("Hello! Welcome to the Attendance Sheet!\nWhat is your name: ");	// Hello prompt
		String nameOfUser = in.nextLine();						// User inputs name
		
		try {		// Try to...
			
			System.out.println(nameOfUser + ", how many courses do you teach: ");	// Figure out the number of courses taught
			cl.setMaxCourses(in.nextInt());							// User inputs number of courses taught

			while (cl.getMaxCourses() <= 0){			// As long as the number of courses is <= 0
				
				System.out.println(nameOfUser + ", how many courses do you teach: ");	// Re-prompt
				cl.setMaxCourses(in.nextInt());							// User re-inputs number of courses taught
				
			}									// End of while
			
		} catch (InputMismatchException e){		// If the user enters something other than the prompt asked for...
			
			System.out.println("I asked for a number... Restart the program...");	// Display an error
			System.exit(1);	// Exit program
			
		}	// End of try-catch
		
		int countClasses = 0;	// countClasses keeps a count of the number of courses entered
		int choice = 0;			// Input for traversing the program
		
		while (!(choice == 10)){	// As long as the choice is not equal to 10 (the exit prompt)...
			
			try {	// Try to 
			
			System.out.println("\n" + nameOfUser + ", What Would You Like To Modify?\n------------------------------");
			System.out.println("1 -- Courses\n2 -- Students\n10 -- Exit Program");	// Prompt a selection
			choice = in.nextInt();	// User inputs their selection
			
			if (choice == 1){	// If user selects 'Courses'...
				
				System.out.println("------------------------------\n1 -- Create a Course"	// Display options
						+ "\n2 -- View Course Schedule" + "\n3 -- Delete Course\n4"
						+ " -- View A Course's Enrollment\n5 -- View Course Information");
				
				choice = in.nextInt();	// User inputs their selection
				System.out.println("------------------------------");	// Border for neatness
				
				if (choice == 1){		// If user selects 'Create a Course'...
					
					if (countClasses < cl.getMaxCourses()){	// And the number of classes already entered does not exceed
															// the maximum number of classes taught...
						
						Course newCourse = new Course();		// Create the course
						if (newCourse.getNumberStudents() > 0){	// If its number of students is more than 0,
																// then it is a course
							
							cl.addCourse(newCourse);			// Add the course to the grand course list
							
								String[] temp = newCourse.getDaysOfWeek();	// Collect the meeting days for the course
								for (String elementDay: temp){				// For every day...
									if (elementDay == null){					// If it is null...
										break;										// then exit
									}											// End if
									
									if (elementDay.equalsIgnoreCase("Monday")){	// If day is Monday...
										monday.addCourse(newCourse);				// Add course to Monday course list
									} else if (elementDay.equalsIgnoreCase("Tuesday")){	// If day is Tuesday...
										tuesday.addCourse(newCourse);						// Add course to Tuesday course list
									} else if (elementDay.equalsIgnoreCase("Wednesday")){ // If day is Wednesday...
										wednesday.addCourse(newCourse);						// Add course to Wednesday course list
									} else if (elementDay.equalsIgnoreCase("Thursday")){ // If day is Thursday...
										thursday.addCourse(newCourse);						// Add course to Thursday course list
									} else if (elementDay.equalsIgnoreCase("Friday")){ // If day is Friday...
										friday.addCourse(newCourse);						// Add course to Friday course list
									} else if (elementDay.equalsIgnoreCase("Saturday")){ // If day is Saturday...
										saturday.addCourse(newCourse);						// Add course to Saturday course list
									} else if (elementDay.equalsIgnoreCase("Sunday")){ // If day is Sunday...
										sunday.addCourse(newCourse);						// Add course to Sunday course list
									} 													// End if statements
								}	// End of for
								
							countClasses++;	// Increment the number of classes created by 1
							
						} else {	// If the number of students is not greater than 0
							
							System.out.println("\nCourse, " 
							+ newCourse.getCourseTitle() + ", was not created");	// Course isn't added to course lists
						}	// End of if statements
						
					} else {	// If the number of classes being taught is equal to the maximum number of classes...
						System.out.println("Class schedule is already full");	// Then a new course can't be created
						
					}	// End of 'Create a Course' 
					
				} else if (choice == 2){	// If user selects 'View Course Schedule'
					
					if (countClasses == 0){	// If no courses have been entered
						
						System.out.println("You haven't entered any courses yet");	// User cannot view schedule
						
					} else {	// If there are courses
					
						System.out.println("What day would you like to view: ");	// Ask which day of courses to view
						cl.displayDaysOfWeek();										// Display the options
						in.nextLine();												// Needed...
						String selection = in.nextLine();							// User inputs their day selection
						if (selection.equalsIgnoreCase("Monday")){	// If selection is Monday...
							monday.displayCourses();					// Display Monday's course schedule
						} else if (selection.equalsIgnoreCase("Tuesday")){	// If selection is Tuesday...
							tuesday.displayCourses();							// Display Tuesday's course schedule
						} else if (selection.equalsIgnoreCase("Wednesday")){	// If selection is Wednesday...
							wednesday.displayCourses();								// Display Wednesday's course schedule
						} else if (selection.equalsIgnoreCase("Thursday")){		// If selection is Thursday...
							thursday.displayCourses();								// Display Thursday's course schedule
						} else if (selection.equalsIgnoreCase("Friday")){		// If selection is Friday...
							friday.displayCourses();								// Display Friday's course schedule
						} else if (selection.equalsIgnoreCase("Saturday")){		// If selection is Saturday...
							saturday.displayCourses();								// Display Saturday's course schedule
						} else if (selection.equalsIgnoreCase("Sunday")){		// If selection is Sunday...
							sunday.displayCourses();								// Display Sunday's course schedule
						} // End of selection if statements
					
					}	// End if
					
					// End of 'View Course Schedule'
					
				} else if (choice == 3){	// If user selects 'Delete Course'
					
					if (countClasses == 0){		// If there are no courses
						
						System.out.println("There are no courses to delete");	// User cannot delete a course
						
					} else {				// If there are courses
						
						System.out.println("What course would you like to delete: ");	// Ask what course to delete
						cl.displayCourses();											// Display the courses
						in.nextLine();													// Needed for User input
						String selection = in.nextLine();								// User inputs their selection
						Course c = cl.delete(selection);								// Selection is stored into a course
						
						if (c != null){													// If it is a course
							
							System.out.println("Course, " + c.getCourseTitle() + ", has been deleted from your schedule");	// Delete it
							String[] temp = c.getDaysOfWeek();							// Program deletes the course from each individual day list
							for (String elementDay: temp){				// For every day...
								
								if (elementDay == null){					// If it is null...
									break;										// then exit the loop
								}											// End if
								
								if (elementDay.equalsIgnoreCase("Monday")){			// If day is Monday...
									monday.delete(selection);							// Delete course from Monday's schedule
								} else if (elementDay.equalsIgnoreCase("Tuesday")){	// If day is Tuesday...
									tuesday.delete(selection);							// Delete course from Tuesday's schedule
								} else if (elementDay.equalsIgnoreCase("Wednesday")){ // If day is Wednesday...
									wednesday.delete(selection);						// Delete course from Wednesday's schedule
								} else if (elementDay.equalsIgnoreCase("Thursday")){ // If day is Thursday...
									thursday.delete(selection);							// ADelete course from Thursday's schedule
								} else if (elementDay.equalsIgnoreCase("Friday")){ // If day is Friday...
									friday.delete(selection);							// Delete course from Friday's schedule
								} else if (elementDay.equalsIgnoreCase("Saturday")){ // If day is Saturday...
									saturday.delete(selection);							// Delete course from Saturday's schedule
								} else if (elementDay.equalsIgnoreCase("Sunday")){ // If day is Sunday...
									sunday.delete(selection);							// Delete course from Sunday's schedule
								} 											// End if statements
								
							}	// End of for
							
						}	// End of if
						
						countClasses--;	// Decrement the number of classes by 1
						
					}	// End if
					
					// End of 'Delete Course'
					
				} else if (choice == 4){	// If user selects 'View a Course's Enrollment'
					
					if (countClasses == 0){	// If there are no courses
						
						System.out.println("Cannot view enrollment");	// No courses have been created
						
					} else {				// If there are courses
						
						System.out.println("What course would you like to view the attendance for: ");	// Ask for what course
						cl.displayCourses();														// Display the courses
						in.nextLine();																// Needed
						String selection = in.nextLine();										// User inputs their selection
							Course c = cl.lookFor(selection);									// Grab the course
							if (c != null){														// If it is a course....
								c.displayStudents();												// Display its students
							}																	// End if
							
					}	// End if
						
						// End of 'View a Course's Enrollment'
						
				} else if (choice == 5){	// If user selects 'View Course Information'
					
					if (countClasses == 0){		// If there are no courses
						
						System.out.println("There are no courses to view information for");	// User cannot view course information
						
					} else {				// If there are courses
					
						System.out.println("What course would you like to view information for: ");	// Ask for a course
						cl.displayCourses();														// Display the courses
						in.nextLine();																// Needed
						String selection = in.nextLine();											// User selects a course
						Course c = cl.lookFor(selection);											// Grab the course
						if (c != null){																// If it is a course...
							c.displayCourseContent();													// Display information
						}																			// End if
						
					}				// End if
					
				} else {								// If user inputs something other than the 5 options...
					
					System.out.println("You didn't answer my prompt correctly");	// They didn't give an appropriate answer, proceed
				
				}	// End of 'Courses'
				
			} else if (choice == 2){	// If user selects 'Students'
				
				System.out.println("------------------------------\n1 -- Create Student");	// Display options for 'Students'
				
				choice = in.nextInt();									// User inputs their choice
				System.out.println("------------------------------");	// For neatness
				
				if (choice == 1){		// If user selects 'Create Student'
					
					if (cl.getSize() == 0){	// If there are no courses
						
						System.out.println("You must create a course before you create students");	// User must create a course first
						
					} else {	// If there is at least one course
						
						Student newStudent = new Student();	// Create the student
						boolean insertion = false;			// He or she has not been inserted into a course yet
						while (!insertion){						// While they haven't been inserted
							System.out.println("\nWhat course would you like to insert " // What course should student be inserted into
								+ newStudent.getFullName() + " into: ");
							
							cl.displayCourses();										// Display course choices
							in.nextLine();												// Needed
							String selection = in.nextLine();							// User inputs their selection
							
							if (cl.isACourse(selection)){								// If it is a course...
								insertion = true;											// Insertion is a success
								Course c = cl.lookFor(selection);							// Grab the selected course and...
								c.addStudent(newStudent);									// Add the student to the course
								
							} else {													// If it isn't a course...
								System.out.println(selection + " does not match any of the classes");	// Doesn't match any courses
							}															// Continue
							
						}	// End while
					
					}	// End if
					
				} // End of 'Create Student'

			} // End of big if statements
			
			} catch (InputMismatchException e){	// Catches an InputMismatchException
				
				System.out.println("You didn't answer my prompt correctly");	// Display the error
				in.nextLine();													// So the loop doesn't continue on forever
				
			}	// End try-catch
			
		}	// End of while
		
		in.close();	// Close the scanner
		
		}	// End of main method

}	// End of Driver
