package expression;

import program.Program;

public abstract class Expression <T>{

	public abstract Object evaluate();
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
}
