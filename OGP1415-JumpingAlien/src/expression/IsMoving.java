package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
public class IsMoving extends Bool {
	public IsMoving(Expression<Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Boolean evaluate() {
		return ((creature.getSpeedX() != 0) || (creature.getSpeedY() != 0));
	}
	
	private Creature creature;

}
