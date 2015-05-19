package statement;


/**
 * A statement that executes the given body with the given variable set to
 * all objects of the given kind for which the where-expression evaluates to
 * true, sorted by the result of the given sort expression in the given
 * direction. The where- and sort-expressions are optional, and can be null;
 * */

public class ForEach extends Statement {
	
	public ForEach(String variableName, Kind variableKind, Expression where, Expression sort,
			SortDirection sortDirection, Statement body)

}
