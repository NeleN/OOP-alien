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
	public Slime (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 250, 100);
	}
	
	public void startMove(Direction direction){
		super.startMove(direction, 100, 70);
	}
	
	public void startJump(){
		super.startJump(0);
	}

}
