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
	static boolean isLooping = true;
	
	@Override
	public void execute() {
		while ((boolean) condition.evaluate() && isLooping){
			body.execute();
			isLooping = true;	
	}
	
	}
}
