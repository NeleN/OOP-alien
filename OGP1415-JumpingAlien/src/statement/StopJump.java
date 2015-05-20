package statement;

/** A statement that makes the object executing the program stop jumping */

public class StopJump extends Statement {
	
	public StopJump(){
		super();
	}
	
	@Override
	public void execute() {
		getProgram().getUser().endJump();
		time -= 0.001;
	}
}
