/*
 * 		[README] TURING MACHINE - turing.gridworld.Head
 * 
 * 		------------------------------------------------------------------------------------
 * 
 * 		Developed and in-line comented by Javier Provecho Fernandez.
 * 			1st Grade Student of Computer Science at University of Valladolid, Spain.
 * 		
 * 		Documented by Alejandro Garcia Gutierrez.
 * 			1st Grade Student of Computer Science at University of Valladolid, Spain.
 * 
 * 		Documentation avalaible at:
 * 			http://github.com/javierprovecho/Turing-Machine/blob/dev/include/Documentacion.docx
 * 
 * 		Repository located at:
 * 			http://www.github.com/javierprovecho/Turing-Machine/
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
 * 		Imports:
 * 
 * 		java.util.ArrayList					as array constructor
 * 		turing.addons.Rule					as custom object for rule data
 * 		info.gridworld.actor.Bug			GridWorld related
 * 		info.gridworld.grid.Location		GridWorld related
 *
 *		------------------------------------------------------------------------------------
 */
package turing.gridworld;
import java.util.ArrayList;
import turing.addons.Rule;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
public class Head extends Bug {    
    public static ArrayList<Rule> ruleList;
    public static boolean halted = false;
    public static int turingMachineStatus = 1;
    public Head(ArrayList<Rule> ruleListParameter) {
    	ruleList = ruleListParameter;
    	ruleListParameter = null;
	}
    public void moveLeft(){
    	setDirection(getDirection()-90);
    	moveTo(getLocation().getAdjacentLocation(getDirection()));
    	setDirection(getDirection()+90);
    }
    public void moveRight(){
    	setDirection(getDirection()+90);
    	moveTo(getLocation().getAdjacentLocation(getDirection()));
    	setDirection(getDirection()-90);
    }
    public void act() {

    	/*
    	 * Try to act
    	 */
 
	    	try{
		    		
		    	/*
		    	 * Exception if GRID = NULL
		    	 */
		    	
		    		if (getGrid() == null) return;
		        
		    	/*
		    	 * Create initial variables
		    	 */
		    		
		    		char headSymbol = 'h';
		    		Location headFacing = new Location(0, this.getLocation().getCol());
		        
		    	/*
		    	 * Read head symbol
		    	 */
		    		
			    	if (getGrid().get(headFacing) instanceof Symbol_0){
			        	headSymbol = '0';
			        } else if (getGrid().get(headFacing) instanceof Symbol_1){
			        	headSymbol = '1';
			        } else if (getGrid().get(headFacing) instanceof Symbol_a){
			        	headSymbol = 'a';
			        } else if (getGrid().get(headFacing) instanceof Symbol_b){
			        	headSymbol = 'b';
			        } else if (getGrid().get(headFacing) instanceof Symbol_h){
			        	headSymbol = 'h';
			        } else if (getGrid().get(headFacing) instanceof Symbol_x){
			        	headSymbol = 'x';
			        } else if (getGrid().get(headFacing) instanceof Symbol_y){
			        	headSymbol = 'y';
			        }else if (getGrid().get(headFacing) == null){
			        	headSymbol = 'h';
			        }
			    	
			    /*
			     * Search rule
			     */
			    	
			        int index = -1;
					for (int i = 0; i < ruleList.size(); i++){
						if (ruleList.get(i).e == headSymbol && ruleList.get(i).q == turingMachineStatus) {
							index = i;
							break;
						}
					}
			    	
				/*
				 * Exception if rule not found
				 */
					
					if (index == -1){
						throw new Exception();
					}
		
				/*
				 * Put new symbol to GRID
				 */
					
			        if (ruleList.get(index).f == '0'){
			        	new Symbol_0().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == '1'){
			        	new Symbol_1().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == 'a'){
			        	new Symbol_a().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == 'b'){
			        	new Symbol_b().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == 'h'){
			        	new Symbol_h().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == 'x'){
			        	new Symbol_x().putSelfInGrid(getGrid(), headFacing);
			        } else if (ruleList.get(index).f == 'y'){
			        	new Symbol_y().putSelfInGrid(getGrid(), headFacing);
			        }
			        
				/*
				 * Write new state
				 */
			        
			        turingMachineStatus = ruleList.get(index).p;
			        
			    /*
			     * Move head
			     */
			        
					if (ruleList.get(index).m == 1){
						moveRight();
					}else if(ruleList.get(index).m == -1){
						moveLeft();
					}
				
	    	}catch(Exception e){System.out.println("Halted!"+e);}
	
    }
}