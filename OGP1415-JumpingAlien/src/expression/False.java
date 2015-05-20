package expression;

public class False extends Bool {
	public False() {
		this.value = false;
	}
	
	boolean value; 
	
	@Override
	public Boolean evaluate() {
		return value;
	}

}
