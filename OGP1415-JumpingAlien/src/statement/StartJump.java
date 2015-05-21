package statement;

import jumpingalien.model.*;

/** A statement that makes the object executing the program start jumping */

public class StartJump extends Statement {

	public StartJump(){
		super();
	}
	
	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			if (getProgram().getUser().getClass() == Mazub.class)
				((Mazub)getProgram().getUser()).startJump();
		}
		if (getProgram().getTime() >= 0.001){
			if (getProgram().getUser().getClass() == Shark.class)
				((Mazub)getProgram().getUser()).startJump();
		}
		getProgram().timeUsed(0.001);		
	}
}
