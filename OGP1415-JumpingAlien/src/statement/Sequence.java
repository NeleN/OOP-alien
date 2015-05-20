package statement;

import java.util.List;

/** A statement that executes of a list of statements subsequently */


public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements){
		this.statements = statements;
	}
	
	private List<Statement> statements;
	
	// controleren of er tijd over is. 
	@Override
	public void execute() {
		((Sequence) statements).execute();
	}


}
