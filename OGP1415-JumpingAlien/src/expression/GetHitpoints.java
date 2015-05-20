package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
public class GetHitpoints extends Number<Integer> {

	public GetHitpoints(Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Integer evaluate(){
		return creature.getHitpoints();
	}
	
	Creature creature;
	
}
