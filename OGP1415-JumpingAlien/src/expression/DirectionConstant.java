package expression;
import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.part3.programs.IProgramFactory.Direction;

@Value
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
