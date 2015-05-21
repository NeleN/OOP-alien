package statement;
/** A statement that terminates the currently executing loop */

public class Break extends Statement {
	
	public Break(){
		super();
	}

	@Override
	public void execute() throws BreakException {
		if (getProgram().getTime() >= 0.001){
			getProgram().timeUsed(0.001);
			throw new BreakException();
		}
		
	}
	
	
// break exception en while haalt die op !! (voor als er meerdere looops zijn!
}
