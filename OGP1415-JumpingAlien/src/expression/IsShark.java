package expression;
import jumpingalien.model.*;

public class IsShark extends Boolean {
	
	public IsShark (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Object evaluate(){
		if (creature.getClass() == Shark.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
