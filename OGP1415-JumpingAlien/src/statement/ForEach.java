package statement;

import java.nio.file.WatchEvent.Kind;

import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import expression.Expression;


/**
 * A statement that executes the given body with the given variable set to
 * all objects of the given kind for which the where-expression evaluates to
 * true, sorted by the result of the given sort expression in the given
 * direction. The where- and sort-expressions are optional, and can be null;
 * */

public class ForEach extends Statement {
	
	public ForEach(String variableName, Kind<?> variableKind, Expression<?> where, Expression<?> sort,
			SortDirection sortDirection, Statement body){
		this.name = variableName;
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	}

	@Override
	public void execute() {
		for(name instanceof kind){
			
		}
		
		
	}
	
	private String name;
	private Kind<?> kind;
	private Expression<?> where;
	private Expression<?> sort;
	private SortDirection sortDirection;
	private Statement body;


}
