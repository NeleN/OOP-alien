/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.*;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele Nauwelaers and Melanie Nijs
 *
 */
public class Plant extends Creature {
	

	/**
	 * Constructor of a plant
	 * 
	 * @effect	...
	 * 			| super.advancetime(dt)
	 * @effect	...
	 * 			| if (Math.random()>0.5)
	 *				startMove(Direction.RIGHT);
	 * @effect	...
	 * 			| if (Math.random()<=0.5)
	 *				startMove(Direction.RIGHT);
	 */
	@Raw
	public Plant (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 50, 1);
		if (Math.random()>0.5)
			startMove(Direction.RIGHT);
		else 
			startMove(Direction.LEFT);
	}
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#advanceTime(double)
	 */
	@Override
	void advanceTime(double dt) throws IllegalDeltaTimeException{
		super.advanceTime(dt);
		for (Mazub alien: world.getMazubsInWorld()){
			if (this.collisionDetection(alien)){
				collisionMazubPlant(alien, this);
			}
		}
		timeLastSwitch += dt;
		if (timeLastSwitch > 0.5){
			this.setSpeedX(-this.getSpeedX());	
			timeLastSwitch = 0;
		}
	}
	
	/**
	 * A double showing how long it's ago the plant has changed direction
	 */
	private double timeLastSwitch=0 ;
	
	/**
	 * @param 	direction
	 * 			the direction of the creature
	 * @effect	...
	 * 			super.startMove(direction, 50, 0);
	 */
	void startMove(Direction direction){
		super.startMove(direction, 50, 0);
	}
	
	/**
	 * @effect	...
	 * 			super.startJump(0)
	 */
	void startJump(){
		super.startJump(0);
	}
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#getCurrentSprite()
	 * 
	 * Returns the sprite corresponding to the current movement of the slime. This will be a slime facing the left if the slime is currently
	 * moving to the left and a slime facing the right if the slime is currently moving to the right.
	 */
	public Sprite getCurrentSprite() {
		if ( this.getSpeedX() > 1 ){
			return this.getImageAtIndex(1);
		}
		else {
			return this.getImageAtIndex(0);
		}
	}
}
