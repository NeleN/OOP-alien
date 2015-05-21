package statement;

import expression.Expression;


/**
 * A statement that executes the given ifBody if the condition evaluates to
 * true, and the given elseBody otherwise.
 * The elseBody is optional, and can be null.
 */

public class If extends Statement {
	
	public If(Expression<Boolean> condition, Statement ifBody, Statement elseBody){
		super();
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	private Expression<Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	
	
	@Override
	public void execute() throws BreakException{
		if (getProgram().getTime() >= 0.001){
			if ((boolean) condition.evaluate()){
				ifBody.execute();
			}
			else{
				if (getProgram().getTime() >= 0.001){
					if (elseBody != null){
					elseBody.execute();
					}
				}
			}
			getProgram().timeUsed(0.001);
		}
	}
}

			
	
	
	


