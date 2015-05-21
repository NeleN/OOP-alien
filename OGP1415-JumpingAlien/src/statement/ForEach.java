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
		return this.objects;
	}
	
//	public static <T> void sort(List<T> objects,
//            Comparator<? super T> c){
//		c.compare(o1, o2)
//	}
	
//	public int compareTo(Object o) {
//		 
//		double compare = (double) sort.evaluate(); 
// 
//		//ascending order
//		return this.quantity - compareQuantity;
// 
//		//descending order
//		return compareQuantity - this.quantity;
// 
//	}
	
	
	@Override
	public void execute() throws BreakException {
		objects = objectsOfKind();
		objects.stream()
			   .filter(a -> {if (where.evaluate() != null)
				   				return (boolean) where.evaluate();
			   				return false;})
			   //.map(o -> new Type((type)o))
			   .sorted();
		for(Object name : objects) {
			if (getProgram().getGlobalVariables().containsKey(name)){
				this.name = name.toString();
				body.execute();
			}
		}
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
//	objects.stream()
//	.filter(a -> {
//		if (where.evaluate() != null)
//			return true;
//		return false;
//	})
//	.sorted((a,b) -> {
//		getProgram().getGlobalVariables().put(getVariableName(), a);
//		Double first = (Double) sort().evaluate();
//		
//		getProgram().getGlobalVariables().put(getVariableName(), b);
//		Double second = (Double) sort().evaluate();
//		
//		if (this.sortDirection == SortDirection.ASCENDING)
//			return Double.compare(first, second);
//		else
//			return Double.compare(second, first);
//	})
//	.collect(Collectors.toSet());
}
