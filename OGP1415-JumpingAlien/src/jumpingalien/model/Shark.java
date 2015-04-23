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
		if (Math.random()>0.5)
			startMove(Direction.RIGHT);
		else 
			startMove(Direction.LEFT);
	}
	
	@Override
	public void advanceTime(double dt) throws IllegalDeltaTimeException {
		super.advanceTime(dt);
		randomMovement(dt, 100, 150, 1, 4);
		for (Mazub alien: world.getMazubsInWorld()){
			if ( ! alien.isImmune()){
				if (this.collisionDetection(alien)){
					collisionMazubShark(alien, this);
				}
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
	
	@Override
	void randomMovement(double dt, int speed, int acceleration, int minDuration, int maxDuration){
		super.randomMovement(dt, speed, acceleration, minDuration, maxDuration);
		double randomCounter = ((double)(Math.random() * 10) + 5 );
		if (lastJump >= randomCounter){
			startJump();
			lastJump = 0;
		}
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
