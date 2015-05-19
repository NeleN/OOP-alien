package expression;

import jumpingalien.model.Creature;

public class GetY extends Number <Double>{
	
	public GetY(Expression<Creature> expression){
		this.creature = (Creature)expression.evaluate();
	}

	@Override
	public Object evaluate() {
		return creature.getPositionY();
	}
	
	private Creature creature;

}


