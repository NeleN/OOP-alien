package expression;

import be.kuleuven.cs.som.annotate.Value;
import program.Program;

@Value
public abstract class Expression <T>{

	public abstract T evaluate();
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
}
