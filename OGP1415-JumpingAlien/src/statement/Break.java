package statement;
/** A statement that terminates the currently executing loop */

public class Break extends Statement {
	
	public Break(){	
	}

	@Override
	public void execute() {
		While.isLooping = false;
	}
	
	
// break exception en while haalt die op !! (voor als er meerdere looops zijn!
}
