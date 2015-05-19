package statement;

import expression.Expression;
import jumpingalien.part3.programs.SourceLocation;


/**
 * A statement that executes the given ifBody if the condition evaluates to
 * true, and the given elseBody otherwise.
 * The elseBody is optional, and can be null.
 */

public class If extends Statement {
	
	public If(Expression<Boolean> condition, Statement ifBody, Statement elseBody){
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	private Expression<Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	
	
	@Override
	public void execute() {
		if ((boolean) condition.evaluate()){
			ifBody.execute();
		}
		else{
			if (elseBody != null)
				elseBody.execute();
		}
		
			
	}
	
	

}
