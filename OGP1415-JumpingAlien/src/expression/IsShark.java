package expression;
import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.*;

@Value
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
