package expression;
import jumpingalien.model.*;

public class IsSlime extends Bool {
	
	public IsSlime (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Boolean evaluate(){
		if (creature.getClass() == Slime.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
