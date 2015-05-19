package statement;

import expression.Expression;


/**
 * A statement that executes the given body while the given condition
 * evaluates to true
 */

public class While extends Statement {

	public While(Expression<Boolean> condition, Statement body){
		this.condition = condition;
		this.body = body;
	}
	
	private Statement body;
	private Expression<Boolean> condition;
	
	@Override
	public void execute() {
		if ((boolean) condition.evaluate())
			body.execute();
	}
	
	
}
