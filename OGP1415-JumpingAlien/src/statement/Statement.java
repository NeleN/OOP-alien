package statement;

import program.Program;

public abstract class Statement {
	
	public Statement(){
		time = this.getProgram().getUser().getTime();
	}
	
	double time;

	public abstract void execute() throws BreakException;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	private Program program;
}
