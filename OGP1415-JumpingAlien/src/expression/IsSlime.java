package expression;
import jumpingalien.model.*;

public class IsSlime extends Boolean {
	
	public boolean evaluate(E expression){
		if (expression.class == Slime.class){
			return true;
		}
		else {
			return false;
		}
	}
}
