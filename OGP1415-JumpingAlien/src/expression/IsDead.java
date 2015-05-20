package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
public class IsDead extends Bool {
	public IsDead(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Boolean evaluate() {
		return ! creature.isAlive();
	}
	
	private Creature creature;

}
