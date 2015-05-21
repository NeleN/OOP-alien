package statement;

import expression.Expression;


/**
 * A statement that executes the given body while the given condition
 * evaluates to true
 */

public class While extends Statement {

	public While(Expression<Boolean> condition, Statement body){
		super();
		this.condition = condition;
		this.body = body;
	}
	
	private Statement body;
	private Expression<Boolean> condition;
	private String state ="if";
	
	
	// tijd meegeven, zolang uitvoeren en tijd teruggeven, tijd over (double meegeven  of time object)
	// drie toestanden( if, body, gedaan)
	// bij body, als er tijd over is naar if en anders onthouden waar je zit in de body.
	@Override
	public void execute() {
		try{		
			if (state == "if"){
				if (getProgram().getTime() >= 0.001){
					if ((boolean) condition.evaluate()){
						state = "body";
						this.execute();
					}
					else{
						state = "finish";
					}
				}
			}
			if (state == "body"){
				if (getProgram().getTime() >= 0.001){
					body.execute();
					state = "if";
					this.execute();
				}
				
			}
			if (state == "finish"){
				
			}
			getProgram().timeUsed(0.001);
		} catch (BreakException exc){
			state = "finish";
			}
	}
}
