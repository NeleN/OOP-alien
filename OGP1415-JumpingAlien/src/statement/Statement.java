package statement;

import program.Program;

public abstract class Statement {
	
	public Statement(){
	}
	
//	if (this.getProgram() != null)
//		time = ;
	
	double time ;
	
	double getTime(){
		return this.getProgram().getUser().getTime();
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
