/*
 * 		[README] TURING MACHINE - turing.addons.Rule
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
 */
package turing.addons;
public class Rule {
    public int q = 1;
    public char e = 'h';
    public int p = 1;
    public char f = 'h';
    public int m = 1;
    public Rule(int v, char w, int x, char y, int z) {
        q = v;
        e = w;
        p = x;
        f = y;
        m = z;
    }
    public Rule(){
        q = 0;
        e = '0';
        p = 0;
        f = '0';
        m = 0;
    }
    public void print(){
    	System.out.print(q+" "+e+" "+p+" "+f+" "+m);
    }
    public void println(){
    	System.out.println(q+" "+e+" "+p+" "+f+" "+m);
    }
}
