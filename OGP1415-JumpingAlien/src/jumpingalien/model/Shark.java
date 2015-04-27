/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele Nauwelaers and Melanie Nijs
 *
 */
public class Shark extends Creature {
	
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
	public Shark (int positionX, int positionY, Sprite[] sprites) {
		super (positionX, positionY, sprites, 400, 100);
		if (Math.random()>0.5)
			startMove(Direction.RIGHT);
		else 
			startMove(Direction.LEFT);
	}
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#advanceTime(double)
	 */
	@Override
	void advanceTime(double dt) throws IllegalDeltaTimeException {
		super.advanceTime(dt);
		if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY + getHeightSprite()) == 2)
				&& timeInAir > 0){
			this.setSpeedY(0);
			this.setAccelerationY(0);
		}
		randomMovement(dt, 100, 150, 1, 4);
		if (timeInAir > 0.2){
			loseHitpoints(6);
			timeInAir-=0.2;
		}
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
		
		if (world.getGeologicalFeature((int)this.getPositionX(), (int)this.getPositionY()) == 0){
			timeInAir += dt;
		}
	}
	
	/**
	 * @param 	direction
	 * 			the direction of the creature
	 * @effect	...
	 * 			super.startMove(direction, 100, 150);
	 */
	void startMove(Direction direction){
		super.startMove(direction, 100, 150);
	}
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#randomMovement(double, int, int, int, int)
	 * 
	 * @effect	...
	 * 			| super.randomMovement(dt, speed, acceleration, minDuration, maxDuration);
	 * @post	...
	 * 			| if (lastJump >= randomCounter){
	 * 				lastJump ==0 }
	 * @effect	...
	 * 			| if (lastJump >= randomCounter){
	 * 				startJump()}
	 * @effect	...
	 * 			| if((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY + getHeightSprite()) == 2)){
	 * 				this.setAccelerationY((double)(Math.random() * 40) - 20 )}
	 */
	@Override
	void randomMovement(double dt, int speed, int acceleration, int minDuration, int maxDuration){
		super.randomMovement(dt, speed, acceleration, minDuration, maxDuration);
		double randomCounter = ((double)(Math.random() * 10) + 5 );
		if (lastJump >= randomCounter){
			startJump();
			lastJump = 0;
		}
		if((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY + getHeightSprite()) == 2)){
			this.setAccelerationY((double)(Math.random() * 40) - 20 );
		}
	}
	
	/**
	 * @effect	...
	 * 			if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 1)
				|| (world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 2)){
	 * 				super.startJump(200)}
	 */
	void startJump(){
		if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 1)
				|| (world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 2)){
			super.startJump(200);
		}	
	}
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#getCurrentSprite()
	 * 
	 * Returns the sprite corresponding to the current movement of the slime. This will be a slime facing the left if the slime is currently
	 * moving to the left and a slime facing the right if the slime is currently moving to the right.
	 */
	public Sprite getCurrentSprite() {
		if ( this.lastDirection == 1 )
			return this.getImageAtIndex(1);
		else
			return this.getImageAtIndex(0);
	}

}
