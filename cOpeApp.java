import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;


public class cOpeApp{
	static ArrayList<Course> courses = new ArrayList<>();

	public static void main(String[] args){
		
		System.out.println(
			"Welcome to cOpeApp\n"+
			"made by Vannes W"
		);

		if(args.length==0){
			System.err.println("[E00] Please include the address of courses file as argument");
			return;
		}
		// Fetch, decode, and print courses
		fetchCourses(args[0]);
		printCourses();

		// Let user launch course page
		Scanner sc = new Scanner(System.in);
		while(true){
			String str = sc.nextLine();
			if(str.equals("exit")) break;
			if(str.equals("-1")) break;
			
			int idx = findCourse(str);
			if(idx<0 || idx>=courses.size()){
				System.err.println("[E02] Chosen course not found");
				continue;
			}
			courses.get(idx).launch();
		}
		// idea: use args[1],etc to put in course code

	}

	// Fetch courses and put it into `ArrayList<Courses> course`
	public static void fetchCourses(String coursesAddress){

		// Make scanner
		File coursesFile = new File(coursesAddress);
		Scanner coursesSC;
		try{
			coursesSC = new Scanner(coursesFile);
		}
		catch(Exception e){
			System.err.println("[E01] Courses file not found");
			return;
		}

		// Index zero for empty course
		// courses.add(new Course());

		// Scan courses file
		while(coursesSC.hasNextLine()){
			Course tmp = new Course();
			
			tmp.code = coursesSC.nextLine();
			if(tmp.code.equals("")) continue; // empty line
			tmp.name = coursesSC.nextLine();
			tmp.url = coursesSC.nextLine();

			courses.add(tmp);
		}
		
		// System.err.println("fetchCourses() completed");
	}

	// print course options
	public static void printCourses(){
		for(int i=0; i<courses.size(); i++){
			System.out.println((i<10 ? " ":"") + i + ". " + courses.get(i).code + "\t" + courses.get(i).name);
		}
	}

	// Fetch which course the user wants to open
	public static int findCourse(String str){
		str = str.toLowerCase();

		// user gives index in the list
		try{
			return Integer.parseInt(str);
		}
		catch(Exception e){

		}

		for(int i=1; i<courses.size(); i++){
			// user gives course code
			if(courses.get(i).code.toLowerCase().equals(str)){
				return i;
			}
			// user gives course name
			if(courses.get(i).name.toLowerCase().contains(str)){
				return i;
			}
		}
		return -1;

	}
}