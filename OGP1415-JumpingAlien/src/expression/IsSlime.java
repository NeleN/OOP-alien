package expression;
import jumpingalien.model.*;

public class IsSlime extends Boolean {
	
	public IsSlime (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Object evaluate(){
		if (creature.getClass() == Slime.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
