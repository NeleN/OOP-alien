package expression;

import jumpingalien.model.Creature;

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
