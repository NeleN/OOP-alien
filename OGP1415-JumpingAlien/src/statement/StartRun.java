package statement;


import expression.Expression;

/**
 * A statement that makes the object executing the program start moving in
 * the given direction
 */


public class StartRun extends Statement {
	
	public StartRun(Expression<?> direction){
		this.direction = direction;
	}
	
	private Expression <?> direction;

	@Override
	public void execute() {
		getProgram().getUser().;
		
	}
	
	

}