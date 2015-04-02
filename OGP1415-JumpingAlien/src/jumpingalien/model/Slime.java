/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele
 *
 */
public class Slime extends Creature {

	/**
	 * 
	 */
	@Raw
	public Slime (int positionX, int positionY, Sprite[] sprites, School school) {
		super (positionX, positionY, sprites, 250, 100);
		belongsToSchool = school;
	}
	
	public void startMove(Direction direction){
		super.startMove(direction, 100, 70);
	}
	
	public void startJump(){
		super.startJump(0);
	}
	
	private void changeSchool(School school){
		this.belongsToSchool = school;
	}
	
	public School getSchool(){
		return this.belongsToSchool;
	}
	
	public Sprite getCurrentSprite() {
		return this.getImageAtIndex(0);
	}
	
	private School belongsToSchool;

}
