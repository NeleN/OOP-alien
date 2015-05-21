package statement;
import jumpingalien.model.*;
/** A statement that makes the object executing the program stop ducking */

public class StopDuck extends Statement {
	
	public StopDuck(){
	}
	
	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
			((Mazub)getProgram().getUser()).endDuck();	
			getProgram().timeUsed(0.001);
		}
	}
}
