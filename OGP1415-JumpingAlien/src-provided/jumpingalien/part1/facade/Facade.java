package jumpingalien.part1.facade;


import jumpingalien.model.Direction;
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
		return new Mazub(pixelLeftX,pixelBottomY,sprites);
	}
	
	public int[] getLocation(Mazub alien){
		int[] array = {alien.getPositionX(),alien.getPositionY()};
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
		int[] array = {alien.getHeightMazub(),alien.getWidthMazub()};
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
	
	public void advanceTime(Mazub alien, double dt){
		alien.advanceTime(dt);
	}
	

}

