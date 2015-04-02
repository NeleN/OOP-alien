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
public class Plant extends Creature {
	
	@Raw
	public Plant (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 50, 1);
	}

	public void startMove(Direction direction){
		super.startMove(direction, 50, 0);
	}
	
	public void startJump(){
		super.startJump(0);
	}
	
	public Sprite getCurrentSprite() {
		if ( this.lastDirection == 1 )
			return this.getImageAtIndex(1);
		else
			return this.getImageAtIndex(0);
	}
}
