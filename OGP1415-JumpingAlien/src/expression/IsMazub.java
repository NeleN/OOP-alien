package expression;
import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.*;

@Value
public class IsMazub extends Bool {
	
	public IsMazub (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	public Boolean evaluate(){
		if (creature.getClass() == Mazub.class){
			return true;
		}
		else {
			return false;
		}
	}
	
	private Creature creature;
}
