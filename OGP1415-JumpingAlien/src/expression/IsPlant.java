package expression;
import jumpingalien.model.*;

public class IsPlant extends Boolean {
	
	public IsPlant (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Object evaluate(){
		if (creature.getClass() == Plant.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
