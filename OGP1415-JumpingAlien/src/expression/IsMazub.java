package expression;
import jumpingalien.model.*;

public class IsMazub extends Boolean {
	
	public IsMazub (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Object evaluate(){
		if (creature.getClass() == Mazub.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
