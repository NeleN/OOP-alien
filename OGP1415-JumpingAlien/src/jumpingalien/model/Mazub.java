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
 * @version 11/03
 * 
 * @assumptions		we calculate everything in pixel, pixel/s, pixel/s²
 *
 */
public class Mazub {


	/**
	 * Initializes Mazub at a given position and its sprites.
	 * 
	 * @param 	positionX
	 * 			The horizontal position of the alien Mazub.
	 * @param 	positionY
	 * 			The vertical position of the alien Mazub.
	 * @param 	sprites
	 * 			An array of images of the type Sprite.
	 * @pre		The current position of the alien should be in the given field.
	 * 			|  isValidPosition(positionX,positionY)
	 * @pre		sprites should contain at least 10 images.
	 * 			| sprites.length >= 10
	 * @post	Mazub's new horizontal position equals positionX.
	 * 			| new.getPositionX() == positionX
	 * @post	Mazub's new vertical position equals positionY.
	 * 			| new.getPositionY() == positionY
	 * @post	Mazub's images equal the given sprites.
	 * 			| new.images == sprites
	 * @post	The parameter m is used to define the number of possible images for cases 8 and 9.
	 * 			| m == (images.length-10)/2
	 */
	public Mazub (int positionX, int positionY, Sprite[] sprites) {
		setPositionX(positionX);
		setPositionY(positionY);
		this.images = sprites;
		m = (images.length-10)/2;
	}
	
	/**
	 * Returns an image corresponding to the current index.
	 * 
	 * @param 	index
	 * 			An integer number that represents the current state of Mazub.
	 * @pre		The index should be positive and may not exceed the length of the array images.
	 * 			| (index >= 0) && (index < images.length)
	 */
	public Sprite getImageAtIndex(int index){
		assert (index >= 0) && (index < images.length);
		return this.images[index];
	}
	
	/**
	 * Returns an array of sprites called images.
	 */
	public Sprite[] getImages(){
		return this.images;
	}
	
	
	/**
	 * @param dt
	 * @post 	The new horizontal position of Mazub achieved in a period of time dt
	 * 			| new.positionX = this.positionX + formulePositionX(dt)
	 * @post	The new vertical position of Mazub achieved in a period of time dt
	 * 			| new.positionY = this.positionY + formulePositionY(dt)
	 * @post	The new horizontal speed of Mazub achieved in a period of time dt
	 * 			| new.speedX = this.speedX + getAccelerationX()*dt
	 * @post	The new vertical speed of Mazub achieved in a period of time dt
	 * 			| new.speedY = this.speedY + getAccelerationY()*dt
	 * @throws	if mazub is on the ground, speed equals zero
	 * @throws	isValidTime(double dt), valid argument
	 * @throws	are multipliable, are addable
	 * VRAAG: moet throwable enkel met gegeven input of gaat dit tot over andere methodes?
	 */
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
		heightMazub =  getCurrentSprite().getHeight();
	}
	
	/**
	 * Changes index of the sprite for the cases 8+n and 9+n+m.
	 * 
	 * @post	n should be smaller than or equal to m
	 * 			| n<=m
	 * @post	changedIndex must be set to current time
	 * 			| new.changedIndex = System.currentTimeMillis()
	 */
	public void updateIndex(){
		if (n<m)
			n+=1;
		else
			n=0;
		changedIndex = System.currentTimeMillis();
	}
	
	/**
	 * Checks if dt is a valid time span.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	True if and only if the given dt is positive and smaller than 0.2
	 * 			| (dt>=0) && (dt<0.2)
	 */
	public boolean isValidTime(double dt){
		return (dt>=0) && (dt<0.2);	// dt in seconds
	}
		
	/**
	 * This method calculates the horizontal travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	the horizontal distance travelled during a period of time dt.
	 * 			| new.travelledDistanceX
	 * @post	the new travelled distance is calculated given the speed and acceleration during a period of time dt.
	 * 			| new.travelledDistanceX = getSpeedX()*dt  + (1/2)*getAccelerationX()*Math.pow(dt, 2)
	 */
	public int formulePositionX(double dt){
		 travelledDistanceX = (int) (getSpeedX()*dt  + (1/2)*getAccelerationX()*Math.pow(dt, 2));
		 return travelledDistanceX;
	}
	
	/**
	 * This method calculates the vertical travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	the vertical distance travelled during a period of time dt.
	 * 			| new.travelledDistanceY
	 * @post	the new travelled distance is calculated given the speed and acceleration during a period of time dt.
	 * 			| new.travelledDistanceY = getSpeedX()*dt  + (1/2)*getAccelerationY()*Math.pow(dt, 2)
	 */
	public int formulePositionY(double dt){
		 travelledDistanceY = (int) (getSpeedY()*dt  + (1/2)*getAccelerationY()*Math.pow(dt, 2));
		 return travelledDistanceY;
	}	
	
	/**
	 * This method initiates a movement of the alien Mazub.
	 * 
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
	 * @post	The last direction the alien has moved to is direction
	 * 			| new.lastDirection == direction
	 * @post	changedIndex is set to the current time
	 * 			| new.changedIndex == System.currentTimeMillis()
	 * @post	n is zero
	 * 			| n == 0
	 */
	public void startMove(Direction direction){
		setSpeedX(100*direction.getDirection());
		setAccelerationX(90*direction.getDirection());
		this.isMovingX = true;
		this.lastDirection = direction.getDirection();
		changedIndex = System.currentTimeMillis();
		n=10;
	}
	
	/**
	 * This method terminates a movement of the alien Mazub.
	 * 
	 * @post	The horizontal speed of the alien is zero
	 * 			| new.getSpeedX() == 0
	 * @post	The horizontal acceleration of the alien is zero
	 * 			new.getAccelerationX() == 0
	 * @post	The alien isn't moving
	 * 			| new.isMovingX == false
	 * @post	timeLastMovedX is set to the current time
	 * 			| new.timeLastMovedX == System.currentTimeMillis()
	 */
	public void endMove(){
		setSpeedX(0);
		setAccelerationX(0);
		timeLastMovedX = System.currentTimeMillis();
		this.isMovingX = false;
	}
	
	/**
	 * This method initiates a jump of the alien Mazub.
	 * 
	 * @post	the boolean isJumping is true.
	 * 			| new.isJumping = true
	 * @post	the vertical speed of Mazub equals 800 pixel/s.
	 * 			| new.getSpeedY() = 800
	 * @throws	???
	 * 
	 */
	public void startJump(){
		setSpeedY(800);				//pixel/s
		this.isJumping = true;
	}
	
	/**
	 * This method terminates a jump of the alien Mazub.
	 * 
	 * @post	the boolean isJumping is false.
	 * 			| new.isJumping = false
	 * @post	the vertical acceleration of Mazub equals gravity.
	 * 			| new.getAccelerationY() = gravity
	 * @throws 	???
	 * 
	 */
	public void endJump(){
		if (getSpeedY() > 0)
			setSpeedY(0);
		setAccelerationY(gravity);
		this.isJumping = false;
	}
	
	/**
	 * This method initiates the alien Mazub's duck.
	 * 
	 * @post	the maximum value of the horizontal speed equals 100 pixel/s.
	 * 			| maxSpeedX = 100
	 * @post	the boolean isDucking is true.
	 * 			| new.isDucking = true 
	 * 
	 */
	public void startDuck(){			//DEFENSIEF
		maxSpeedX = 100;
		this.isDucking = true;
	}
	
	/**
	 * This method end the alien Mazub's duck.
	 * 
	 * @post	the maximum value of the horizontal speed equals 300 pixel/s.
	 * 			| maxSpeedX = 300
	 * @post	the boolean isDucking is false.
	 * 			| new.isDucking = false 
	 * 
	 */
	public void endDuck(){
		maxSpeedX = 300;
		this.isDucking = false;
	}
	
	/**
	 * Checks if the alien Mazub is ducking.
	 * 
	 * @return	true if and only if Mazub is ducking.
	 * 			| this.isDucking
	 */
	public boolean isDucking(){
		return this.isDucking;
	}

	/**
	 * Checks if the alien Mazub is moving horizontally.
	 * 
	 * @return	true if and only if Mazub is moving horizontally.
	 * 			| this.isMovingX
	 */
	public boolean isMovingX(){
		return this.isMovingX;
	}

	/**
	 * Checks if the alien Mazub is jumping.
	 * 
	 * @return	true if and only if Mazub is jumping.
	 * 			| this.isJumping
	 */
	public boolean isJumping(){
		return this.isJumping;
	}
	
	
	/**
	 * ijhfipui /////////////////////////////////// WE GAAN DEES DEFENSIEF DOEN
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public boolean isValidPosition(int positionX, int positionY){
		return (positionX>=0 && positionX<=1024-1) && (positionY>=0 && positionY<=768-1);
	}
	
	/**
	 * Checks whether a given speed is valid or not.
	 * 
	 * @param 	speed
	 * 			The horizontal speed of the alien
	 * @return	True if and only if speed is smaller than or equal to the maximum horizontal speed.
	 * 			| Math.abs(speed) <= this.maxSpeedX
	 */
	public boolean isValidSpeedX(double speed){
		return Math.abs(speed) <= this.maxSpeedX;
	}
	
	
	/**
	 * Returns the horizontal position of the alien Mazub.
	 */
	public int getPositionX(){
		return this.positionX;
	}
	
	/**
	 * Returns the vertical position of the alien Mazub.
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Changes the horizontal position of the alien Mazub.
	 * 
	 * @param 	position
	 * 			The position Mazub should be set to.
	 * @pre		position must be a valid position.
	 * 			| isValidPosition(position,0)
	 * @post	The horizontal position of mazub is position.
	 * 			| new.positionX == position
	 */
	public void setPositionX(int position){
		if (isValidPosition(position,0))
			this.positionX = position;
	}
	
	/**
	 * Changes the vertical position of the alien Mazub.
	 * 
	 * @param 	position
	 * 			The position Mazub should be set to.
	 * @pre		position must be a valid vertical position.
	 * 			| isValidPosition(0,position)
	 * @post	The vertical position of mazub is position.
	 * 			| new.positionY == position
	 */
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
	 */
	public double getSpeedX(){
		return this.speedX;
	}
	
	/**
	 * Return the current speed in vertical direction of the alien.
	 */
	public double getSpeedY(){
		return this.speedY;
	}
	
	/**
	 * Return the current acceleration in horizontal direction of the alien.
	 */
	public double getAccelerationX(){
		return this.accelerationX;
	}
	
	/**
	 * Return the current acceleration in vertical direction of the alien.
	 */
	public double getAccelerationY(){
		return this.accelerationY;
	}
	
	/**
	 * Sets the horizontal speed of the alien to a given speed
	 * 
	 * @param 	speed
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
	 * @param 	speed
	 * @post	The new vertical speed of the alien must be equal to the given speed
	 * 			| new.getSpeedY() == speed
	 */
	public void setSpeedY(double speed){
		this.speedY = speed;
	}
	
	/**
	 * Sets the horizontal acceleration of the alien to a given acceleration
	 * 
	 * @param 	acc
	 * @post	The new horizontal acceleration of the alien must be equal to the given acceleration
	 * 			| new.getAccelerationX() = acc
	 */
	public void setAccelerationX(double acc){
		this.accelerationX = acc;
	}
	
	/**
	 * Sets the vertical acceleration of the alien to a given acceleration
	 * 
	 * @param 	acc
	 * @post	The new vertical acceleration of the alien must be equal to the given acceleration
	 * 			| new.getAccelerationY() == acc
	 */
	public void setAccelerationY(double acc){
		this.accelerationY = acc; 
	}
	
	/**
	 * Checks whether Mazub has moved horizontally within the last second.
	 * 
	 * @return	True if and only if Mazub has moved whithin the last second.
	 * 			| (System.currentTimeMillis() - timeLastMovedX < 1000)
	 */
	public boolean hasMovedX(){
		return (System.currentTimeMillis() - timeLastMovedX < 1000);
	}
	
	/**
	 * Returns a sprite corresponding to the defined state of Mazub.
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
	

	
 
	private int gravity = -1000;					// -10m/s --> pixel = 0,01m --> 1000 pixels/s²
	private double maxSpeedX = 300;					//	3 m/s --> pixel = 0,01m --> 300 pixel/s 
	private double speedX = 0;
	private double speedY = 0;
	private double accelerationX = 0;
	private double accelerationY = 0;
	private int heightMazub;
	private int widthMazub;
	private int positionX = 0;
	private int positionY = 0;
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
