package expression;
import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.*;

@Value
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
