package statement;

/** A statement that does nothing */ 

public class Skip extends Statement {
	
	public Skip(){
		super();
	}

	@Override
	public void execute() {
		if (time > 0.001){	
		}
		time -=0.001;
	}
	
	
}
