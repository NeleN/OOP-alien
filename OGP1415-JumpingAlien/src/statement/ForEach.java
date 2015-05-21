package statement;

import java.util.*;

import program.Program;
import jumpingalien.model.Creature;
import jumpingalien.part3.programs.IProgramFactory.*;
import expression.Expression;
import type.Type;
import type.Type.type;


/**
 * A statement that executes the given body with the given variable set to
 * all objects of the given kind for which the where-expression evaluates to
 * true, sorted by the result of the given sort expression in the given
 * direction. The where- and sort-expressions are optional, and can be null;
 * */

public class ForEach extends Statement {
	
	public ForEach(String variableName, jumpingalien.part3.programs.IProgramFactory.Kind variableKind, Expression<Boolean> where,
			Expression<Double> sort, SortDirection sortDirection, Statement body){
		super();
		this.name = variableName;
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	} 

	
	public List <?> objectsOfKind(){
		if (this.kind == Kind.MAZUB){
			this.objects = (List<?>) getProgram().getUser().getWorld().getMazubsInWorld();
		}
		if (this.kind == Kind.BUZAM){
			this.objects = (List<?>) getProgram().getUser().getWorld().getBuzamsInWorld();
		}
		if (this.kind == Kind.SLIME){
			this.objects = (List<?>) getProgram().getUser().getWorld().getSlimesInWorld();
		}
		if (this.kind == Kind.SHARK){
			this.objects = (List<?>) getProgram().getUser().getWorld().getSharksInWorld();
		}
		if (this.kind == Kind.PLANT){
			this.objects = (List<?>) getProgram().getUser().getWorld().getPlantsInWorld();
		}
		if (this.kind == Kind.TERRAIN){			
		}
		if (this.kind == Kind.ANY){
			this.objects = (List<?>) getProgram().getUser().getWorld().getInWorldCreatures();	
		}	
		return objects;
	}
	
	@Override
	public void execute() throws BreakException {
		objects = objectsOfKind();
		objects.stream()
			   .filter(e -> {if (where.evaluate() != null)
				   				return (boolean) where.evaluate();
			   				return false;})
			  // .map(e -> new Type((type) e))
			   .sorted()
//			   .sorted((e1, e2) ->{if (this.sortDirection == SortDirection.ASCENDING){
//				   						Double.compare((double) e1.hashCode(),(double)e2.hashCode());}
//			   					   else{
//					   					Double.compare((double) e2.hashCode(), (double)e1.hashCode());}})
			   .forEach(e -> {if (getProgram().getGlobalVariables().containsKey(e)){
										this.name = e.toString();
										try {
											body.execute();
										} catch (Exception e1) {
											e1.printStackTrace();
										}}});
		}

	private String name;
	private jumpingalien.part3.programs.IProgramFactory.Kind kind;
	private Expression<Boolean> where;
	private Expression<Double> sort;
	private SortDirection sortDirection;
	private Statement body;
	private List<?> objects;

}
