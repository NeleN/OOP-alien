package statement;


import jumpingalien.model.*;
import expression.Expression;

/**
 * A statement that makes the object executing the program start moving in
 * the given direction
 */


public class StartRun extends Statement {
	
	public StartRun(Expression<Integer> direction){
		System.out.println(direction.evaluate());
		if ((int) direction.evaluate() == 1){
			this.direction = Direction.RIGHT;
		}
		if (direction.evaluate() == null){
			
		}
		else{
			this.direction = Direction.LEFT;
		}
	}
	
	private Direction direction;

	@Override
	public void execute() {
		if (time > 0.001){
			if (getProgram().getUser().getClass() == Mazub.class){
				((Mazub)getProgram().getUser()).startMove(direction);
			}
			time -= 0.001;
		}
		time -= 0.001;
		if (time > 0.001){
			if (getProgram().getUser().getClass() == Shark.class){
				((Mazub)getProgram().getUser()).startMove(direction);
			}
			time -= 0.001;
		}
		time -= 0.001;
		if (time > 0.001){
			if (getProgram().getUser().getClass() == Plant.class){
				((Mazub)getProgram().getUser()).startMove(direction);
			}
			time -= 0.001;
		}
		time -=0.001;
		if (time > 0.001){
			if (getProgram().getUser().getClass() == Slime.class){
				((Mazub)getProgram().getUser()).startMove(direction);
			}
			time -= 0.001;
		}
		time -= 0.001;
	}
	
}
