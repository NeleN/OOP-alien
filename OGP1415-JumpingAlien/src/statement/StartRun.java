package statement;


import jumpingalien.model.*;
import expression.Expression;

/**
 * A statement that makes the object executing the program start moving in
 * the given direction
 */


public class StartRun extends Statement {
	
	public StartRun(Expression<?> direction){
		this.direction = (Direction) direction.evaluate();
	}
	
	private Direction direction;

	@Override
	public void execute() {
		if (getProgram().getUser().getClass() == Mazub.class)
			((Mazub)getProgram().getUser()).startMove(direction);
		if (getProgram().getUser().getClass() == Shark.class)
			((Mazub)getProgram().getUser()).startMove(direction);
		if (getProgram().getUser().getClass() == Plant.class)
			((Mazub)getProgram().getUser()).startMove(direction);
		if (getProgram().getUser().getClass() == Slime.class)
			((Mazub)getProgram().getUser()).startMove(direction);
		
	
	

}
