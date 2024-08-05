public class Course{
	String code;	// SC2002
	String name;	// object oriented design and programming
	String url;		// https://ntulearn.ntu.edu.sg/....
	Course(){
		code = name = url = "";
	}

	// Launch course site in browser
	// Currently only support firefox
	public void launch(){
		System.out.println("Launching " + code);
		try{
			Process p = Runtime.getRuntime().exec("firefox " + url);
		}
		catch(Exception e){
			System.err.println("[E20] Failed to launch course site");
		}
	}
}