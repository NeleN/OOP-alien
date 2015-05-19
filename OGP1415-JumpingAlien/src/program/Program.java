package program;

import java.util.Map;

import jumpingalien.model.Creature;
import statement.Statement;
import type.Type;
/**
 * Create a program with the given main statement and variable declarations.
 * The globalVariables map contains the type for each declared variable,
 * with the name of the variable as the key.
 */
public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables){
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
		getMainStatement().setProgram(this);
	}
	
	private Statement mainStatement;
	private Map<String, Type> globalVariables;
	private Creature user;
	
	public Creature getUser(){
		return this.user;
	}
	
	public Statement getMainStatement(){
		return this.mainStatement;
	}
	
	public Map<String, Type> getGlobalVariables(){
		return this.globalVariables;		
	}
	
	public void execute(){
		getMainStatement().execute();
	}
		
}
