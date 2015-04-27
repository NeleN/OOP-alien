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
	

	@Raw
	public Plant (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 50, 1);
		if (Math.random()>0.5)
			startMove(Direction.RIGHT);
		else 
			startMove(Direction.LEFT);
	}
	
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
	
	private double timeLastSwitch=0 ;
	
	void startMove(Direction direction){
		super.startMove(direction, 50, 0);
	}

	void startJump(){
		super.startJump(0);
	}
	
	public Sprite getCurrentSprite() {
		if ( this.getSpeedX() > 1 ){
			return this.getImageAtIndex(1);
		}
		else {
			return this.getImageAtIndex(0);
		}
	}
}
