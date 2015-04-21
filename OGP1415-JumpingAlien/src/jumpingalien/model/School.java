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
	
	void losePoints(){
		for (Slime slime: slimesInSchool){
			slime.loseHitpoints(1);
		}
	}
	
	void gainPoints(){
		for (Slime slime: slimesInSchool){
			slime.gainHitpoints(1);
		}
	}
	
	void pointsSlimeToSchool(Slime slime){
		slime.loseHitpoints(this.getNbSlimesInSchool());
		this.gainPoints();
	}
	
	void pointsSchoolToSlime(Slime slime){
		slime.gainHitpoints(this.getNbSlimesInSchool());
		this.losePoints();
	}
	
	int getNbSlimesInSchool(){
		return slimesInSchool.size();
	}

	List<Slime> slimesInSchool = new ArrayList<Slime>();
	
}
