package expression;

import jumpingalien.model.Creature;

public class GetX extends Number <Double>{
	
	public GetX(Expression<Creature> expr){
		this.creature = (Creature)expr;
	}
	
	private Creature creature;

	@Override
	public E evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

}
