package statement;

import expression.Expression;
import jumpingalien.part3.programs.SourceLocation;

/**
 * A statement that suspends the execution of the program for the given
 * duration
 */

public class Wait extends Statement {

	public Wait(Expression<Long> duration){
		this.duration = duration.evaluate();
	}
	
	private long duration;

	@Override
	public void execute() {
		if (getProgram().getTime() >= duration){
			try {
				getProgram().wait(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			duration = (long)getProgram().getTime();
		}
		getProgram().timeUsed(0.1);
	}
}
