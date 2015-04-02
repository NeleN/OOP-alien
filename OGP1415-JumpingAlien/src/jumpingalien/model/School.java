/**
 * 
 */
package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nele
 *
 */
public class School {
	public School (){
		
	}
	
	public void losePoints(){
		for (Slime slime: slimesInSchool){
			slime.loseHitpoints(1);
		}
	}

	public List<Slime> slimesInSchool = new ArrayList<Slime>();
	
}
