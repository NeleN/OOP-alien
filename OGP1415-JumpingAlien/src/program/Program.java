package program;

import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.Creature;
import statement.BreakException;
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
	
	public void execute() throws BreakException {
		getMainStatement().execute();
	}

	public void setUser(Creature user) {
		this.user = user;
	}
	
	public boolean isWellFormed(){
		try {
			this.execute();
		} catch (BreakException exc) {
			this.isWellFormed = false;
		}
		return this.isWellFormed;
		
		
	}
	
	public double time ;
	
	public double getTime(){
		return time;
	}
	
	public double timeUsed(Double time){
		return this.time -= time;
	}
	
	private boolean isWellFormed = true;
		
}
