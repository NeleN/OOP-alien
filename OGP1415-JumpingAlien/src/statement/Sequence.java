package statement;

import java.util.List;

/** A statement that executes of a list of statements subsequently */


public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements){
		super();
		this.statements = statements;
		this.length = statements.size();
		this.i = 0;
	}
	
	private List<Statement> statements;
	
	// controleren of er tijd over is. 
	@Override
	public void execute() throws BreakException {
		while ((i < length) && (getProgram().getTime() > 0.001)){
			statements.get(i).execute();
			i+=1;
		}
		getProgram().timeUsed(0.001);
	}

	
	int length;
	int i;

}
