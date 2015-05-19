package statement;

import program.Program;

public abstract class Statement {

	public abstract void execute();
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
}
