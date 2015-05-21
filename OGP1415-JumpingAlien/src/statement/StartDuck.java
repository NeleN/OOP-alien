package statement;
import jumpingalien.model.*;

/** A statement that makes the object executing the program start ducking */

public class StartDuck extends Statement {

	public StartDuck(){
		super();
	}
	
	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			((Mazub)getProgram().getUser()).startDuck();
		}
		getProgram().timeUsed(0.001);
	}
}

