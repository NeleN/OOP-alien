package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.*;

@Value
public class IsDucking extends Bool {
	public IsDucking(Expression<Creature> expression){
		this.creature = (Mazub) expression.evaluate();
	}
	
	@Override
	public Boolean evaluate() {
		return creature.isDucking();
	}
	
	private Mazub creature;

}
