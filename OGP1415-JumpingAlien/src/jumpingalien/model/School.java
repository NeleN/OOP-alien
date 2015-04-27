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
	/**
	 * Constructor of a school
	 */
	public School (){
		
	}
	
	/**
	 * @param slime
	 * 			a slime to be added to the school
	 * @post	...
	 * 			| new.slimesInSchool == slimesInSchool.add(slime)
	 */
	void addSlime(Slime slime){
		slimesInSchool.add(slime);
	}
	
	/**
	 * @param slime
	 * 			a slime to be removed from the school
	 * @post	...
	 * 			| new.slimesInSchool == slimesInSchool.remove(slime)
	 */
	void removeSlime(Slime slime){
		slimesInSchool.remove(slime);
	}
	
	/**
	 * @post	...
	 * 			for (Slime slime: slimesInSchool){
	 * 				slime.new.hitpoints == slime.hitpoints-1
	 * 			}
	 */
	void losePoints(){
		for (Slime slime: slimesInSchool){
			slime.hitpoints -= 1;
		}
	}
	
	/**
	 * @post	...
	 * 			for (Slime slime: slimesInSchool){
	 * 				slime.new.hitpoints == slime.hitpoints+1
	 * 			}
	 */
	void gainPoints(){
		for (Slime slime: slimesInSchool){
			slime.gainHitpoints(1);
		}
	}
	
	/**
	 * @param slime
	 * 			A slime that gives a point to each member of the school
	 * @effect	...
	 * 			| slime.loseHitpoints(this.getNbSlimesInSchool())
	 * @effect	...
	 * 			| this.gaintPoints()
	 */
	void pointsSlimeToSchool(Slime slime){
		slime.loseHitpoints(this.getNbSlimesInSchool());
		this.gainPoints();
	}
	
	/**
	 * @param slime
	 * 			A slime that gets a hitpoint from each of the members of the school
	 * @effect	...
	 * 			| slime.gainHitpoints(this.getNbSlimesInSchool());
	 * @effect	...
	 * 			| this.losePoints
	 */
	void pointsSchoolToSlime(Slime slime){
		slime.gainHitpoints(this.getNbSlimesInSchool());
		this.losePoints();
	}
	
	/**
	 * @return
	 * 		The number of slimes in the school
	 */
	int getNbSlimesInSchool(){
		return slimesInSchool.size();
	}
	
	/**
	 * @return
	 * 		A list with all the slimes in the school
	 */
	public List<Slime> getSlimesInSchool(){
		return slimesInSchool;
	}

	/**
	 * A list of slimes that are in the school
	 */
	List<Slime> slimesInSchool = new ArrayList<Slime>();
	
}
