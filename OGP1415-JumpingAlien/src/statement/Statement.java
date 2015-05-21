package statement;

import program.Program;

public abstract class Statement {
	
	public Statement(){
	}

	public abstract void execute() throws BreakException;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
	
}
