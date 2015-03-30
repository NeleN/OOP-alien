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
public class Shark extends Creature {
	
	@Raw
	public Shark (int positionX, int positionY, Sprite[] sprites, double maxSpeed, int hitpoints) {
		super (positionX, positionY, sprites, 400, 100);
	}
	
	public void startMove(Direction direction){
		super.startMove(direction, 100, 150);
	}
	
	public void startJump(){
		super.startJump(200);
	}

}
