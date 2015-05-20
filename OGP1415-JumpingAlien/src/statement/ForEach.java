package statement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import program.Program;
import jumpingalien.model.Creature;
import jumpingalien.part3.programs.IProgramFactory.*;
import expression.Expression;
import type.Type;


/**
 * A statement that executes the given body with the given variable set to
 * all objects of the given kind for which the where-expression evaluates to
 * true, sorted by the result of the given sort expression in the given
 * direction. The where- and sort-expressions are optional, and can be null;
 * */

public class ForEach extends Statement {
	
	public ForEach(String variableName, jumpingalien.part3.programs.IProgramFactory.Kind variableKind, Expression<?> where,
			Expression<?> sort, SortDirection sortDirection, Statement body){
		super();
		this.name = variableName;
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	} 
	
	public List <?> objectsOfKindWithRestriction(jumpingalien.part3.programs.IProgramFactory.Kind kind){
		if (kind == Kind.MAZUB){
			objects = (List<?>) getProgram().getUser().getWorld().getMazubsInWorld();
		}
		if (kind == Kind.BUZAM){
			objects = (List<?>) getProgram().getUser().getWorld().getBuzamsInWorld();
		}
		if (kind == Kind.SLIME){
			objects = (List<?>) getProgram().getUser().getWorld().getSlimesInWorld();
		}
		if (kind == Kind.SHARK){
			objects = (List<?>) getProgram().getUser().getWorld().getSharksInWorld();
		}
		if (kind == Kind.PLANT){
			objects = (List<?>) getProgram().getUser().getWorld().getPlantsInWorld();
		}
		if (kind == Kind.TERRAIN){			
		}
		if (kind == Kind.ANY){
			objects = (List<?>) getProgram().getUser().getWorld().getInWorldCreatures();	
		}
		objects.stream()
		.filter(a -> {
			if (where.evaluate() != null)
				return objects;
			return false;
		})
		.sorted((a,b) -> {
			getProgram().getGlobalVariables().put(getVariableName(), a);
			Double first = (Double) sort().evaluate();
			
			getProgram().getGlobalVariables().put(getVariableName(), b);
			Double second = (Double) sort().evaluate();
			
			if (getSortDirection() == SortDirection.ASCENDING)
				return Double.compare(first, second);
			else
				return Double.compare(second, first);
		})
		.collect(Collectors.toSet());

return gameobjects;
		return objects;
	}
	

	@Override
	public void execute() {
//		for (name; kind)
//			if (where)
//				body.execute();
//			return sorted(sort)
		}
		
		
	
	
	private String name;
	private jumpingalien.part3.programs.IProgramFactory.Kind kind;
	private Expression<?> where;
	private Expression<?> sort;
	private SortDirection sortDirection;
	private Statement body;
	private List<?> objects;

//	/** Kind enum */
//	public enum Kind {
//		MAZUB, BUZAM, SLIME, SHARK, PLANT, TERRAIN, ANY
//	}

}
