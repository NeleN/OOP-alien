package expression;

public abstract class Expression <T>{

	public abstract Object evaluate();
	
	public String getName(Expression<?> e){
		return this.name;
	}
	
	private String name;
}
