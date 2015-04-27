/**
 * 
 */
package jumpingalien.model;

import java.lang.reflect.GenericArrayType;
import java.util.*;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele Nauwelaers and Melanie Nijs
 *
 */
/**
 * @author Nele
 *
 */
public class Slime extends Creature {

	/**
	 * Constructor of a slime
	 * 
	 * @effect	...
	 * 			| super.advancetime(dt)
	 * @post	...
	 * 			| new.belongsToSchool = school
	 * @post	...
	 * 			| school.new.getSlimesInSchool().contains(this)
	 * @effect	...
	 * 			| if (Math.random()>0.5)
	 *				startMove(Direction.RIGHT);
	 * @effect	...
	 * 			| if (Math.random()<=0.5)
	 *				startMove(Direction.RIGHT);
	 */
	@Raw
	public Slime (int positionX, int positionY, Sprite[] sprites, School school) {
		super (positionX, positionY, sprites, 250, 100);
		this.setAccelerationY(gravity);
		belongsToSchool = school;
		school.addSlime(this);
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
		randomMovement(dt, 100, 70, 2, 6);
		if (timeInWater > 0.2){
			loseHitpoints(2);
			timeInWater-=0.2;
		}
		if (timeInMagma > 0.2){
			loseHitpoints(50);
			timeInMagma-=0.2;
		}
		for (Mazub alien: world.getMazubsInWorld()){
			if (! alien.isImmune()){
				if (this.collisionDetection(alien)){
					collisionMazubSlime(alien, this);
				}
			}
		}
		for (Shark shark: world.getSharksInWorld()){
			if (this.collisionDetection(shark)){
				collisionSharkSlime(shark,this);
			}
		}
		for (Slime slime: world.getSlimesInWorld()){
			if (this.collisionDetection(slime)){
				collisionSlimeSlime(this,slime);
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
	
	/**
	 * @param 	direction
	 * 			the direction of the creature
	 * @effect	...
	 * 			super.startMove(direction, 100, 70);
	 */
	void startMove(Direction direction){
		super.startMove(direction, 100, 70);
	}
	
	/**
	 * @effect	...
	 * 			super.startJump(0)
	 */
	void startJump(){
		super.startJump(0);
	}
	
	/**
	 * @param 	newSchool
	 * 			The school the creature will change to
	 * @effect	...
	 * 			| newSchool.pointsSchoolToSlime(this);
	 * @post	...
	 * 			| this.new.belongsToSchool = school
	 * @effect	...
	 * 			| newSchool.addSlime(this)
	 * @effect	...
	 * 			| oldSchool.poinsSlimeToSchool(this)
	 */
	void changeSchool(School newSchool){
		School oldSchool = this.getSchool();
		newSchool.pointsSchoolToSlime(this);
		this.belongsToSchool = newSchool;
		oldSchool.removeSlime(this);
		newSchool.addSlime(this);
		oldSchool.pointsSlimeToSchool(this);
	}
	 
	/**
	 * @return
	 * 		The school the slime belongs to
	 */
	public School getSchool(){
		return this.belongsToSchool;
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
	
	/* (non-Javadoc)
	 * @see jumpingalien.model.Creature#loseHitpoints(int)
	 * 
	 * @effect	...
	 * 			| super.loseHitpoints(points-1)
	 * @effect	...
	 * 			| getSchool().losePoints()
	 */
	@Override
	void loseHitpoints(int points){
		super.loseHitpoints(points-1);
		getSchool().losePoints();
	}
	
	/**
	 * The school the slimes currently belongs to
	 */
	private School belongsToSchool;

}
