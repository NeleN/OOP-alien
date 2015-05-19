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
			for (Creature creature: getProgram().getUser().getWorld().getInWorldCreatures()){
				if (positionX > creature.getPositionX() && ((positionX - creature.getPositionX()) < distanceClosestCreature)){
					closestCreature = creature;
					distanceClosestCreature = positionX - creature.getPositionX();
				}
			}
			return closestCreature;
		}
		if (direction == Direction.RIGHT){
			for (Creature creature: getProgram().getUser().getWorld().getInWorldCreatures()){
				if (positionX < creature.getPositionX() && ((creature.getPositionX() - positionX) < distanceClosestCreature)){
					closestCreature = creature;
					distanceClosestCreature = creature.getPositionX() - positionX;
				}
			}
			return closestCreature;
		}
		if (direction == Direction.DOWN){
			for (Creature creature: getProgram().getUser().getWorld().getInWorldCreatures()){
				if (positionY > creature.getPositionY() && ((positionY - creature.getPositionY()) < distanceClosestCreature)){
					closestCreature = creature;
					distanceClosestCreature = positionY - creature.getPositionY();
				}
			}
			return closestCreature;
		}
		else{																						//if (direction == Direction.UP){
			for (Creature creature: getProgram().getUser().getWorld().getInWorldCreatures()){
				if (positionY < creature.getPositionY() && ((creature.getPositionY() - positionY) < distanceClosestCreature)){
					closestCreature = creature;
					distanceClosestCreature = creature.getPositionY() - positionY;
				}
			}
			return closestCreature;
		}
	}
	
	private Direction direction;
	private double positionX;
	private double positionY;
	private Creature closestCreature;
	private double distanceClosestCreature = Double.MAX_VALUE;
}
