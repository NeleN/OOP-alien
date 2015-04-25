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
public class Slime extends Creature {

	/**
	 * 
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
	
	@Override
	public void advanceTime(double dt) throws IllegalDeltaTimeException {
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
	
	public void startMove(Direction direction){
		super.startMove(direction, 100, 70);
	}
	
	public void startJump(){
		super.startJump(0);
	}
	
	void changeSchool(School newSchool){
		School oldSchool = this.getSchool();
		newSchool.pointsSchoolToSlime(this);
		this.belongsToSchool = newSchool;
		oldSchool.removeSlime(this);
		newSchool.addSlime(this);
		oldSchool.pointsSlimeToSchool(this);
		// TODO hitpoints
	}
	 
	public School getSchool(){
		return this.belongsToSchool;
	}
	
	public Sprite getCurrentSprite() {
		if ( this.lastDirection == 1 )
			return this.getImageAtIndex(1);
		else
			return this.getImageAtIndex(0);
	}
	
	@Override
	void loseHitpoints(int points){
		super.loseHitpoints(points-1);
		getSchool().losePoints();
	}
	
	private School belongsToSchool;

}
