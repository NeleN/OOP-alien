package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
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


