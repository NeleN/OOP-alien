package expression;
import jumpingalien.model.*;

public class IsPlant extends Bool {
	
	public IsPlant (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Boolean evaluate(){
		if (creature.getClass() == Plant.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
