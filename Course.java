import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Course {
	
	Scanner in = new Scanner(System.in);
	
	private int numberStudents;
	private String[] daysOfWeek = new String[7];
	private String courseTitle;
	private String acronym;
	private Student[] students;
	public int count = 0;
	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	
	public Course(){
		try {
			
			System.out.println("What is the name of the course: ");
			String name = in.nextLine();
			setCourseTitle(name);
			
			System.out.println("Is there an acronym for the course (Yes or No): ");
			String t = in.nextLine();
			// Maybe ask "Is there an acronym for the course? If yes, enter acronym. If no, enter no." and get it done at the same time instead of asking multiple questions.
			while (!(t.equalsIgnoreCase("yes") || t.equalsIgnoreCase("no"))){
				System.out.println("Is there an acronym for the course (Yes or No): ");
				t = in.nextLine();
			}
			if (t.equalsIgnoreCase("yes")){
				System.out.println("What is the acronym:");
				t = in.nextLine();
				setCourseID(t);
			}
			
			hmap.put(1, "Monday");
		    hmap.put(2, "Tuesday");
		    hmap.put(3, "Wednesday");
		    hmap.put(4, "Thursday");
		    hmap.put(5, "Friday");
		    hmap.put(6, "Saturday");
		    hmap.put(7, "Sunday");
			
			int temp;
			System.out.println("What days of the week does the course meet\nMonday = 1, Tuesday = 2, "
					+ "Wednesday = 3, Thursday = 4, Friday = 5, Saturday = 6, Sunday = 7 (type 'done' when finished): ");
			temp = in.nextInt();
			String tempS = Integer.toString(temp);
			
			while (!tempS.equalsIgnoreCase("done")){
				if (temp == 1 || temp == 2 || temp == 3 || temp == 4 || temp == 5 || temp == 6 || temp == 7){
					String day = hmap.get(temp);
					setDaysOfWeek(day);
				} else {
					System.out.println("No days were added\n");
				}
				System.out.println("Next day (type 'done' to exit): ");
				tempS = in.next();
				if (isNumeric(tempS) == true){
					temp = Integer.parseInt(tempS);
				}
			}
			
			System.out.println("How many students are in the class: ");
			
				int number = in.nextInt();
				setNumberStudents(number);
				this.students = new Student[getNumberStudents()];
				
			System.out.println("Course successfully created");
				
		} catch (InputMismatchException e){
			
			System.out.println("Input was not consistent with the prompt.");
	
		} catch (NumberFormatException j){
			
			System.out.println("");
		}
	}

	public int getNumberStudents() {
		return numberStudents;
	}

	public void setNumberStudents(int numberStudents) {
		this.numberStudents = numberStudents;
	}

	public String[] getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		for (int i = 0; i < this.daysOfWeek.length; i++){
	         if (daysOfWeek.equals(this.daysOfWeek[i])){
	        	 System.out.println("You cannot add the same day");
	        	 return;
	         }
		}
	        	 if (count <= 6){
	     			this.daysOfWeek[count] = daysOfWeek;
	     			count++;
	     			System.out.println(daysOfWeek + " has been added to the weekly class schedule\n");
	     		} else {
	     			System.out.println("Too many days have been entered");
	     			return;
	     		}
	         
	      
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	// Maybe also get/set CourseID (I assume this is what acronym is "IT 101" or "10456")?
	
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Student[] getStudents() {
		return students;
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}
	
	public void addStudent(Student s){
		if (count < getNumberStudents()){
			students[count] = s;
			count++;
		} else {
			System.out.println("Could not add " + s.getFullName() + " because it exceeds the total number of students");
		}
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?"); 
	}

}
