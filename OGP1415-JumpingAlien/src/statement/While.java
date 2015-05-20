package statement;

import expression.Expression;


/**
 * A statement that executes the given body while the given condition
 * evaluates to true
 */

public class While extends Statement {

	public While(Expression<Boolean> condition, Statement body){
		this.condition = condition;
		this.body = body;
	}
	
	private Statement body;
	private Expression<Boolean> condition;
	static boolean isLooping = true;
	
	// tijd meegeven, zolang uitvoeren en tijd teruggeven, tijd over (double meegeven  of time object)
	// drie toestanden( if, body, gedaan)
	// bij body, als er tijd over is naar if en anders onthouden waar je zit in de body.
	@Override
	public void execute() {
		while ((boolean) condition.evaluate() && isLooping){
			body.execute();
			isLooping = true;	
	}
	
	}
}
