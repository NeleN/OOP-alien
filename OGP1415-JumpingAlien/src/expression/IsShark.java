package expression;
import jumpingalien.model.*;

public class IsShark extends Bool {
	
	public IsShark (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Boolean evaluate(){
		if (creature.getClass() == Shark.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
