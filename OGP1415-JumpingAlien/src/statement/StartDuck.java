package statement;
import jumpingalien.model.*;

/** A statement that makes the object executing the program start ducking */

public class StartDuck extends Statement {

	public StartDuck(){
	}
	
	@Override
	public void execute() {
			((Mazub)getProgram().getUser()).startDuck();
		
	}
}

