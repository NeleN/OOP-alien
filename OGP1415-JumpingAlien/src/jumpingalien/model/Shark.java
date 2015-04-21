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
	public Shark (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 400, 100);
	}
	
	@Override
	public void advanceTime(double dt) throws IllegalDeltaTimeException {
		super.advanceTime(dt);
		for (Mazub alien: world.getMazubsInWorld()){
			if (this.collisionDetection(alien)){
				collisionMazubShark(alien, this);
			}
		}
		for (Slime slime: world.getSlimesInWorld()){
			if (this.collisionDetection(slime)){
				collisionSharkSlime(this, slime);
			}
		}
		for (Shark shark: world.getSharksInWorld()){
			if (this.collisionDetection(shark)){
				collisionSharkShark(this, shark);
			}
		}
	}
	
	public void startMove(Direction direction){
		super.startMove(direction, 100, 150);
	}
	
	public void startJump(){
		super.startJump(200);
	}
	
	public Sprite getCurrentSprite() {
		if ( this.lastDirection == 1 )
			return this.getImageAtIndex(1);
		else
			return this.getImageAtIndex(0);
	}

}
