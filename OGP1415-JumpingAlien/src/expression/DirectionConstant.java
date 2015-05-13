package expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class DirectionConstant extends E<exp> {
	
	public DirectionConstant(Direction value){
		this.direction = value;
	}
	
	private Direction direction;
	
	public E evaluate(){
		return direction;
	}

}
