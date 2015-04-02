/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele
 *
 */
public class Creature{
	
	
	/**
	 * Initializes the creature at a given position and its sprites.
	 * 
	 * @param 	positionX
	 * 			The horizontal position of  the creature.
	 * @param 	positionY
	 * 			The vertical position of  the creature.
	 * @param 	sprites
	 * 			An array of images of the type Sprite.
	 * @pre		sprites should contain at least 10 images.
	 * 			| sprites.length != null && sprites.length >= 10
	 * @effect	the creature's new horizontal position equals positionX.
	 * 			| setPositionX(positionX)
	 * @effect	the creature's new vertical position equals positionY.
	 * 			| setPositionY(positionY)
	 * @post	the creature's images equal the given sprites.
	 * 			| new.images == sprites
	 * @post	The parameter m is used to define the number of possible images for cases 8 and 9.
	 * 			| m == (images.length-10)/2
	 */
	@Raw
	public Creature (int positionX, int positionY, Sprite[] sprites, double maxSpeedX, int hitpoints) {
		try {
			setPositionX(positionX);
			setPositionY(positionY);
		} catch (IllegalArgumentException exc) {
			throw new jumpingalien.util.ModelException("Illegal position", exc);	
		}
		this.images = sprites;
		assert ((!(images == null)) && (images.length >= 10));
		m = (images.length-10)/2;
	}

	
	/**
	 * @param dt
	 * @effect 	The new horizontal position of the creature achieved in a period of time dt
	 * 			| setPositionX(this.positionX + formulePositionX(dt))
	 * @effect	The new vertical position of the creature achieved in a period of time dt
	 * 			| setPositionY(this.positionY + formulePositionY(dt))
	 * @effect	The new horizontal speed of the creature achieved in a period of time dt
	 * 			| setSpeedX(this.speedX + getAccelerationX()*dt)
	 * @effect	The new vertical speed of the creature achieved in a period of time dt
	 * 			| setSpeedY(this.speedY + getAccelerationY()*dt)
	 * @throws	IllegalDeltaTimeException
	 * 			The given period of time is an invalid input.
	 * 			| !(dt>=0) || !(dt<0.2)
	 */
	public void advanceTime(double dt) throws IllegalDeltaTimeException {
		if (! isValidTime(dt))
			throw new IllegalDeltaTimeException(dt);
		else
			try {
				setPositionX(getPositionX() + getTravelledDistanceX(dt));
				setPositionY(getPositionY() + getTravelledDistanceY(dt));
			} catch (IllegalArgumentException exc) {
				throw new jumpingalien.util.ModelException("Illegal position", exc);	
			}
			setSpeedX(getSpeedX() + getAccelerationX()*dt); 
			setSpeedY(getSpeedY() + getAccelerationY()*dt);
			if (getPositionY() == 0)
				setSpeedY(0);
			timeLastMovedX += dt;
			changedIndex += dt;
			if (changedIndex >= 0.075)
				this.updateIndex();
			widthSprite = getCurrentSprite().getWidth();
			heightSprite =  getCurrentSprite().getHeight();
	}
	
	/**
	 * Checks if dt is a valid time span.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	True if and only if the given dt is positive and smaller than 0.2
	 * 			| (dt>=0) && (dt<0.2)
	 */
	@Raw
	public boolean isValidTime(double dt){
		return (dt>=0) && (dt<0.2);	
	}
	
	/**
	 * Changes index of the sprite for the cases 8+n and 9+n+m.
	 * 
	 * @post	n should be smaller than or equal to m
	 * 			| n<=m
	 * @post	changedIndex must be set to current time
	 * 			| new.changedIndex = System.currentTimeMillis()
	 */
	private void updateIndex(){
		if (alternatingIndex<m)
			alternatingIndex+=1;
		else
			alternatingIndex=0;
		changedIndex = 0;
	}
	
	
	public int getHitpoints(){
		return this.hitpoints;
	}
	
	void gainHitpoints(int points){
		hitpoints+=points;
	}
	
	void loseHitpoints(int points){
		hitpoints-=points;
	}
	
		
	/**
	 * This method calculates the horizontal travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 */
	public double getTravelledDistanceX(double dt){
		 return (getSpeedX()*dt  + 0.5*getAccelerationX()*Math.pow(dt, 2));
	}
	
	
	/**
	 * This method calculates the vertical travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 */
	public double getTravelledDistanceY(double dt){
		 return (getSpeedY()*dt  + 0.5*getAccelerationY()*Math.pow(dt, 2));
	}	

	
	/**
	 * This method initiates a movement of  the creature.
	 * 
	 * @param 	direction
	 * 			the direction of the creature
	 * @pre		direction has to be a valid direction
	 * 			| direction == (RIGHT || LEFT)
	 * @post	if the direction is left the horizontal speed and acceleration should be negative
	 * 			| if (direction.getDirection() == -1)
	 * 				(new.getSpeedX() < 0 || new.getAccelerationX() < 0)
	 * @post	if the direction is right the horizontal speed and acceleration should be positive
	 * 			| if (direction.getDirection() == 1)
	 * 				(new.getSpeedX() > 0 || new.getAccelerationX() > 0)
	 * @post	The absolute value of the horizontal speed should be 100
	 * 			| Math.abs(new.getSpeedX()) == 100									EFFECT????????????????????????????
	 * @post	The absolute value of the horizontal acceleration should be 90
	 * 			| Math.abs(new.getAccelerationX()) == 90
	 * @post	 is moving
	 * 			new.isMovingX == true
	 * @post	The last direction  has moved to is direction
	 * 			| new.lastDirection == direction
	 * @post	changedIndex is set to the current time
	 * 			| new.changedIndex == System.currentTimeMillis()
	 * @post	alternatingIndex is zero
	 * 			| alternatingIndex == 0
	 */
	public void startMove(Direction direction, int speed, int acceleration){
		setSpeedX(speed*direction.getDirection());
		setAccelerationX(acceleration*direction.getDirection());
		this.isMovingX = true;
		this.lastDirection = direction.getDirection();
		changedIndex = 0;
		alternatingIndex = 0;
	}
	
	/**
	 * This method terminates a movement of  the creature.
	 * 
	 * @post	The horizontal speed of  is zero.
	 * 			| new.getSpeedX() == 0
	 * @post	The horizontal acceleration of  is zero.
	 * 			new.getAccelerationX() == 0
	 * @post	 isn't moving.
	 * 			| new.isMovingX == false
	 * @post	timeLastMovedX is set to the current time.
	 * 			| new.timeLastMovedX == System.currentTimeMillis()
	 */
	public void endMove(){
		setSpeedX(0);
		setAccelerationX(0);
		timeLastMovedX = 0;
		this.isMovingX = false;
	}
	
	/**
	 * This method initiates a jump of  the creature.
	 * 
	 * @post	the boolean isJumping is true.
	 * 			| new.isJumping = true
	 * @post	the vertical speed of the creature equals 800 pixel/s.
	 * 			| new.getSpeedY() = 800
	 */
	public void startJump(double speedY){
		setSpeedY(speedY);				
		this.isJumping = true;
	}
	
	/**
	 * This method terminates a jump of the creature.
	 * 
	 * @post	the boolean isJumping is false.
	 * 			| new.isJumping = false
	 * @post	the vertical acceleration of the creature equals gravity.
	 * 			| new.getAccelerationY() = gravity
	 * @post	the vertical speed equals zero or is negative.
	 * 			| new.getSpeedY() <= 0
	 */
	public void endJump(){
		if (getSpeedY() > 0)
			setSpeedY(0);
		setAccelerationY(gravity);
		this.isJumping = false;
	}
	


	/**
	 * Checks if  the creature is moving horizontally.
	 * 
	 * @return	true if and only if the creature is moving horizontally.
	 * 			| this.isMovingX
	 */
	@Basic
	public boolean isMovingX(){
		return this.isMovingX;
	}

	/**
	 * Checks if  the creature is jumping.
	 * 
	 * @return	true if and only if the creature is jumping.
	 * 			| this.isJumping
	 */
	@Basic
	public boolean isJumping(){
		return this.isJumping;
	}
	
	
	/**
	 * Checks whether the (x, y) position of  the creature is valid.
	 * @param 	positionX
	 * 			The horizontal position of the creature's bottom left.
	 * @param 	positionY
	 * 			The vertical position of the creature's bottom left.
	 * @return	true if and only if x is an element of [0, 1023] and y an element of [0, 767]
	 * 			| (positionX>=xMin && positionX<=xMax) && (positionY>=yMin && positionY<=yMax)
	 */
	@Raw
	public boolean isValidPosition(double positionX, double positionY){
		return (positionX>=getXMin() && positionX<=getXMax()) && (positionY>=getYMin() && positionY<=getYMax());
	}
	
	/**
	 * Checks whether a given speed is valid or not.
	 * 
	 * @param 	speed
	 * 			The horizontal speed of 
	 * @return	True if and only if speed is smaller than or equal to the maximum horizontal speed.
	 * 			| Math.abs(speed) <= this.maxSpeedX
	 */
	@Raw
	public boolean isValidSpeedX(double speed){
		return Math.abs(speed) <= this.maxSpeedX;
	}
	
	/**
	 * Checks whether the creature has moved horizontally within the last second.
	 * 
	 * @return	True if and only if the creature has moved within the last second.
	 * 			| (System.currentTimeMillis() - timeLastMovedX < 1000)
	 */
	@Basic
	public boolean hasMovedX(){
		return (timeLastMovedX < 1);
	}
	
	
	/**
	 * Returns the horizontal position of  the creature.
	 */
	@Basic
	public double getPositionX(){
		return this.positionX;
	}
	
	/**
	 * Returns the vertical position of  the creature.
	 */
	@Basic
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Changes the horizontal position of  the creature.
	 * 
	 * @param 	position
	 * 			The position the creature should be set to.
	 * @post	The horizontal position of the creature is position.
	 * 			| new.positionX == position
	 * @throws	IllegalArgumentException
	 * 			The given position is not allowed for the creature.
	 * 			(positionX>=xMin && positionX<=xMax)
	 * 
	 */
	private void setPositionX(double position) throws IllegalArgumentException {
		if (! isValidPosition(position,0))
			throw new IllegalArgumentException();
		else
			this.positionX = position;
	}
	
	/**
	 * Changes the vertical position of  the creature.
	 * 
	 * @param 	position
	 * 			The position the creature should be set to.
	 * @post	The vertical position of the creature is position.
	 * 			| new.positionY == position
	 * @ throws	IllegalArgumentException
	 * 			The given position is not allowed for the creature
	 * 			(positionY>=yMin && positionY<=yMax)
	 */
	private void setPositionY(double position) throws IllegalArgumentException {
		if (position < 0)
			position = 0;
		if ( ! isValidPosition(0,position))
			throw new IllegalArgumentException();
		else
			this.positionY = position;
	}
	
	/**
	 * Return the current height of 
	 * @return
	 */
	@Basic
	public int getHeightSprite(){
		return this.heightSprite;
	}
	
	/**
	 * Return the current width of 
	 * @return
	 */
	@Basic
	public int getWidthSprite(){
		return this.widthSprite;
	}
	
	/**
	 * Sets the height of  to a given height.
	 * 
	 * @param height
	 * @post	The new height of  should be equal to the given height
	 * 			| new.getHeightthe creature() == height
	 * @throws	... 																	isValidHeight maken
	 */
	public void setHeightSprite(int height){
		this.heightSprite = height;
	}
	
	/**
	 * Sets the width of  to a given width.
	 * 
	 * @param width
	 * @post	The new width of  should be equal to the given width
	 * 			| new.getWidththe creature() == width
	 * @throws	...
	 */
	public void setWidthSprite(int width){
		this.widthSprite= width;
	}
	
	/**
	 * Return the current speed in horizontal direction of .
	 */
	@Basic
	public double getSpeedX(){
		return this.speedX;
	}
	
	/**
	 * Return the current speed in vertical direction of .
	 */
	@Basic
	public double getSpeedY(){
		return this.speedY;
	}
	
	/**
	 * Return the current acceleration in horizontal direction of .
	 */
	@Basic
	public double getAccelerationX(){
		return this.accelerationX;
	}
	
	/**
	 * Return the current acceleration in vertical direction of .
	 */
	@Basic
	public double getAccelerationY(){
		return this.accelerationY;
	}
	
	/**
	 * Sets the horizontal speed of  to a given speed
	 * 
	 * @param 	speed
	 * @pre		The given speed may not exceed the max. speed
	 * 			| isValidSpeedX(speed)
	 * @post	The new horizontal speed of  must be equal to the given speed
	 * 			new.getSpeedX() == speed
	 * 
	 */
	private void setSpeedX(double speed){
		if (isValidSpeedX(speed))
			this.speedX = speed;
		else
			this.speedX = (this.lastDirection*maxSpeedX);
	}
	
	/**
	 * Sets the vertical speed of  to a given speed
	 * 
	 * @param 	speed
	 * @post	The new vertical speed of  must be equal to the given speed
	 * 			| new.getSpeedY() == speed
	 */
	private void setSpeedY(double speed){
		this.speedY = speed;
	}
	
	/**
	 * Sets the horizontal acceleration of  to a given acceleration
	 * 
	 * @param 	acc
	 * @post	The new horizontal acceleration of  must be equal to the given acceleration
	 * 			| new.getAccelerationX() = acc
	 */
	private void setAccelerationX(double acc){
		this.accelerationX = acc;
	}
	
	/**
	 * Sets the vertical acceleration of  to a given acceleration
	 * 
	 * @param 	acc
	 * @post	The new vertical acceleration of  must be equal to the given acceleration
	 * 			| new.getAccelerationY() == acc
	 */
	private void setAccelerationY(double acc){
		this.accelerationY = acc; 
	}
	
	public int[] getLocation(){
		int[] array = {(int)this.getPositionX(), (int)this.getPositionY()};
		return array;
	}	
		
	
	/**
	 * Returns an image corresponding to the current index.
	 * 
	 * @param 	index
	 * 			An integer number that represents the current state of the creature.
	 * @pre		The index should be positive and may not exceed the length of the array images.
	 * 			| (index >= 0) && (index < images.length)
	 */
	@Basic
	public Sprite getImageAtIndex(int index){
		assert (index >= 0) && (index < images.length);
		return this.images[index];
	}
	
	/**
	 * Returns an array of sprites called images.
	 */
	@Basic @Immutable
	public Sprite[] getImages(){
		return this.images;
	}
	
	public abstract Sprite getCurrentSprite();
	
	
 
	/**
	 * The gravitational constant we assume equal to earth's.
	 */
	private int gravity = -1000;					// -10m/s --> pixel = 0,01m --> 1000 pixels/s²
	/**
	 * The maximum speed  may have.
	 */
	public double maxSpeedX;					//	3 m/s --> pixel = 0,01m --> 300 pixel/s 
	/**
	 * The current speed in horizontal direction of  the creature.
	 */
	private double speedX = 0; 
	/**
	 * The current speed in vertical direction of  the creature.
	 */
	private double speedY = 0;
	/**
	 * The current acceleration in horizontal direction of  the creature.
	 */
	private double accelerationX = 0;
	/**
	 * The current acceleration in vertical direction of  the creature.
	 */
	private double accelerationY = 0;
	/**
	 * The current height of  the creature.
	 */
	public int heightSprite;
	/**
	 * The current width of  the creature.
	 */
	public int widthSprite;
	/**
	 * The current position of  the creature in horizontal direction.
	 */
	private double positionX = 0;
	/**
	 * The current position of  the creature in vertical direction.
	 */
	private double positionY = 0;
	/**
	 * The distance the creature has covered in horizontal direction after a short period of time dt.
	 */
	public int travelledDistanceX = 0;
	/**
	 * The distance the creature has covered in vertical direction after a short period of time dt.
	 */
	public int travelledDistanceY = 0;
	/**
	 * The moment the creature was moving for the last time.
	 */
	public double timeLastMovedX = 0;
	/**
	 * A boolean which shows whether the creature is moving or not.
	 */
	private boolean isMovingX = false;
	/**
	 * A boolean which shows whether the creature is jumping or not.
	 */
	private boolean isJumping = false;	
	/**
	 * The last direction the creature has moved in.
	 */
	public int lastDirection = 0;
	/**
	 * The number of alternating images for the cases 8 and 9 in the method getCurrentSprite.
	 */
	public int m;
	/**
	 * An integer that alternates from zero to m.
	 */
	public int alternatingIndex;
	/**
	 * The moment alternatingIndex was updated for the last time.
	 */
	public double changedIndex;
	/**
	 * An array of images that represent the creature in its different states.
	 */
	public Sprite[] images;
	
	int hitpoints;
	
	boolean isAlive = true;
	
	double lastCollisionEnemy;
	
}

