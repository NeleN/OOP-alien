package statement;

/** A statement that does nothing */ 

public class Skip extends Statement {
	
	public Skip(){
		super();
	}

	@Override
	public void execute() {
		if (getProgram().getTime() >= 0.001){
		}
		getProgram().timeUsed(0.001);
	}
	
	
}
