package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
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


