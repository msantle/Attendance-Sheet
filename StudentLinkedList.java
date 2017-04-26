
public class StudentLinkedList{		// Beginning of class
	
	Student first;					// First student in linked list
	Student previous;				// Previous student in linked list
	int size;						// Size of linked list
	
	/**
	 * Default constructor
	 */
	public StudentLinkedList(){		// Default constructor
		first = null;					// First is null
		previous = first;				// Previous is null
		size = 0;						// Size is 0
	}	// End constructor
	
	/**
	 * Takes in a Student parameter
	 * Inserts the student into the linked list
	 */
	public void insert(Student s){	// Insertion method for the linked list, takes in a student as an argument
		
		if (first == null){			// If first is null
			
			first = s;					// first equals the argument
			
		} else {					// If first isn't null
			
			if (s.getlName().compareTo(first.getlName()) < 0){	// If student argument comes before first
				s.next = first;										// Student points to the first student
				first = s;											// first becomes the student argument
				
			} else if (s.getlName().compareTo(first.getlName()) == 0){	// If student argument's last name and first's last name are the same
																		// Compare the first names
				if (s.getfName().compareTo(first.getfName()) < 0){		// If student argument comes before first...
					s.next = first;											// Student points to first	
					first = s;												// first becomes student
				} else {												// Otherwise
					Student temp = first.next;								// Create temporary student, it equals first.next
					first.next = s;											// Reassign first.next to student
					s.next = temp;											// Student's next becomes temp
				}	// End if
				
			} else {	// If last names are not the same...
				
				Student current = first;	// Create a current student that is initialized to first
				
				for (int i = 0; i < size; i++){	// For every student in the linked list
					
					if (s.getlName().compareTo(current.getlName()) < 0){	// If student's last name comes before current's
						
						previous.next = s;	// Previous.next equals student
						s.next = current;	// student's next is the current student
						break;				// Break out of the loop
						
					} else if ((s.getlName().compareTo(current.getlName()) > 0) && (current.next == null)){	// If student comes after current and current.next is null
						
						current.next = s;		// Current.next becomes the student
						break;					// Break out of the loop
						
					} else if (s.getlName().compareTo(current.getlName()) == 0){	// If current and student's last names are equal, compare their first names
						
						if (s.getfName().compareTo(current.getfName()) < 0){		// If student's first name comes before
							previous.next = s;											// previous.next equals the student
							s.next = current;											// Student's next becomes current
							break;														// Break out of the loop
							
						} else if ((s.getfName().compareTo(current.getfName()) > 0) && (current.next == null)){	// If student comes after current and current.next is null
							
							current.next = s;	// Current.next equals the student
							break;				// Break out of the loop
							
						}	// End if (first names comparisons)
						
					}	// End if (last name comparisons)
					
					previous = current;	// Previous becomes the current student
					current = current.next;	// Current becomes the next student in the linked list
					
				}	// End of for
				
			}	// End if (first name comparisons between student and first)
		
		}	// End if (first is null)
		
		size++;	// Increment the size of the linked list by 1
		
	}	// End insert()
	
	/**
	 * Displays the organized students in the course
	 */
	public void displayStudents(){	// Displays the students in the linked list
		
		System.out.println("First Student (top) to the Last Student (bottom)"	// Prints a header for the Linked list
				+ "\n------------------------------------------ ");		// For neatness
		
		Student current = first;							// New link 'current' initialized to data in 'first'
		while (current != null){						// Until we come to the end...
			System.out.println(current.getFullName());		// Display contents of link
			current = current.next;							// Move on to the next link
		}	// End while
		
	}	// End displayStudents()
	
}	// End of class
