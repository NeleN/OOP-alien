package statement;
import jumpingalien.model.*;
/** A statement that makes the object executing the program stop ducking */

public class StopDuck extends Statement {
	
	public StopDuck(){
	}
	
	@Override
	public void execute() {
		((Mazub)getProgram().getUser()).endDuck();	
	}
}
