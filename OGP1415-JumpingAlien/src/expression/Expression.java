package expression;

import program.Program;

public abstract class Expression <T>{

	public abstract T evaluate();
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
}
