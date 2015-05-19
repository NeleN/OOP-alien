package statement;

import expression.Expression;

/**
 * A statement that makes the object executing the program stop moving in
 * the given direction
 */

public class StopRun extends Statement {
	
	public StopRun(Expression<?> direction){
		this.direction = direction;
	}
	

	private Expression <?> direction;

	@Override
	public void execute() {
		getProgram().getUser().endMove();
		
	}
}
