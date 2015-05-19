package statement;

import jumpingalien.model.*;

/** A statement that makes the object executing the program start jumping */

public class StartJump extends Statement {

	public StartJump(){
	}
	
	@Override
	public void execute() {
		if (getProgram().getUser().getClass() == Mazub.class)
			((Mazub)getProgram().getUser()).startJump();
		if (getProgram().getUser().getClass() == Shark.class)
			((Mazub)getProgram().getUser()).startJump();
		
	}
}
