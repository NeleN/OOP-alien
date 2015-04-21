/**
 * 
 */
package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Nele
 *
 */
public class Slime extends Creature {

	/**
	 * 
	 */
	@Raw
	public Slime (int positionX, int positionY, Sprite[] sprites, School school) {
		super (positionX, positionY, sprites, 250, 100);
		belongsToSchool = school;
	}
	
	@Override
	public void advanceTime(double dt) throws IllegalDeltaTimeException {
		super.advanceTime(dt);
		for (Mazub alien: world.getMazubsInWorld()){
			if (this.collisionDetection(alien)){
				collisionMazubSlime(alien, this);
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
