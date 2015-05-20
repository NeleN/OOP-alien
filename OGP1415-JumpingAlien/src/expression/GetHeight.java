package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;

@Value
public class GetHeight extends Number<Integer> {
	
	public GetHeight (Expression <Creature> expression){
		this.creature = (Creature) expression.evaluate();
	}
	
	@Override
	public Integer evaluate() {
		return creature.getHeightSprite();
	}
	
	private Creature creature;

}
