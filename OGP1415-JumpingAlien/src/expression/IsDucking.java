package expression;

import jumpingalien.model.*;

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
