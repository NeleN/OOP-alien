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
				if (time > 0.001){
					if ((boolean) condition.evaluate()){
						state = "body";
						this.execute();
					}
					else{
						state = "finish";
					}
					time -= 0.001;
				}
			}
			if (state == "body"){
				if (time > 0.001){
					body.execute();
					state = "if";
					time -= 0.001;
					this.execute();
				}
				
			}
			if (state == "finish"){
				
			}
		} catch (BreakException exc){
			state = "finish";
			}
	}
}
