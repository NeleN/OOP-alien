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
	}

	public void startMove(Direction direction){
		super.startMove(direction, 100, 90);
	}
	
	public void startJump(){
		super.startJump(800);
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
		maxSpeedX = 300;
		this.isDucking = false;
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
		if (lastCollisionEnemy <= 0.6)
				return true;
		return false;
	}
	
	/**
	 * A boolean which shows whether the creature is ducking or not.
	 */
	private boolean isDucking = false;

}
