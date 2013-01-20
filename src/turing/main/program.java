/*
 * 		TURING MACHINE 
 * 
 * 		------------------------------------------------------------------------------------
 * 
 * 		Developed and in-line comented by Javier Provecho Fernandez.
 * 			1st Grade Student of Computer Science at University of Valladolid, Spain.
 * 		
 * 		Documented by Alejandro Gracia Gutierrez.
 * 			1st Grade Student of Computer Science at University of Valladolid, Spain.
 * 
 * 		Documentation avalaible at:
 * 			
 * 
 * 		Repository located at:
 * 			http://www.github.com/javierprovecho/Turing
 * 
 * 		License avalaible here:
 * 			http://creativecommons.org/licenses/by-sa/3.0/
 * 
 * 		THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *		IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *		FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF THIRD PARTY RIGHTS. IN
 *		NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 *		DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 *		OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 *		OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * 		------------------------------------------------------------------------------------
 * 
 * 		There are two ways to execute this project:
 * 			- Text mode
 * 			- Graphical mode (GRIDWORLD adaptation)
 * 	
 * 		
 */

package turing.main;

/*
 * Imports:
 * 
 * 		java.io.File			as file reader
 * 		java.util.ArrayList 	as array constructor
 * 		java.util.Scanner		as file scanner and input reader/scanner
 * 		turing.addons.Rule		as custom object for rule data
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import turing.addons.Rule;

public class program {
	public static ArrayList<Rule> ruleList;
	public static ArrayList<Character> tape;
	
	public static void main(String[] Args){
		
		/*
		 * Print title
		 */
		
			title();
		
		/*
		 * Initialize ruleList & tape
		 */

			ruleList = new ArrayList<Rule>();
			tape = new ArrayList<Character>();
		
		/*
		 * Call parseFileTo method
		 */
			
			parseFileTo();
	
		/*
		 * Call validate method
		 */
			
			validate();
	
		/*
		 * Call askInitialTape method
		 */

			askInitialTape();
		
		/*
		 * Call textMode method
		 */
	
			turingMachineTextMode();
			
	}
	public static void title() {
		
		/*
		 * Try to get title from turing.addons and while title has next line, print it.
		 * When finished, close resources.
		 */
		
			try{
				Scanner title = new Scanner(Rule.class.getResourceAsStream("/turing/addons/title.txt"));
				while(title.hasNextLine()){
					System.out.println(title.nextLine());
				}
				title.close(); title = null;
			}catch (Exception e){}
			
	}
	public static void parseFileTo(){
		
		/*
		 * Create initial objects and variables
		 */
			
			Scanner scannedFile = null;
			String lineAsString;
			int l = 0, r = 0;
			Rule line = null;
		
		/*
		 * Try to get file from path returned from calling askFilePath method.
		 * 
		 * If it fails, print error message and exit.
		 */
			
			try{
				scannedFile = new Scanner(new File(askFilePath()));
			}catch(Exception e1){
				System.out.println("File not found. Closing...");
				System.exit(0);
			}
			
		/*
		 * Try to parse the previous readed file if any, with the following conditions:
		 * 
		 * -While file has next line, sum 1 to line counter and execute the following code:
		 * --Copy line to string and check if length is bigger than 0 and ommit comments.
		 * ---Sum 1 to rule counter, convert from string to rule and validate it.
		 * ----If OK, print line and add it to ruleList.
		 * 
		 * -If any error is found while parsing, print line and rule where error was found.
		 * 
		 * If any error is found while reading, print error message and exit.
		 */
			
			try{
				while (scannedFile.hasNextLine()){
					l++;
					lineAsString = scannedFile.nextLine();
					if(lineAsString.length() != 0 && lineAsString.charAt(0) != '/'){
						r++;
						line = createLineFrom(lineAsString);
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
			
		/*
		 * Close resources
		 */
			
			scannedFile.close(); scannedFile = null; lineAsString = null; line = null;
			
	}
	public static String askFilePath(){
		
		/*
		 * Create initial objects and variables
		 */
			
			Scanner keyboard0 = new Scanner(System.in); String text = null;
			
		/*
		 * Ask user to type path to rule list's file.
		 * If blank, use default.
		 * If error is found, print error message and exit.
		 */
			
			System.out.print("\n\nType path for rule list's file and press 'Enter'. (Default: rules.txt) : ");
			try{
				text = keyboard0.nextLine();
				if(text.length()==0){
					text = "rules.txt";
				}
				System.out.println("You choose '"+text+"' .\n");
			}
			catch(Exception e){
				System.out.println("Wrong input. Closing...");
				System.exit(0);
			}
			
		/*
		 * Close resources and return string 'text'.
		 */
			
			keyboard0 = null;
			return text;
			
	}
	public static Rule createLineFrom(String rule){
		
		/*
		 * Create initial object.
		 */

			Scanner ruleScan = new Scanner(rule);
		
		/*
		 * Parse from string to object, one parameter each time. Close resources and return new rule constructed by parsed parameters.
		 */
			
			return new Rule(Integer.parseInt(ruleScan.next()), ruleScan.next().charAt(0), Integer.parseInt(ruleScan.next()), ruleScan.next().charAt(0), Integer.parseInt(ruleScan.next()));
			
	}
	public static boolean validate (Rule line){
		
		/*
		 * If conditions apply, return true.
		 * Else return false.
		 */
		
			if(line.q >= 1 && "01abxyh".indexOf(line.e) != -1 && line.p >= 0 && "01abxyh".indexOf(line.f) != -1 && (line.m == 1 || line.m == -1)) return true;
			else return false;
			
	}
	public static void validate (){
		
		/*
		 * Check ruleList size conditions.
		 * Mustn't be 0 and bigger than 200.
		 * If error found, print error message and exit.	
		 */
		
			if (ruleList.size() == 0){
				System.out.println("Rules not found. Closing...");
				System.exit(0);
			}
			
			if (ruleList.size() >= 200){
				System.out.println("Rules mustn't be more that 200. Closing...");
				System.exit(0);
			}
		
		/*
		 * Check start condition
		 * Must be one rule that it initial state is 1.
		 * If error found, print error message and exit.
		 */
			
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
			
		/*
		 * Print OK message.
		 */
			
			System.out.println("\nEverything seems okay.\n");
			
	}
	public static void askInitialTape(){
		
		/*
		 * Create initial objects and variables
		 */
		
			Scanner keyboard = new Scanner(System.in); String text = null;
			
		/*
		 * Ask user to type initial tape and validate it with the symbols provided.
		 * Add blank symbols ('h') at start and at the end of the tape.
		 * If error is found, print error message and exit.
		 */
			
			System.out.print("Type initial tape composition and press 'Enter' : ");
			try{
				text = keyboard.next();
				if (text.length() != 0){
					for(int i = 0; i<=9; i++){
						tape.add('h');
					}
				}
				for (int i=0; i <= text.length()-1; i++){
					if(text.charAt(i)==' '){
						text = text.substring(0, i) + 'h' + text.substring(i + 1);
					}
					if("01abxyh".indexOf(text.charAt(i)) == -1){
						System.out.println("Wrong tape composition. Closing...");
						System.exit(0);
					}
					tape.add(text.charAt(i));
				}
				for(int i = 0; i<(9+text.length()); i++){
					tape.add('h');
				}
			}
			catch(Exception e){
				System.out.println(e+"Wrong tape composition. Closing...1");
				System.exit(0);
			}
			
			/*
			 * Close resources
			 */
			
				keyboard = null; text = null;
				
	}
	public static void turingMachineTextMode(){
		
		/*
		 * Create initial objects and variables
		 */
		
			Scanner keyboard = new Scanner(System.in);
			int headPosition = 10, turingMachineStatus = 1, turingMachineStep = 0;
			boolean step = askExecutionType();
		
		/*
		 * Start Turing Machine Logic.
		 * Print each movement only if step = TRUE. Else print last movement.
		 */
			
			while(true){
				try{		
					/*
					 * Check if there is space avalaible.
					 */
					
						try{
							tape.get(headPosition);
						}catch(Exception e){
							tape.add(headPosition, 'h');
						}
					
					/*
					 * Search for rule in ruleList that apply for turingMachineStatus and symbol at headPosition. 
					 */
						
						int index = -1;
						for (int i = 0; i < ruleList.size(); i++){
							if (ruleList.get(i).e == tape.get(headPosition) && ruleList.get(i).q == turingMachineStatus) {
								index = i;
								break;
							}
						}

					/*
					 * Exception if there is no rule that apply.
					 */
						
						if (index == -1){
							if(step == true){
								System.out.println("\nNo rule found for q = "+turingMachineStatus+" and e = '"+tape.get(headPosition)+"'");
							}
							break;
						}
					
					/*
					 * Apply the rule.
					 */

						tape.set(headPosition, ruleList.get(index).f);
						turingMachineStatus = ruleList.get(index).p;
						headPosition = headPosition + ruleList.get(index).m;
					
					/*
					 * Add space at the start of the tape if needed.
					 */
						
						if(headPosition == -1){
							tape.add(0, ' ');
							headPosition = 0;
						}
					
					/*
					 * Sum 1 to step counter.
					 */
						
						turingMachineStep++;
					
					/*
					 * ONLY WHEN STEP = TRUE
					 * Render each step when 'enter' key is pressed.
					 */
						
						if (step == true){
							System.out.println(tape+"     "+turingMachineStep);
							keyboard.nextLine();
						}
						
				}catch(Exception e){}
			}
			
			/*
			 * ONLY WHEN STEP = FALSE
			 * Render last step.
			 */
			
				if(step == false){
					System.out.println("\n"+tape+"     \nSteps: "+turingMachineStep);
				}
			
			/*
			 * Close resources
			 */
			
				keyboard.close(); keyboard = null; 
			
			/*
			 * Show exit message 
			 */
			
				System.out.println("\nHalted! Closing...");
			
	}
	public static boolean askExecutionType(){
		
		/*
		 * Create initial objects and variables
		 */
		
			Scanner keyboard = new Scanner(System.in); String text = null;
			
		/*
		 * Ask user execution type, close resources and return boolean.
		 * TRUE=step
		 * FALSE=fast
		 */
			
			System.out.print("\nType execution type ('s' for STEP or 'f' for FAST) and press 'Enter'.  (Default: FAST) : ");
			text = keyboard.nextLine();
			keyboard = null;
			if (text.indexOf('s') == 0){
				return true;
			}else if(text.indexOf('f') == 0){
				return false;
			}else if (text.length() == 0){
				return false;
			}else{
				System.out.println("Wrong type. Closing...");
				System.exit(0); text = null; return false;
			}
			
	}
}