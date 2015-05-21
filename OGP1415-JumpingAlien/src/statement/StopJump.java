package statement;

/** A statement that makes the object executing the program stop jumping */

public class StopJump extends Statement {
	
	public StopJump(){
	}
	
	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			getProgram().getUser().endJump();
			getProgram().timeUsed(0.001);
		}
	}
}
