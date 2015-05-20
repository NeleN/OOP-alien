package expression;

import jumpingalien.model.Creature;

public class GetY extends Number <Double>{
	
	public GetY(Expression<Creature> expression){
		this.creature = (Creature)expression.evaluate();
	}

	@Override
	public Double evaluate() {
		return creature.getPositionY();
	}
	
	private Creature creature;

}


