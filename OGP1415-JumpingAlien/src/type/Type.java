package type;

public class Type {
	
	public Type(type value){
		this.value = value;
	}
	
	public type getType(){
		return value;
	}

	public enum type {
		DOUBLE, BOOLEAN, CREATURE, DIRECTION
	}
	
	private final type value;

}