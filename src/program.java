import java.io.File; // READER
import java.util.Scanner; // PARSER
import java.util.ArrayList; // ARRAY

public class program {
	public static void main(String[] Args){
		// PRINT title
		title();
		
		// CREATE rule list 'array' and tape 'array'
		ArrayList<Rule> ruleList = new ArrayList<Rule>();
		ArrayList<Character> tape = new ArrayList<Character>();
		
		// READ rules from file, VALIDATE line by line and WRITE to rule list 'array'
		parseFileTo(ruleList);
	
		// VALIDATE rule list array
		validate(ruleList);
	
		// ASK initial tape and WRITE it to tape 'array'
		askInitialTape(tape);
		
		// EXECUTE turing machine
		turingMachine(ruleList, tape);
	}
	public static void title() {
		// CREATE scanner 'object'
		Scanner file = null;
		
		// PRINT title
		try{
			file = new Scanner(new File("text.txt"));
			while(file.hasNextLine()){
				System.out.println(file.nextLine());
			}	
		}catch (Exception e){}
	}
	public static void parseFileTo(ArrayList<Rule> ruleList){
		// CREATE scanner 'object', line 'string', line count 'int' and rule count 'int'
		Scanner scannedFile = null;
		String lineAsString;
		int l = 0, r = 0;
		
		// ASK file path, READ file from path and WRITE file to scanner 'object'
		try{
			scannedFile = new Scanner(new File(askFilePath()));
		}catch(Exception e1){
			System.out.println("File not found. Closing...");
			System.exit(0);
		}
		
		// PARSE scanner 'object' and WRITE to rule list 'array'
		try{
			while (scannedFile.hasNextLine()){
				// LINE found
				l++;
				
				// COPY line to 'String'
				lineAsString = scannedFile.nextLine();
				
				// IGNORE comments
				if(lineAsString.length() != 0 && lineAsString.charAt(0) != '/'){
					// RULE found
					r++;
					
					// COPY line to 'object'
					Rule line = createLineFrom(lineAsString);
					if(validate(line)){
						System.out.print("["+l+", "+r+"]   ");
						line.println();
						ruleList.add(line);
					}
					else System.out.println("Error in line "+l+", in rule "+r);
				}
			}
		}catch(Exception e1){
			System.out.println("Coudn't read file. Closing...");
			System.exit(0);
		}
		scannedFile.close();
	}
	public static String askFilePath(){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("\n\nType path for rule list's file and press 'Enter'. (Default: rules.txt) : ");
		String text = null;
		try{
			text = keyboard.nextLine();
			if(text.length()==0){
				text = "rules.txt";
			}
			System.out.println("You choose '"+text+"' .\n");
		}
		catch(Exception e){
			System.out.println("Wrong input. Closing...");
			System.exit(0);
		}
		return text;
	}
	public static Rule createLineFrom(String rule){
		// CREATE scanner 'object' and result 'object'
		Scanner ruleScan = new Scanner(rule);
		Rule result = new Rule();
		
		// PARSE rules from 'string' to 'object'
		result.q = Integer.parseInt(ruleScan.next());
		result.e = ruleScan.next().charAt(0);
		result.p = Integer.parseInt(ruleScan.next());
		result.f = ruleScan.next().charAt(0);
		result.m = Integer.parseInt(ruleScan.next());
		
		// RETURN result 'object'
		return result;
	}
	public static boolean validate (Rule line){
		int q = line.q;
		char e = line.e;
		int p = line.p;
		char f = line.f;
		int m = line.m;
		boolean intQ = (q >= 1), charE = ("01abxyh".indexOf(e) != -1), intP = (p >= 0), charF = ("01abxyh".indexOf(f) != -1), intM = (m == 1 || m == -1);
		if(intQ && charE && intP && charF && intM){
			return true;
		}else return false;
	}
	public static void validate (ArrayList<Rule> ruleList){
		// STEP 1 - CHECK SIZE
		if (ruleList.size() == 0){
			System.out.println("Rules not found. Closing...");
			System.exit(0);
		}
		
		if (ruleList.size() > 200){
			System.out.println("Rules mustn't be more that 200. Closing...");
			System.exit(0);
		}
		
		// STEP 2 - CHECK START CONDITIONS
		int index = -1;
		for (int i = 0; i < ruleList.size(); i++){
		    if (ruleList.get(i).q == 1) {
		        index = i;
		        break;
		    }
		}
		if (index == -1){
			System.out.println("Initial status not found in rule list. Closing...");
			System.exit(0);
		}
		
		// STEP 3 - CHECK STOP CONDITIONS
		index = -1;
		for (int i = 0; i < ruleList.size(); i++){
		    if (ruleList.get(i).p == 0) {
		        index = i;
		        break;
		    }
		}
		if (index == -1){
			System.out.println("Halt status not found in rule list. Closing...");
			System.exit(0);
		}
		
		System.out.println("\nEverything seems okay.\n");
		
/*		// STEP 4 - CHECK STATUS CONTINUITY
		index = -1
		for (int i = 0; i < ruleList.size(); i++){
			if (ruleList.get(i).p == 1 && ) {
				index = i;
				break;
			}
		}
		int s = 1;
		while(s != 0){
			index = -1;
			while(true){
				for (int i = 0; i < ruleList.size(); i++){
					if (ruleList.get(i).p == s) {
						index = i;
						break;
					}
				}
			}
		}*/
	}
	public static void askInitialTape(ArrayList<Character> tape){
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Type initial tape composition and press 'Enter' : ");
		String text = null;
		try{
			text = keyboard.nextLine();
			int i = 0;
			for (i=0; i <= text.length()-1; i++){
				if(text.charAt(i)==' '){
					text = text.substring(0, i) + 'h' + text.substring(i + 1);
				}
				if("01abxyh".indexOf(text.charAt(i)) == -1){
					System.out.println("Wrong tape composition. Closing...");
					System.exit(0);
				}
				tape.add(text.charAt(i));
			}
		}
		catch(Exception e){
			System.out.println("Wrong tape composition. Closing...");
			System.exit(0);
		}
	}
	public static boolean askExecutionType(){
		Scanner keyboard = new Scanner(System.in);
		String text = null;
		System.out.print("Type execution type ('s' for STEP or 'f' for FAST) and press 'Enter'.  (Default: FAST) : ");
		// true is STEP
		// false is FAST
		text = keyboard.nextLine();
		if (text.indexOf("s") == 0){
			return true;
		}else if(text.indexOf("f") == 0){
			return false;
		}else if (text.length() == 0){
			return true;
		}else{
			System.out.println("Wrong type. Closing...");
			System.exit(0);
		}
		return false;
	}
	@SuppressWarnings("null")
	public static void turingMachine(ArrayList<Rule> ruleList, ArrayList<Character> tape){
		// CREATE and SETUP initial variables
		Scanner keyboard = new Scanner(System.in);
		int headPosition = 0;
		int turingMachineStatus = 1;
		int turingMachineStep = 0;
		boolean step = askExecutionType();
		
		// START loop until 'status = 0' (halted)
		while(true){
			// SEARCH for rule
			try{		
				// CHECK if there is space avalaible
				try{
					tape.get(headPosition);
				}catch(Exception e){
					tape.add(headPosition, ' ');
				}
				
				// LOOP through ruleList 'array'
				int index = -1;
				for (int i = 0; i < ruleList.size(); i++){
					if (ruleList.get(i).e == tape.get(headPosition) && ruleList.get(i).q == turingMachineStatus) {
						index = i;
						break;
					}
				}
			
				// EXCEPTION if rule not found for 'q' and 'e'
				if (index == -1){
					if(step == true){
						System.out.println("\nNo rule found for q = "+turingMachineStatus+" and e = "+tape.get(headPosition));
					}
					Exception e = null;
					throw e;
				}
				
				// EXCEPTION if tape 'array' is too short
				
			
				// APPLY rule
				tape.set(headPosition, ruleList.get(index).f);
				turingMachineStatus = ruleList.get(index).p;
				headPosition = headPosition + ruleList.get(index).m;
				
				// ADD SPACE to tape 'array' if needed
				if(headPosition == -1){
					tape.add(0, ' ');
					headPosition = 0;
				}
				
				// WAIT if step mode is 'true' and render step by step
				if (step == true){
					System.out.println(tape+"     "+turingMachineStep);
					keyboard.nextLine();
				}
			}catch(Exception e){
				if(step == false){
					renderTape(tape, turingMachineStep, step);
					System.out.println(tape+"     \nSteps: "+turingMachineStep);
				}else{
					System.out.println("\nHalted! Closing...");
				}
				System.exit(0);
			}
		}
	}
	public static void renderTape(ArrayList<Character> tape, int turingMachineStep, boolean step){
		
	}
}