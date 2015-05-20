package expression;

import jumpingalien.model.Creature;

public class GetX extends Number <Double>{
	
	public GetX(Expression<Creature> expression){
		this.creature = (Creature)expression.evaluate();
	}

	@Override
	public Double evaluate() {
		return creature.getPositionX();
	}
	
	private Creature creature;

}


