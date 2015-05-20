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
		System.out.println(value.evaluate());
		time -= 0.001;
	}
}
