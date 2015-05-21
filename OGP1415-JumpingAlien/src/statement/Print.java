package statement;

import expression.Expression;

/** A statement that prints the value of the given expression */


public class Print extends Statement {
	
	public Print (Expression<?> value){
		super();
		this.value = value;
		
	}
	
	private Expression<?> value;

	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			System.out.println(value.evaluate());
			getProgram().timeUsed(0.001);
		}
	}
}
