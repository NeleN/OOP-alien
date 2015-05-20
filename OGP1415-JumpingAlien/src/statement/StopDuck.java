package statement;
import jumpingalien.model.*;
/** A statement that makes the object executing the program stop ducking */

public class StopDuck extends Statement {
	
	public StopDuck(){
		super();
	}
	
	@Override
	public void execute() {
		((Mazub)getProgram().getUser()).endDuck();	
		time -= 0.001;
	}
}
