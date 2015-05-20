package statement;
import jumpingalien.model.*;

/** A statement that makes the object executing the program start ducking */

public class StartDuck extends Statement {

	public StartDuck(){
		super();
	}
	
	@Override
	public void execute() {
		if (time > 0.001){
			((Mazub)getProgram().getUser()).startDuck();
		}
		time -= 0.001;
	}
}

