package expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionConstant extends Expression<Integer> {
	
	public DirectionConstant(Direction value){
		this.direction = value;
	}
	
	private Direction direction;
	
	public Integer evaluate(){
		if (direction == Direction.RIGHT || direction == Direction.UP){
			return 1;
		}
		else {
			return -1;
		}
	}

}
