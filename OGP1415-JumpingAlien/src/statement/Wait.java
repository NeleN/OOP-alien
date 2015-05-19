package statement;

import expression.Expression;
import jumpingalien.part3.programs.SourceLocation;

/**
 * A statement that suspends the execution of the program for the given
 * duration
 */

public class Wait extends Statement {

	public Wait(Expression<?> duration){
		this.duration = (long) duration.evaluate();
	}
	
	private long duration;

	@Override
	public void execute() {
		try {
			getProgram().wait(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
