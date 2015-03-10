package jumpingalien.model;


import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.Sprite;



/**
 * 
 */

/**
 * Class containing the movements and characteristics of the alien Mazub.
 * 
 * @invar	The speed of the alien may not exceed the max. speed
 * 			| isValidSpeedX(getSpeedX())
 * 
 * @author Melanie Nijs and Nele Nauwelaers
 * @version 2/03 : characteristics of Mazub (position and dimension)
 * 				   basic functions
 * @todo	2/03 :  documentation at home, direction left right implementation, 
 * 
 * @assumptions		we calculate everything in pixel, pixel/s, pixel/s²
 *
 */
public class Mazub {

	public Mazub (int pixelX, int pixelY, Sprite[] sprites) {
		setPositionX(pixelX);
		setPositionY(pixelY);
		this.images = sprites;
		m = (images.length-10)/2;
	}
	
	public Sprite getImageAtIndex(int index){
		return this.images[index];
	}
	
	public Sprite[] getImages(){
		return this.images;
	}
	
	
	public void advanceTime(double dt){							//DEFENSIEF
		setPositionX(getPositionX() + formulePositionX(dt));
		setPositionY(getPositionY() + formulePositionY(dt));
		setSpeedX(getSpeedX() + getAccelerationX()*dt); 
		setSpeedY(getSpeedY() + getAccelerationY()*dt);
		if (getPositionY() == 0)
			setSpeedY(0);
		if (System.currentTimeMillis() - changedIndex  >= 75)
			this.updateIndex();
		widthMazub = getCurrentSprite().getWidth();
		heightMazub = getCurrentSprite().getHeight();
	}
	
	public void updateIndex(){
		if (n<m)
			n+=1;
		else
			n=0;
		changedIndex = System.currentTimeMillis();
	}
	
	public boolean isValidTime(double dt){
		return (dt>=0) && (dt<0.2);	// dt in seconds
	}
		
	public int formulePositionX(double dt){
		 travelledDistanceX = (int) (getSpeedX()*dt  + (1/2)*getAccelerationX()*Math.pow(dt, 2));
		 return travelledDistanceX;
	}
	
	public int formulePositionY(double dt){
		 travelledDistanceY = (int) (getSpeedY()*dt  + (1/2)*getAccelerationY()*Math.pow(dt, 2));
		 return travelledDistanceY;
	}	
	
	/**
	 * @param direction
	 * @pre		direction has to be a valid direction
	 * 			| direction == (RIGHT || LEFT)
	 * @post	if the direction is left the horizontal speed and acceleration should be negative
	 * 			| if (direction.getDirection() == -1)
	 * 				(new.getSpeedX() < 0 || new.getAccelerationX() < 0)
	 * @post	if the direction is right the horizontal speed and acceleration should be positive
	 * 			| if (direction.getDirection() == 1)
	 * 				(new.getSpeedX() > 0 || new.getAccelerationX() > 0)
	 * @post	The absolute value of the horizontal speed should be 100
	 * 			| Math.abs(new.getSpeedX()) == 100
	 * @post	The absolute value of the horizontal acceleration should be 90
	 * 			| Math.abs(new.getAccelerationX()) == 90
	 * @post	The alien is moving
	 * 			new.isMovingX == true
	 * 
	 */
	public void startMove(Direction direction){
		setSpeedX(100*direction.getDirection());
		setAccelerationX(90*direction.getDirection());
		this.isMovingX = true;
		this.lastDirection = direction.getDirection();
		changedIndex = System.currentTimeMillis();
		n=0;
	}
	
	/**
	 * @post	The horizontal speed of the alien is zero
	 * 			| new.getSpeedX() == 0
	 * @post	The horizontal acceleration of the alien is zero
	 * 			new.getAccelerationX() == 0
	 * @post	The alien isn't moving
	 * 			| new.isMovingX == false
	 */
	public void endMove(){
		setSpeedX(0);
		setAccelerationX(0);
		timeLastMovedX = System.currentTimeMillis();
		this.isMovingX = false;
	}
	
	public void startJump(){
		setSpeedY(800);				//pixel/s
		this.isJumping = true;
	}
	
	public void endJump(){
		if (getSpeedY() > 0)
			setSpeedY(0);
		setAccelerationY(gravity);
		this.isJumping = false;
	}
	
//	public void fall(){			
//		if (getPositionY() > 0)
//			setAccelerationY(gravity);
//	}
//	
	public void startDuck(){			//DEFENSIEF
		maxSpeedX = 100;
		this.isDucking = true;
	}
	
	public void endDuck(){
		maxSpeedX = 300;
		this.isDucking = false;
	}
	
	public boolean isDucking(){
		return this.isDucking;
	}

	
	public boolean isMovingX(){
		return this.isMovingX;
	}

	
	public boolean isJumping(){
		return this.isJumping;
	}
	
	
	public boolean isValidPosition(int positionX, int positionY){
		return (positionX>=0 && positionX<=1024) && (positionY>=0 && positionY<=768);
	}
	
	public boolean isValidSpeedX(double speed){
		return Math.abs(speed) <= this.maxSpeedX;
	}
	
	
	public int getPositionX(){
		return this.positionX;
	}
	
	public int getPositionY() {
		return this.positionY;
	}
	
	public void setPositionX(int position){
		if (isValidPosition(position,0))
			this.positionX = position;
	}
	
	public void setPositionY(int position){
		if (isValidPosition(0,position))
			this.positionY = position;
		if (position < 0)
			this.positionY = 0;
	}
	
	/**
	 * Return the current height of the alien
	 * @return
	 */
	public int getHeightMazub(){
		return this.heightMazub;
	}
	
	/**
	 * Return the current width of the alien
	 * @return
	 */
	public int getWidthMazub(){
		return this.widthMazub;
	}
	
	/**
	 * Sets the height of the alien to a given height.
	 * 
	 * @param height
	 * @post	The new height of the alien should be equal to the given height
	 * 			| new.getHeightMazub() == height
	 * @throws	... 																	isValidHeight maken
	 */
	public void setHeightMazub(int height){
		this.heightMazub = height;
	}
	
	/**
	 * Sets the width of the alien to a given width.
	 * 
	 * @param width
	 * @post	The new width of the alien should be equal to the given width
	 * 			| new.getWidthMazub() == width
	 * @throws	...
	 */
	public void setWidthMazub(int width){
		this.widthMazub = width;
	}
	
	/**
	 * Return the current speed in horizontal direction of the alien.
	 * @return
	 */
	public double getSpeedX(){
		return this.speedX;
	}
	
	/**
	 * Return the current speed in vertical direction of the alien.
	 * @return
	 */
	public double getSpeedY(){
		return this.speedY;
	}
	
	/**
	 * Return the current acceleration in horizontal direction of the alien.
	 * @return
	 */
	public double getAccelerationX(){
		return this.accelerationX;
	}
	
	/**
	 * Return the current acceleration in vertical direction of the alien.
	 * @return
	 */
	public double getAccelerationY(){
		return this.accelerationY;
	}
	
	/**
	 * Sets the horizontal speed of the alien to a given speed
	 * 
	 * @param speed
	 * @pre		The given speed may not exceed the max. speed
	 * 			| isValidSpeedX(speed)
	 * @post	The new horizontal speed of the alien must be equal to the given speed
	 * 			new.getSpeedX() == speed
	 * 
	 */
	public void setSpeedX(double speed){
		assert isValidSpeedX(speed);
		this.speedX = speed;
	}
	
	/**
	 * Sets the vertical speed of the alien to a given speed
	 * 
	 * @param speed
	 * @post	The new vertical speed of the alien must be equal to the given speed
	 * 			| new.getSpeedY() == speed
	 */
	public void setSpeedY(double speed){
		this.speedY = speed;
	}
	
	/**
	 * Sets the horizontal acceleration of the alien to a given acceleration
	 * 
	 * @param acc
	 * @post	The new horizontal acceleration of the alien must be equal to the given acceleration
	 * 			| new.getAccelerationX() = acc
	 */
	public void setAccelerationX(double acc){
		this.accelerationX = acc;
	}
	
	/**
	 * Sets the vertical acceleration of the alien to a given acceleration
	 * 
	 * @param acc
	 * AANNAME: versnelling niet groter laten worden dan abs(10), indien ongeldige versnelling zet op standaar gravitatie-waarde.
	 * @post	The new vertical acceleration of the alien must be equal to the given acceleration
	 * 			| new.getAccelerationY() == acc
	 * TOTAAL (BIJ BV FALL GEBRUIK MAKEN VAN EFFECT??)
	 */
	public void setAccelerationY(double acc){
		if (Math.abs(acc) <= 10)
			this.accelerationY = acc; 
		else
			this.accelerationY = gravity;
	}
	
	/**
	 * if true Mazub has moved in the last second
	 * @return
	 */
	public boolean hasMovedX(){
		return (System.currentTimeMillis() - timeLastMovedX < 1000);
	}
	
	/**
	 * @return
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
			return this.getImageAtIndex(8+n);
		if ( isMovingX() && this.lastDirection == -1 && ! isJumping() && ! isDucking())
			return this.getImageAtIndex(9+m+n);
		else return this.getImageAtIndex(0);
		
	}
	
//	public Sprite alternatingImages(int m, int basicIndex) {
//		if (m<=0)
//			return this.getImageAtIndex(basicIndex);
//		else
//			changedIndex = System.currentTimeMillis();
//			return this.getImageAtIndex(basicIndex);
//			if 
//	}
	

	
 
	private int gravity = -1000;					// -10m/s --> pixel = 0,01m --> 1000 pixels/s²
	private double maxSpeedX = 300;					//	3 m/s --> pixel = 0,01m --> 300 pixel/s 
	private double speedX = 0;
	private double speedY = 0;
	private double accelerationX = 0;
	private double accelerationY = 0;
	private int heightMazub = 11;
	private int widthMazub = 6;
	private int positionX = 0;
	private int positionY = 0;
	private int pixelX = 0;
	private int pixelY = 0;
	private int travelledDistanceX = 0;
	private int travelledDistanceY = 0;
	private long timeLastMovedX = -1001;
	private boolean isDucking = false;
	private boolean isMovingX = false;
	private boolean isJumping = false;	
	private int lastDirection = 0;
	private int m;
	private int n;
	private long changedIndex;
	public Sprite[] images;
	
	
}
