package expression;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.model.Creature;
import program.Program;

@Value
public class Self extends Expression<Creature> {
	
	public Creature evaluate(){
		return getProgram().getUser();
	}
	
	private Program program;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
}
