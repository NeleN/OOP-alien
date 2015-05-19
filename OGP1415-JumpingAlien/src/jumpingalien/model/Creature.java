package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of creatures.
 * @author Nele Nauwelaers and Melanie Nijs
 *
 */
public abstract class Creature{
	
	
	/**
	 * Initializes the creature at a given position and its sprites.
	 * 
	 * @param 	positionX
	 * 			The horizontal position of  the creature.
	 * @param 	positionY
	 * 			The vertical position of  the creature.
	 * @param 	sprites
	 * 			An array of images of the type Sprite.
	 * @param	maxSpeedX
	 * 			The maximum horizontal velocity.
	 * @param	hitpoints
	 * 			The number of hitpoints of a creature. 
	 * @pre		sprites should contain at least 10 images.
	 * 			| sprites.length != null && sprites.length >= 2
	 * @effect	the creature's new horizontal position equals positionX.
	 * 			| setPositionX(positionX)
	 * @effect	the creature's new vertical position equals positionY.
	 * 			| setPositionY(positionY)
	 * @post	the creature's images equal the given sprites.
	 * 			| new.images == sprites
	 * @post	The parameter m is used to define the number of possible images for cases 8 and 9.
	 * 			| m == (images.length-10)/2
	 * @post	...
	 * 			|new.hitpoint == hitpoints;
	 * @post 	...
	 * 			|new.maxSpeedX == maxSpeedX;
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
		assert ((!(images == null)) && (images.length >= 2));
		m = (images.length-10)/2;
		this.hitpoints = hitpoints;
		this.maxSpeedX = maxSpeedX;
	}
	
	/**
	 * TODO
	 * No documentation needed?
	 * @param dt
	 * @post 	The new horizontal position of the creature achieved in a period of time dt
	 * 			| new.newPositionX == (Math.max(getPositionX() + getTravelledDistanceX(dt), 0));
	 * @post	The new vertical position of the creature achieved in a period of time dt
	 * 			| new.newPositionY == (Math.max(getPositionY() + getTravelledDistanceY(dt), 0));;
	 * @effect	The new horizontal speed of the creature achieved in a period of time dt
	 * 			| setSpeedX(this.speedX + getAccelerationX()*dt)
	 * @effect	The new vertical speed of the creature achieved in a period of time dt
	 * 			| setSpeedY(this.speedY + getAccelerationY()*dt)
	 * @effect ...
	 * 			| if (getPositionY() == 0)
				|	setSpeedY(0)
	 * @throws	IllegalDeltaTimeException
	 * 			The given period of time is an invalid input.
	 * 			| !(dt>=0) || !(dt<0.2)
	 */
	@Raw 
	void advanceTime(double dt) throws IllegalDeltaTimeException {

		if (! isValidTime(dt)){
			throw new IllegalDeltaTimeException(dt);
		}
		else {
			if (! isAlive){
				timeOfDeath += dt;
				if (timeOfDeath >= 0.6){
					world.removeCreatures.add(this);
				}
			}
			newPositionX = (Math.max(getPositionX() + getTravelledDistanceX(dt), 0));
			newPositionY = (Math.max(getPositionY() + getTravelledDistanceY(dt), 0));
			setSpeedX(getSpeedX() + getAccelerationX()*dt); 
			setSpeedY(getSpeedY() + getAccelerationY()*dt);
			if (getPositionY() == 0){
				setSpeedY(0);
			}
			timeLastMovedX += dt;
			changedIndex += dt;
			if (changedIndex >= 0.075){
				this.updateIndex();
			}
			widthSprite = getCurrentSprite().getWidth();
			heightSprite =  getCurrentSprite().getHeight();
			if (getSpeedX() == 0 && getSpeedY() == 0){	
			}
			else{
				if (lastDirection == -1){
					if (world.getGeologicalFeature((int)newPositionX, (int)getPositionY() + 1) == 1
						|| (world.getGeologicalFeature((int)newPositionX, (int)(getPositionY()) + getHeightSprite()) == 1)){
						setPositionX((world.getTileNbX((int)newPositionX)+1)*world.getTileLength());
					}
					else{
						setPositionX(newPositionX);
					}
				}
				if (lastDirection == 1){
					if (world.getGeologicalFeature((int)newPositionX + getWidthSprite(), (int)getPositionY() + 1) == 1
						|| (world.getGeologicalFeature((int)newPositionX + getWidthSprite(), (int)(getPositionY()) + getHeightSprite()) == 1)){
						setPositionX(world.getTileNbX((int)newPositionX)*world.getTileLength());
					}
					else{
						setPositionX(newPositionX);
					}
				}
				if (getSpeedY() > 0){
					if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY + getHeightSprite()) == 1)
							|| (world.getGeologicalFeature((int)this.getPositionX() + this.getWidthSprite() - 2, (int)newPositionY + getHeightSprite()) == 1)){
						setPositionY((world.getTileNbY((int)newPositionY + getHeightSprite()))*world.getTileLength()-1-getHeightSprite());
					} else {
						setPositionY(newPositionY);
					}
				}
				if (getSpeedY() < 0){
					if ((world.getGeologicalFeature((int)this.getPositionX()+2, (int)newPositionY) == 1)
							|| (world.getGeologicalFeature((int)this.getPositionX() + this.getWidthSprite()-2, (int)newPositionY) == 1)){
						setPositionY((world.getTileNbY((int)newPositionY)+1)*world.getTileLength()-1);
						setSpeedY(0);
					} else {
						setPositionY(newPositionY);
					}
				}
			}
		}
	}

	
	
/****************************************************************************************************
 * 																									*
 * 									     BOOLEANS													*
 * 																									*										
 ****************************************************************************************************/	
	
	/**
	 * Checks if dt is a valid time span.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	True if and only if the given dt is positive and smaller than 0.2
	 * 			| (dt>=0) && (dt<0.2)
	 */
	@Raw @Basic
	boolean isValidTime(double dt){
		return (dt>=0) && (dt<=0.2);	
	}
	
	/**
	 * Checks whether there's a collision between two creatures or not.
	 * @param creature
	 * @return ... 
	 * 		| ((if (creature != this &&) ! (( this.getPositionX() + (this.getWidthSprite() - 1) < creature.getPositionX() )
	 *		|		|| (creature.getPositionX() + (creature.getWidthSprite() - 1) < this.getPositionX())
	 *		|		|| (this.getPositionY() + (this.getHeightSprite() - 1) < creature.getPositionY())
	 *		|		|| (creature.getPositionY() + (creature.getHeightSprite() - 1) < this.getPositionY())) )
	 * 
	 */
	boolean collisionDetection(Creature creature){
		if (creature != this){
			if (( this.getPositionX() + (this.getWidthSprite() - 1) < creature.getPositionX() )
					|| (creature.getPositionX() + (creature.getWidthSprite() - 1) < this.getPositionX())
					|| (this.getPositionY() + (this.getHeightSprite() - 1) < creature.getPositionY())
					|| (creature.getPositionY() + (creature.getHeightSprite() - 1) < this.getPositionY())){
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks whether there's a collision between the creature and the tile at coordinates(tilePixelX, tilePixelY). 
	 * @param tilePixelX
	 * @param tilePixelY
	 * @return	...
	 * 		| ! ( (int) this.getPositionX() + (this.getWidthSprite() -1 ) < tilePixelX )
				|| (tilePixelX + (world.getTileLength() -1 ) < (int)this.getPositionX())
				|| ((int)this.getPositionY() + (this.getHeightSprite() -1 ) < tilePixelY)
				|| (tilePixelY + (world.getTileLength() - 1) < (int)this.getPositionY())
	 */
	@Raw
	boolean collisionDetectionTile(int tilePixelX, int tilePixelY){
		if (( (int) this.getPositionX() + (this.getWidthSprite() -1 ) < tilePixelX )
				|| (tilePixelX + (world.getTileLength() -1 ) < (int)this.getPositionX())
				|| ((int)this.getPositionY() + (this.getHeightSprite() -1 ) < tilePixelY)
				|| (tilePixelY + (world.getTileLength() - 1) < (int)this.getPositionY())){
			return false;
		}
		else {
			return true;
		}
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
	public	boolean isJumping(){
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
		return (positionX>=World.getXMin() && positionX<=World.getXMax()) && (positionY>=World.getYMin() && positionY<=World.getYMax());
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
	boolean isValidSpeedX(double speed){
		return Math.abs(speed) <= this.maxSpeedX;
	}
	
	/**
	 * Checks whether the creature has moved horizontally within the last second.
	 * 
	 * @return	True if and only if the creature has moved within the last second.
	 * 			| timeLastMovedX < 1
	 */
	@Basic
	boolean hasMovedX(){
		return (timeLastMovedX < 1);
	}

/****************************************************************************************************
 * 																									*
 * 									     GETTERS AND SETTERS										*
 * 																									*										
 ****************************************************************************************************/
	
	public World getWorld(){
		return this.world;
	}
	
	
	/**
	 * Adjusts the hitpoints after a collision between Mazub and a plant;
	 * @param alien
	 * @param plant
	 * @effect ...
	 * 		| alien.gainHitpoints(50);
	 * @effect..
	 * 		| plant.dies();
	 */
	void collisionMazubPlant(Mazub alien, Plant plant){
		alien.gainHitpoints(50);
		plant.dies();
	}
	
	/**
	 * Adjusts the hitpoints after a collision between Mazub and a slime;
	 * @param alien
	 * @param slime
	 * @effect ...
	 * 		|alien.loseHitpoints(50);
	 * @effect ...
	 * 		|slime.loseHitpoints(50);
	 * @post ...
	 * 		|alien.new.lastCollisionEnemy == 0;
	 */
	void collisionMazubSlime(Mazub alien, Slime slime){
		alien.loseHitpoints(50);
		slime.loseHitpoints(50);
		alien.lastCollisionEnemy = 0;
	}
	
	/**
	 * Adjusts the hitpoints after a collision between Mazub and a shark;
	 * @param alien
	 * @param shark
	 * @effect ...
	 * 		|alien.loseHitpoints(50);
	 * @effect ...
	 * 		|shark.loseHitpoints(50);
	 * @post ...
	 * 		|alien.new.lastCollisionEnemy == 0;
	 */
	void collisionMazubShark(Mazub alien, Shark shark){
		alien.loseHitpoints(50);
		shark.loseHitpoints(50);
		alien.lastCollisionEnemy = 0;
	}
	
	/**
	 * Adjusts the moving direction after a collision between two sharks;
	 * @param shark1
	 * @param shark2
	 * @effect ...
	 * 		| if (shark1.getPositionX() <  shark2.getPositionX())
	 * 		| 	shark1.startMove(Direction.LEFT);
	 * 		|	shark2.startMove(Direction.RIGHT);
	 * @effect ..
	 * 		| shark1.startMove(Direction.RIGHT);
	 * 		| shark2.startMove(Direction.LEFT);
	 */
	void collisionSharkShark(Shark shark1, Shark shark2){
		if (shark1.getPositionX() <  shark2.getPositionX()){
			shark1.startMove(Direction.LEFT);
			shark2.startMove(Direction.RIGHT);
		}
		else{
			shark1.startMove(Direction.RIGHT);
			shark2.startMove(Direction.LEFT);
		}
	}
	
	/**
	 * Adjusts the hitpoints after a collision between a slime and a shark;
	 * @param shark
	 * @param slime
	 * @effect ...
	 * 		|slime.loseHitpoints(50);
	 * @effect ...
	 * 		|shark.loseHitpoints(50);
	 */
	void collisionSharkSlime(Shark shark, Slime slime){
		slime.loseHitpoints(50);
		shark.loseHitpoints(50);
	}
	
	/**
	 * Adjusts the moving direction and school after a collision between two slimes;
	 * @param slime1
	 * @param slime2
	 * @effect ...
	 * 		|if (slime1.getPositionX() <  slime2.getPositionX()
	 * 		|	slime1.startMove(Direction.LEFT);
	 * 		|	slime2.startMove(Direction.RIGHT);
	 * @effect ...
	 * 		|else
	 * 		|	slime1.startMove(Direction.RIGHT);
	 * 		|	slime2.startMove(Direction.LEFT);
	 * @effect ...
	 * 		|if (slime1.getSchool().getNbSlimesInSchool() > slime2.getSchool().getNbSlimesInSchool())
	 * 		|	slime2.changeSchool(slime1.getSchool());
	 * @effect ...
	 * 		|if (slime1.getSchool().getNbSlimesInSchool() < slime2.getSchool().getNbSlimesInSchool())
	 * 		|	slime1.changeSchool(slime2.getSchool()); 
	 * 
	 */
	void collisionSlimeSlime(Slime slime1, Slime slime2){
		if (slime1.getPositionX() <  slime2.getPositionX()){
			slime1.startMove(Direction.LEFT);
			slime2.startMove(Direction.RIGHT);
		}
		else{
			slime1.startMove(Direction.RIGHT);
			slime2.startMove(Direction.LEFT);
		}
				
		if (slime1.getSchool().getNbSlimesInSchool() > slime2.getSchool().getNbSlimesInSchool()){
			slime2.changeSchool(slime1.getSchool());
		}
		if (slime1.getSchool().getNbSlimesInSchool() < slime2.getSchool().getNbSlimesInSchool()){
			slime1.changeSchool(slime2.getSchool()); 
		}
	}
	
	/**
	 * Updates the index of the alternating sprites in getCurrentSprite.
	 * @post ...
	 * 		| if (alternatingIndex<m)
	 * 		|	new.alternatingIndex == this.alternatingIndex + 1;
	 * 		| else
	 * 		|	new.alternatingIndex == 0;
	 * @post ...
	 * 		|new.changedIndex == 0;
	 */
	@Basic
	private void updateIndex(){
		if (alternatingIndex<m){
			alternatingIndex+=1;
		}
		else{
			alternatingIndex=0;
		}
		changedIndex = 0;
	}
	

	/**
	 * Return the number of hitpoints the creature has.
	 * @return
	 * 		The integer hitpoints.
	 */
	@Basic
	public int getHitpoints(){
		return this.hitpoints;
	}
	
	/**
	 * Adds points to the hitpoints of the creature.
	 * @param points
	 * @post ...
	 *		| new.hitpoints == this.hitpoints + points; 
	 */
	@Basic
	void gainHitpoints(int points){
		hitpoints+=points;
	}
	
	/**
	 * Subtracts points from the hitpoints of the creature.
	 * @param points
	 * @post ...
	 * 		| new.hitpoints == this.hitpoints - points;
	 * @effect ...
	 * 		|if (hitpoints <= 0)
	 * 		|	this.dies();
	 */
	@Raw
	void loseHitpoints(int points){
		hitpoints-=points;
		if (hitpoints <= 0){
			this.dies();
		}
	}
	
	/**
	 * Makes the creature die.
	 * @post ...
	 * 		| new.timeOfDeath == 0;
	 * @post ...
	 * 		| new.isAlive == false;
	 */
	@Basic
	void dies(){
		this.timeOfDeath = 0;
		this.isAlive=false;
	}
		
	/**
	 * This method calculates the horizontal travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return 	...
	 * 		| (getSpeedX()*dt  + 0.5*getAccelerationX()*Math.pow(dt, 2))		
	 */
	@Raw
	double getTravelledDistanceX(double dt){
		 return (getSpeedX()*dt  + 0.5*getAccelerationX()*Math.pow(dt, 2));
	}
		
	/**
	 * This method calculates the vertical travelled distance.
	 * 
	 * @param 	dt
	 * 			an infinitesimally small period of time
	 * @return	... 
	 * 		| (getSpeedY()*dt  + 0.5*getAccelerationY()*Math.pow(dt, 2))
	 */
	@Raw
	double getTravelledDistanceY(double dt){
		 return (getSpeedY()*dt  + 0.5*getAccelerationY()*Math.pow(dt, 2));
	}	

	/**
	 * This method initiates a movement of  the creature.
	 * 
	 * @param 	direction
	 * 			the direction of the creature
	 * @effect ...
	 * 		|setSpeedX(speed*direction.getDirection());
	 * @effect ...
	 * 		|setAccelerationX(acceleration*direction.getDirection());
	 * @post ...
	 * 		|new.isMovingX == true
	 * @post ...
	 * 		| new.lastDirection == direction
	 * @post ...
	 * 		| new.changedIndex == 0;
	 * @post ...
	 * 		| new.alternatingIndex == 0
	 */
	@Raw
	void startMove(Direction direction, int speed, int acceleration){
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
	 * @effect ...
	 * 		| setSpeedX(0);
	 * @effect ...
	 * 		| setAccelerationX(0);
	 * @post ...
	 * 		| new.isMovingX == false
	 * @post ...
	 * 		| new.timeLastMovedX == 0;
	 * 
	 * TODO If there are multiple
	 *	ongoing movements at the same time, e.g. startMove(left) has been invoked while Mazub was already moving to the right, the horizontal velocity
	 *	shall not be set to zero before all ongoing movements are terminated.
	 */
	public void endMove(){
		setSpeedX(0);
		setAccelerationX(0);
		timeLastMovedX = 0;
		this.isMovingX = false;
	}
	
	/**
	 * This method initiates a jump of  the creature.
	 * @param speedY
	 * @effect ...
	 * 		| setSpeedY(speedY);
	 * @post ...
	 * 		| new.isJumping == true;
	 */
	@Raw
	void startJump(double speedY){
			setSpeedY(speedY);				
			this.isJumping = true;
	}
	
	/**
	 * This method terminates a jump of the creature.
	 * 
	 * @effect ...
	 * 		| if (getSpeedY() > 0)
	 * 		|	setSpeedY(0);
	 * @effect ...
	 * 		| setAccelerationY(gravity);
	 * @post ...
	 * 		| new.isJumping == false;
	 */
	public void endJump(){
		if (getSpeedY() > 0){
			setSpeedY(0);
		}
		setAccelerationY(gravity);
		this.isJumping = false;
	}
	
	
	/**
	 * Makes the creature start to move in a random direction.
	 * 
	 * @param dt
	 * @param speed
	 * @param acceleration
	 * @param minDuration
	 * @param maxDuration
	 * @post ...
	 * 		| new.timer == this.timer + dt;
	 * @post ...
	 * 		| if (timer >= randomNumber)
	 * 		| 	new.timer==0;
	 * @post ...
	 * 		| if (timer >= randomNumber)
	 * 		|	new.lastJump== this.lastJump+1;
	 * @effect ...
	 * 		| if ((timer >= randomNumber) && isJumping())
	 * 		|	endJump();
	 * @effect ...
	 * 		| if ((timer >= randomNumber) && (lastDirection == -1))
	 * 		|	startMove(Direction.RIGHT, speed, acceleration);
	 * @effect ...
	 * 		| if ((timer >= randomNumber) && (lastDirection == 1))
	 * 		|	startMove(Direction.LEFT, speed, acceleration);	
	 */
	@Raw
	void randomMovement(double dt, int speed, int acceleration, int minDuration, int maxDuration){
		double randomNumber = ((double)(Math.random() * (maxDuration - minDuration)) + minDuration );
		timer += dt;
		if (timer >= randomNumber){
			timer=0;
			lastJump+=1;
			if (isJumping()){
				endJump();
			}
			if (lastDirection == -1){
				startMove(Direction.RIGHT, speed, acceleration);
			}
			else {
				startMove(Direction.LEFT, speed, acceleration);
			}
		}
	}
		
	/**
	 * Returns the horizontal position of  the creature.
	 * @return	...
	 * 		| this.positionX;
	 */
	@Basic 
	public double getPositionX(){
		return this.positionX;
	}
	
	/**
	 * Returns the vertical position of  the creature.
	 * @return ...
	 * 		| this.positionY;
	 */
	@Basic
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Returns the right side of the rectangle that is the creature.
	 * @return ...
	 * 		|(this.positionX + this.getWidthSprite());
	 */
	double getRightSideOfRectangle(){
		return (this.positionX + this.getWidthSprite());
	}
	
	/**
	 * Returns the top of the rectangle that is the creature.
	 * @return ...
	 * 		| (this.positionY + this.getHeightSprite());	
	 */
	double getTopSideOfRectangle(){
		return (this.positionY + this.getHeightSprite());
	}
	
	/**
	 * Changes the horizontal position of the creature.
	 * 
	 * @param 	position
	 * 			The position the creature should be set to.
	 *@effect ...
	 *		| if (! isValidPosition(position,0))
	 *		| this.dies();
	 *@post ...
	 *		| if ( isValidPosition(position, 0))
	 *		|	new.positionX == position;
	 */
	@Raw
	void setPositionX(double position){
			if (! isValidPosition(position,0)){
				this.dies();
			}
			else {
				this.positionX = position;
			}
		}
		
	/**
	 * Changes the vertical position of the creature.
	 * 
	 * @param 	position
	 * 			The position the creature should be set to.
	 * @effect ...
	 * 		| if ( ! isValidPosition(0,position))
	 * 		| this.dies();
	 * @post ...
	 *		| if ( isValidPosition(0, position))
	 *		|	new.positionY == position;
	 * 
	 */
	@Raw
	void setPositionY(double position)  {
			if ( ! isValidPosition(0,position)){
				this.dies();
			}
			else {
				this.positionY = position;
			}
		}
			
	/**
	 * Return the current height of the creature.
	 * @return
	 * 		|this.heightSprite
	 */
	@Basic
	public int getHeightSprite(){
		return this.heightSprite;
	}
	
	/**
	 * Return the current width of the creature.
	 * @return
	 * 		|this.widthSprite
	 */
	@Basic
	public int getWidthSprite(){
		return this.widthSprite;
	}
	
	/**
	 * Sets the height of the creature to a given height.
	 * 
	 * @param height
	 * @post The new height of  should be equal to the given height
	 * 		| new.heigthSprite == height
	 * 
	 */
	@Raw @Basic
	void setHeightSprite(int height){
		this.heightSprite = height;
	}
	
	/**
	 * Sets the width of the creature to a given width.
	 * 
	 * @param width
	 * @post	The new width of  should be equal to the given width
	 * 	| new.widthSprite == width
	 */
	@Raw @Basic
	void setWidthSprite(int width){
		this.widthSprite= width;
	}
	
	/**
	 * Return the current speed in horizontal direction of the creature .
	 * @return ...
	 * 		| this.speedX
	 */
	@Basic
	public double getSpeedX(){
		return this.speedX;
	}
	
	/**
	 * Return the current speed in vertical direction of the creature.
	 * return ...
	 * 		| this.speedY
	 */
	@Basic
	public double getSpeedY(){
		return this.speedY;
	}
	
	/**
	 * Return the current acceleration in horizontal direction of the creature.
	 * @return ...
	 * 		| this.accelerationX
	 */
	@Basic
	public double getAccelerationX(){
		return this.accelerationX;
	} 
	
	/**
	 * Return the current acceleration in vertical direction of the creature.
	 * @return ...
	 * 		| this.accelerationY
	 */
	@Basic
	public double getAccelerationY(){
		return this.accelerationY;
	}
	
	/**
	 * Sets the horizontal speed of the creature to a given speed
	 * 
	 * @param 	speed
	 * @post ...
	 *		| if (isValidSpeedX(speed))
	 * 	 	|	new.speedX == speed
	 * @post ...
	 * 		| else
	 * 		|	 new.speedX == (this.lastDirection*maxSpeedX)
	 * 
	 */
	@Raw
	void setSpeedX(double speed){
		if (isValidSpeedX(speed))
			this.speedX = speed;
		else
			this.speedX = (this.lastDirection*maxSpeedX);
	}
	
	/**
	 * Sets the vertical speed of the creature to a given speed
	 * 
	 * @param 	speed
	 * @post ...
	 * 		| new.speedY == speed
	 */
	@Raw
	void setSpeedY(double speed){
		this.speedY = speed;
	}
	
	/**
	 * Sets the horizontal acceleration of the creature to a given acceleration
	 * 
	 * @param 	acc
	 * @post ...
	 * 		| new.accelerationX == acc
	 */
	@Raw
	void setAccelerationX(double acc){
		this.accelerationX = acc;
	}
	
	/**
	 * Sets the vertical acceleration of  to a given acceleration
	 * 
	 * @param 	acc
	 * @post ...
	 * 		| new.accelerationY == acc
	 */
	@Raw
	void setAccelerationY(double acc){
		this.accelerationY = acc; 
	}
	
	/**
	 * returns an integer array of the x and y coordinate of the creature's current position.
	 * @return
	 * 		|  {(int)this.getPositionX(), (int)this.getPositionY()}
	 */
	public int[] getLocation(){
		int[] array = {(int)this.getPositionX(), (int)this.getPositionY()};
		return array;
	}	
		
	/**
	 * Returns an image corresponding to the current index.
	 * 
	 * @param 	index
	 * 		An integer number that represents the current state of the creature.
	 * @pre	The index should be positive and may not exceed the length of the array images.
	 * 		| (index >= 0) && (index < images.length)
	 * @return	
	 * 		| this.images[index];
	 */
	@Basic @Raw
	public Sprite getImageAtIndex(int index){
		assert (index >= 0) && (index < images.length);
		return this.images[index];
	}
	
	/**
	 * Returns an array of sprites called images.
	 * @return 
	 * 		| this.images
	 */
	@Basic @Immutable
	Sprite[] getImages(){
		return this.images;
	}
	
	/**
	 * Returns the current sprite of the creature.
	 * @return
	 * 		The current sprite
	 */
	abstract Sprite getCurrentSprite();
	 
	
/****************************************************************************************************
 * 																									*
 * 									     	VARIABLES												*
 * 																									*										
 ****************************************************************************************************/
		
	
	/**
	 * The gravitational constant we assume equal to earth's.
	 */
	final int gravity = -1000;					// -10m/s --> pixel = 0,01m --> 1000 pixels/s²
	
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
	public int lastDirection;
	
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
	public final Sprite[] images;
	
	/**
	 * An integer that represents the hitpoints of the creature.
	 */
	int hitpoints;
	
	/**
	 * A boolean that is true if the creature is alive.
	 */
	boolean isAlive = true;
	
	/**
	 * A double that is used to keep the creature immune. 
	 */
	double lastCollisionEnemy = 1;
	
	/**
	 * The world in which the creature lives.
	 */
	public World world;

	/**
	 * A timer that's used to calculate a random movement. 
	 */
	private double timer;
	
	/**
	 * An integer that shows how many movements ago the creature has jumped.
	 */
	int lastJump;
	
	/**
	 * The time the creature is in the air.
	 */
	double timeInAir;
	
	/**
	 * The time the creature is in the water.
	 */
	double timeInWater;
	
	/**
	 * The time the creature is in magma.
	 */
	double timeInMagma;
	
	/**
	 * The new horizontal position of the creature.
	 */
	double newPositionX;
	
	/**
	 * The new vertical position of the creature.
	 */
	double newPositionY;
	
	/**
	 * The time the creature is dead.
	 */
	int timeOfDeath;
	
}

