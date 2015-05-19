package expression;

import jumpingalien.model.Creature;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class SearchObject extends Expression<Creature> {
	
	public SearchObject(Expression <Direction> direction){
		this.direction = (Direction) direction.evaluate();
		positionX = getProgram().getUser().getPositionX();
		positionY = getProgram().getUser().getPositionY();
	}

	@Override
	public Object evaluate() {
		if (direction == Direction.LEFT){
			
		}
		if (direction == Direction.RIGHT){
			
		}
		if (direction == Direction.DOWN){
			
		}
		if (direction == Direction.UP){
			
		}
	}
	
	private Direction direction;
	private double positionX;
	private double positionY;

}
