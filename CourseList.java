
public class CourseList {		// Beginning of class
	
	protected Course[] courses;		// Holds the courses
	protected Course[] sortedCourses;	// Holds the courses in a sorted order
	private int size;					// Current Number of courses
	private int maxCourses = 5;			// Maximum number of courses
	private boolean isSorted = false;	// Are the courses sorted? variable
	
	/**
	 * Default Constructor for the CourseList
	 * Sets the CourseList to empty, but initializes it to size 5
	 */
	public CourseList(){	// Default constructor
		
		setSize(0);				// It is empty
		courses = new Course[maxCourses];	// Initialized to an array of size 5 (programmer's choice)
		sortedCourses = courses;			// sortedCourses array equals the courses array
		
	}	// End constructor
	
	/**
	 * Takes in a course parameter
	 * Adds the course to the list
	 */
	public void addCourse(Course c){	// Adds a course to the list
		
		if (getSize() < maxCourses){	//  If the array isn't full...
			
			courses[getSize()] = c;		// Next index is filled with the Course argument
			setSize(getSize() + 1);		// Increment size by 1
			setSorted(false);			// It is now unsorted
			selectionSort();			// Sort the array
			
		} else {	// If array is full
			
			System.out.println("Cannot hold any more classes");	// Can't add more classes
			
		}	// End if
		
	}	// End addCourse()
	
	/**
	 * Takes in a string parameter
	 * Returns the deleted course
	 */
	public Course delete(String name){	// Deletes a course
		
		Course c = lookFor(name);		// Create the course based off the name argument
		if (c == null){						// If name argument is not a course...
			
			System.out.println(name + " is not a course");	// No course was found
			return null;									// Return null
			
		} else {							// If it is a course
						
			int count = 0;						// Index marker for the for loops
			
			for (Course element: courses){			// For every course in the courses array
				
				if (element.getCourseTitle().equalsIgnoreCase(name)){	// If that course matches the name argument
					
					for (int j = count; j < courses.length; j++){					// For every index after... (Reorganizes array)
					
						if (courses[j+1] == null) {				// If the next course is null
							
							courses[j] = null;						// Current index becomes null
							break;									// Break out of the loop
							
						} else {								// If next course isn't null
							
							courses[j] = courses[j+1];				// Current index's course becomes the next index's course
						}	// End if
					}														// End for
					
					break;													// Break from outer for loop
					
				}	// End if
				
				count++;	// Increment count by 1
				
			}	// End for
			
			setSize(getSize() - 1);	// Decrement size by 1
			setSorted(false);		// Need to Reorganize the sortedCourses array
			selectionSort();		// Re-sort the sortedCourses array
			return c;				// Return the deleted course
			
		}	// End of if-else
		
	}	// End delete()
	
	/**
	 * Takes in a String parameter
	 * Returns a course
	 */
	public Course lookFor(String name){	// Returns a course given a name argument
		
		if (isACourse(name)){		// If it is a course...
			
			for (int i = 0; i < getSize(); i++){	// For every course in the courses array
				
				Course j = courses[i];				// Create a temporary Course based off that array index
				if (j.getCourseTitle().equalsIgnoreCase(name)){	// If the temporary course name is equal to the name argument
					
					return j;	// Return the temporary course
					
				}	// End if
				
			}	// End for
			
			return null;	// If method reaches this point, then something went wrong
			
		} else {	// If name argument isn't a course..
			
			System.out.println(name + " does not match any course title");	// Display that it isn't a course
			return null;	// Return null;
			
		}	// End if=else
		
	}	// End lookFor()
	
	/**
	 * displays the courses in the list
	 */
	public void displayCourses(){	// Displays the sorted Courses
		
		System.out.println("\nCourse Schedule\n-------------------------");	// Prints a header
		int count = 1;	// Part of course organization
		for (Course element : sortedCourses){	// For every course
			
			if (element == null){	// If it is null
				break;	// Leave the for loop
				
			} else {	// If it isn't null...
				
				System.out.println("Course " + count + ": " + element.getCourseTitle() + "  " + element.getStartTime() + 
						" - " + element.getEndTime());	// Display course
				count++;	// Increment count
				
			}	// End if
			
		}	// End for
		
	}	// End displayCourses()
	
	/**
	 * Takes in a string parameter
	 * Returns true if the string is a course, false if it isn't
	 */
	public boolean isACourse(String course){	// Returns a boolean value, determines whether the given argument is a course
		for (Course element: courses){				// For every listed course
			
			if (element != null){	// If it isn't null
				
				if (element.getCourseTitle().equalsIgnoreCase(course)){	// And its title matches the current element
					return true;										// Then it is a course
					
				}	// End if
				
			}	// End if
			
		}	// End for
		
		return false; // If it isn't a course, return false
		
	}	// End isACourse()
	
	/**
	 * Sorts the courses using the Selection Sort algorithm
	 */
	public void selectionSort(){		// Sorts courses based off start time (Uses a Selection sort)
		
		if (!isSorted()){					// If the array isn't sorted...
			
			for (int n = 0; n < getSize(); n++){	// For every index...
				
				Course c = courses[n];				// Create a temporary course assigned to the current index
				
				for (int i = n + 1; i < getSize(); i++){	// For every index after it
					
					Course d = courses[i];					// Create another temporary course to the current index
					if (d.getStart().before(c.getStart())){		// Compare temporary courses based on start time
						
						courses[i] = c;								// Whichever starts later is reinserted into the array
						c = d;										// The earlier one is saved
						
					}	//	End if
					
				}	// End for
				
				sortedCourses[n] = c;	// The earliest course is placed into the sortedCourses array

			}	// End for
			
			setSorted(true);		// The array is now sorted
			
		} else {	// If array is already sorted
			
			System.out.println("Course list is already sorted");	// Don't re-sort the array
			
		}	// End if
		
	}	// End selectionSort()
	
	/*
	 * displays the seven days of the week
	 */
	
	public void displayDaysOfWeek(){	// Prints Days of the week
		
		System.out.println("Monday\nTuesday\nWednesday\nThursday\nFriday\nSaturday\nSunday");	// Display days
		
	}	// End displayDaysOfWeek()
	
	// Getters and Setters for the variables

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMaxCourses() {
		return maxCourses;
	}

	public void setMaxCourses(int maxCourses) {
		this.maxCourses = maxCourses;
	}

	public boolean isSorted() {
		return isSorted;
	}

	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

}	// End of class
