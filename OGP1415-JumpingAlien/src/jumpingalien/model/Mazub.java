package jumpingalien.model;


import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.Sprite;



/**
 * 
 */

/**
 * Class containing the movements and characteristics of the alien Mazub.
 * 
 * @invar	The speed of the alien may not exceed the max. speed.
 * 			| isValidSpeedX(getSpeedX())
 * @invar	The alien Mazub should be positioned inside the given field.
 * 			| isValidPosition(getPositionX(),getPositionY())
 * 
 * @author Melanie Nijs and Nele Nauwelaers
 * @version 1.7
 * 
 * @assumptions		we calculate everything in pixel, pixel/s, pixel/s²
 *
 */
public class Mazub extends Creature{

	@Raw
	public Mazub (int positionX, int positionY, Sprite[] sprites, double maxSpeedX, int hitpoints) {
		super (positionX, positionY, sprites, 300,100);
		this.setAccelerationY(gravity);
	}
	
	@Override
	void advanceTime(double dt) throws IllegalDeltaTimeException {
		super.advanceTime(dt);
		if (tryEndDuck){
			endDuck();
		}
		this.lastCollisionEnemy+=dt;
		if (timeInWater > 0.2){
			loseHitpoints(2);
			timeInWater-=0.2;
		}
		if (timeInMagma > 0.2){
			loseHitpoints(50);
			timeInMagma-=0.2;
		}
		if (! this.isImmune()){
			for (Slime slime: world.getSlimesInWorld()){
				if (this.collisionDetection( slime)){
					collisionMazubSlime(this, slime);
				}
			}
			for (Shark shark: world.getSharksInWorld()){
				if (this.collisionDetection( shark)){
					collisionMazubShark(this, shark);
				}
			}
		}
		for (Plant plant: world.getPlantsInWorld()){
			if (this.collisionDetection( plant)){
				collisionMazubPlant(this, plant);
			}
		}
		for (int i= 0; i < (world.getFeatureTiles(2).length); i+=1){
			if (collisionDetectionTile(world.getFeatureTiles(2)[i][0], world.getFeatureTiles(2)[i][1])){
				timeInWater += dt;
				i = world.getFeatureTiles(2).length;
			}
			else {
				if (i == world.getFeatureTiles(2).length-1){
				timeInWater = 0;
				}
			}
		}
		for (int i= 0; i < (world.getFeatureTiles(3).length); i+=1){
			if (collisionDetectionTile(world.getFeatureTiles(3)[i][0], world.getFeatureTiles(3)[i][1])){
				timeInMagma += dt;
				i = world.getFeatureTiles(3).length;
			}
			else {
				if (i == world.getFeatureTiles(3).length-1){
				timeInMagma = 0;
				}
			}
		}
	}
	
	@Override
	void gainHitpoints(int points){
		if (this.getHitpoints() + points < 500){
			super.gainHitpoints(points);
		}
		else {
			this.hitpoints = 500;
		}
	}

	public void startMove(Direction direction){
		super.startMove(direction, 100, 90);
	}
	
	public void startJump(){
		if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 1)
				|| (world.getGeologicalFeature((int)this.getPositionX()-2 + this.getWidthSprite(), (int)newPositionY) == 1)){
			super.startJump(800);
		}
	}
	
	/**
	 * This method initiates the alien Mazubs duck.
	 * 
	 * @post	the maximum value of the horizontal speed equals 100 pixel/s.
	 * 			| maxSpeedX = 100
	 * @post	the boolean isDucking is true.
	 * 			| new.isDucking = true 
	 */
	public void startDuck(){			
		maxSpeedX = 100;
		this.isDucking = true;
	}
	
	/**
	 * This method end the alien Mazubs duck.
	 * 
	 * @post	the maximum value of the horizontal speed equals 300 pixel/s.
	 * 			| maxSpeedX = 300
	 * @post	the boolean isDucking is false.
	 * 			| new.isDucking = false 
	 */
	public void endDuck(){
		if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY + getHeightSprite()+3) == 1)
				|| (world.getGeologicalFeature((int)this.getPositionX() + this.getWidthSprite() - 2, (int)newPositionY + getHeightSprite()+3) == 1)){
			tryEndDuck = true;
			isDucking = true;
		}
		else{
			maxSpeedX = 300;
			this.isDucking = false;
			tryEndDuck = false;
		}
	}
	
	/**
	 * Checks if the alien Mazub is ducking.
	 * 
	 * @return	true if and only if the creature is ducking.
	 * 			| this.isDucking
	 */
	@Basic
	public boolean isDucking(){
		return this.isDucking;
	}
	
	public boolean isImmune(){
		if (lastCollisionEnemy <= 0.6) {
				return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A boolean which shows whether the creature is ducking or not.
	 */
	boolean isDucking = false;
	
	private boolean tryEndDuck = false;
	
	/**
	 * Returns a sprite corresponding to the defined state of the creature.UITGEBREIDER UITLEGGEN
	 */
	public Sprite getCurrentSprite() {
		if (! isMovingX() && ! hasMovedX() && ! isDucking())
			return this.getImageAtIndex(0);
		if (! isMovingX() && ! hasMovedX() && isDucking())
			return this.getImageAtIndex(1);
		if (! isMovingX() && hasMovedX() && this.lastDirection == 1 && ! isDucking())
			return this.getImageAtIndex(2);
		if (! isMovingX() && hasMovedX() && this.lastDirection == -1 && ! isDucking())
			return this.getImageAtIndex(3);
		if ( isMovingX() && this.lastDirection == 1 && isJumping() && ! isDucking())
			return this.getImageAtIndex(4);
		if ( isMovingX() && this.lastDirection == -1 && isJumping() && ! isDucking())
			return this.getImageAtIndex(5);
		if ( (isMovingX() || hasMovedX())  && this.lastDirection == 1 && isDucking())
			return this.getImageAtIndex(6);
		if ( (isMovingX() || hasMovedX())  && this.lastDirection == -1 && isDucking())
			return this.getImageAtIndex(7);
		if ( isMovingX() && this.lastDirection == 1 && ! isJumping() && ! isDucking())
			return this.getImageAtIndex(8+alternatingIndex);
		if ( isMovingX() && this.lastDirection == -1 && ! isJumping() && ! isDucking())
			return this.getImageAtIndex(9+m+alternatingIndex);
		else return this.getImageAtIndex(0);
		
	}


}
