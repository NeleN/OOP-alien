package statement;

import jumpingalien.model.Direction;
import expression.Expression;

/**
 * A statement that makes the object executing the program stop moving in
 * the given direction
 */

public class StopRun extends Statement {
	
	public StopRun(Expression<?> direction){
		if (direction.evaluate() != null){
			if ((int) direction.evaluate() == 1){
				this.direction = Direction.RIGHT;
			}
			
			else{
				this.direction = Direction.LEFT;
			}
		}
	}
	

	private Direction direction;

	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			getProgram().getUser().endMove(direction);
			getProgram().timeUsed(0.001);
		}
	}
}
