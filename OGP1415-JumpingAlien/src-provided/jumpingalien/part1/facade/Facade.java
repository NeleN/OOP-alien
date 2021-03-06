package jumpingalien.part1.facade;


import jumpingalien.model.Direction;
import jumpingalien.model.IllegalDeltaTimeException;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

/**
 * 
 */

/**
 * @author Melanie Nijs
 *
 */
public class Facade implements IFacade {
	
	public Facade(){
		
	}
	
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX,pixelBottomY,sprites, 300, 100);
	}
	
	public int[] getLocation(Mazub alien){
		int[] array = {(int)alien.getPositionX(),(int)alien.getPositionY()};
		return array;
	}
	
	public double[] getVelocity(Mazub alien){
		double[] array = {alien.getSpeedX(),alien.getSpeedY()};
		return array;
	}
	
	public double[] getAcceleration(Mazub alien){
		double[] array = {alien.getAccelerationX(),alien.getAccelerationY()};
		return array;
	}
	
	public int[] getSize(Mazub alien){
		int[] array = {alien.getWidthSprite(),alien.getHeightSprite()};
		return array;
	}
	
	public Sprite getCurrentSprite(Mazub alien){
		return alien.getCurrentSprite();
	}
	
	public void startJump(Mazub alien){
		alien.startJump();
	}
	
	public void endJump(Mazub alien){
		alien.endJump();
	}
	
	public void startMoveLeft(Mazub alien){
		alien.startMove(Direction.LEFT);
	}
	
	public void endMoveLeft(Mazub alien){
		alien.endMove();
	}
	
	public void startMoveRight(Mazub alien){
		alien.startMove(Direction.RIGHT);
	}
	
	public void endMoveRight(Mazub alien){
		alien.endMove();
	}
	
	public void startDuck(Mazub alien){
		alien.startDuck();
	}
	
	public void endDuck(Mazub alien){
		alien.endDuck();
	}
	
	public void advanceTime(Mazub alien, double dt) throws jumpingalien.util.ModelException {
//		try {
//			alien.advanceTime(dt);
//		} catch (IllegalDeltaTimeException exc) {
//			throw new jumpingalien.util.ModelException("Illegal time interval", exc);
//		}
	}

}

